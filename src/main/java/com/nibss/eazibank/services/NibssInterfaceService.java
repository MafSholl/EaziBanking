package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssBankUserDto;
import com.nibss.eazibank.dto.NibssInterbankDto;

public interface NibssInterfaceService {
     NibssBankUserDto bvnGenerator(CreateBvnDto createBvnDto);

     boolean isNibssAvailable();

     Object nibssInterbankDeposit(NibssInterbankDto nibssDepositRequest);
}
