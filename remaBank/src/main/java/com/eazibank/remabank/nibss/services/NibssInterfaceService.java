package com.eazibank.remabank.nibss.services;

import com.eazibank.remabank.account.dto.response.CreateBvnDto;
import com.eazibank.remabank.nibss.dto.response.NibssBankUserDto;
import com.eazibank.remabank.nibss.dto.response.NibssInterbankDto;

public interface NibssInterfaceService {
     NibssBankUserDto bvnGenerator(CreateBvnDto createBvnDto);

     boolean isNibssAvailable();

     NibssBankUserDto nibssInterbankDeposit(NibssInterbankDto nibssDepositRequest);
}
