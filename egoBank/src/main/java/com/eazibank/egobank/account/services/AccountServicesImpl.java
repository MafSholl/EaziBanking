package com.eazibank.egobank.account.services;

import com.eazibank.egobank.account.dto.request.AccountBalanceRequest;
import com.eazibank.egobank.account.dto.request.CreditAccountRequest;
import com.eazibank.egobank.account.dto.request.DebitAccountRequest;
import com.eazibank.egobank.account.dto.request.RegisterAccountRequest;
import com.eazibank.egobank.account.dto.response.*;
import com.eazibank.egobank.account.models.Account;
import com.eazibank.egobank.account.models.AccountType;
import com.eazibank.egobank.account.repository.AccountRepository;
import com.eazibank.egobank.exception.exceptions.AccountDoesNotExistException;
import com.eazibank.egobank.exception.exceptions.EaziBankExceptions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.eazibank.egobank.account.models.AccountType.*;
import static java.lang.System.getenv;

@Service
public class AccountServicesImpl implements AccountServices {
    @Autowired
    private AccountRepository accountRepository;
    private int accountNumber = 100_000_000;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public RegisterAccountResponse createAccount(RegisterAccountRequest request) {

        Account account = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .accountNumber(accountNumberGenerator())
                .balance(accountBalanceSetter(request))
                .accountType(accountTypeSetter(request))
                .accountCreationDate(LocalDateTime.now())
                .build();
        CreateBvnDto createBvnDto = new CreateBvnDto(account);
        createBvnDto.setBankId(1L);

        //Setting up a client and make request through the client
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest bvnRequest = null;
        try {
            bvnRequest = HttpRequest.newBuilder()
                            .uri(new URI(getenv("NIBSS_BASE_URL")))
                            .timeout(Duration.of(10, ChronoUnit.SECONDS))
                            .POST(HttpRequest.BodyPublishers.ofString(request.toString()))
                            .build();
            HttpResponse<String> httpResponse = httpClient.send(bvnRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e)  {
            throw new RuntimeException(e);
        }



//        account.setBvn(
//
//        ); //should be changed. this should call the Nibss api instead

        Account createdAccount = accountRepository.save(account);

        RegisterAccountResponse response = modelMapper.map(createdAccount, RegisterAccountResponse.class);
        return response;
    }

    private BigInteger accountBalanceSetter(RegisterAccountRequest request) {
        if (request.getOpeningAmount() == null) {
            return BigInteger.ZERO;
        }else {
            return request.getOpeningAmount();
        }
    }

    private AccountType accountTypeSetter(RegisterAccountRequest request) {
        if(request.getAccountType().equalsIgnoreCase("savings")) {
            return SAVINGS;
        } else if(request.getAccountType().equalsIgnoreCase("current")) {
            return CURRENT;
        } else if(request.getAccountType().equalsIgnoreCase("Domiciliary")) {
            return DOMICILIARY;
        }
        throw new EaziBankExceptions("Valid account type isn't given", HttpStatus.BAD_REQUEST.value());
    }

    private String accountNumberGenerator() {
        StringBuilder generatedAccountNumber = new StringBuilder("0");
        generatedAccountNumber.append(this.accountNumber++);
        generatedAccountNumber = accountNumberValidator(generatedAccountNumber);
        return generatedAccountNumber.toString();
    }

    private StringBuilder accountNumberValidator(StringBuilder generatedAccountNumber) {
        if(accountRepository.findByAccountNumber(generatedAccountNumber.toString()).isPresent())
            generatedAccountNumber = new StringBuilder(accountNumberGenerator());
        return generatedAccountNumber;
    }

    @Override
    public CreditAccountResponse creditAccount(CreditAccountRequest creditRequest) {
        Account account = new Account();
        account.setBalance(creditRequest.getAmount());
        account.setAccountNumber(creditRequest.getAccountNumber());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        BigInteger balance = accountInDatabase.get().getBalance();
        accountInDatabase.get().setBalance(balance.add(creditRequest.getAmount()));

        Account creditedAccount = accountRepository.save(accountInDatabase.get());
        CreditAccountResponse creditResponse = new CreditAccountResponse();
        if (creditedAccount.getBalance().compareTo(balance) > 0) {
            creditResponse.setSuccessful(true);
            creditResponse.setAccountNumber(creditedAccount.getAccountNumber());
            creditResponse.setBalance(creditedAccount.getBalance());
            creditResponse.setFirstName(creditedAccount.getFirstName());
            creditResponse.setLastName(creditedAccount.getLastName());
            creditResponse.setBankVerificationNumber(creditedAccount.getBvn());
        }
        return creditResponse;
    }

    @Override
    public DebitAccountResponse debitAccount(DebitAccountRequest debitRequest) {
        Account account = new Account();
        account.setAccountNumber(debitRequest.getAccountNumber());
        account.setBalance(debitRequest.getAmount());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(account.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        BigInteger previousBalance = accountInDatabase.get().getBalance();
        accountInDatabase.get().setBalance(accountInDatabase.get().getBalance().subtract(account.getBalance()));
        Account debitedAccount = accountRepository.save(accountInDatabase.get());

        DebitAccountResponse response = new DebitAccountResponse();
        if(previousBalance.subtract(debitedAccount.getBalance()).equals(debitRequest.getAmount())) {
            response.setSuccessful(true);
            response.setAccountNumber(debitedAccount.getAccountNumber());
            response.setFirstName(debitedAccount.getFirstName());
            response.setLastName(debitedAccount.getLastName());
            response.setDebitedAmount(debitRequest.getAmount());
            response.setBalance(debitedAccount.getBalance());
        }

        return response;
    }

    @Override
    public AccountBalanceResponse getBalance(AccountBalanceRequest checkBalanceRequest) {
        Account account = new Account();
        account.setAccountNumber(checkBalanceRequest.getAccountNumber());

        Optional<Account> accountInDatabase = accountRepository.findByAccountNumber(account.getAccountNumber());
        if(accountInDatabase.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");

        AccountBalanceResponse response = new AccountBalanceResponse();
        response.setBalance(accountInDatabase.get().getBalance());
        response.setFirstName(accountInDatabase.get().getFirstName());
        response.setLastName(accountInDatabase.get().getLastName());
        return response;
    }

    @Override
    public Optional<Account> findAccount(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) throw new AccountDoesNotExistException("Account does not exist");
        return account;
    }
}
