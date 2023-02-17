package com.nibss.eazibank.nibss.services;

import com.nibss.eazibank.account.dto.response.CreateBvnDto;
import com.nibss.eazibank.nibss.dto.response.NibssBankUserDto;
import com.nibss.eazibank.nibss.dto.response.NibssInterbankDto;

public interface NibssInterfaceService {
     NibssBankUserDto bvnGenerator(CreateBvnDto createBvnDto);

     boolean isNibssAvailable();

     NibssBankUserDto nibssInterbankDeposit(NibssInterbankDto nibssDepositRequest);
}
