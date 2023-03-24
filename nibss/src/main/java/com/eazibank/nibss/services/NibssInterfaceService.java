package com.eazibank.nibss.services;


import com.eazibank.nibss.dto.response.NibssBankUserDto;
import com.eazibank.nibss.dto.response.NibssInterbankDto;
import com.eazibank.nibss.models.CreateBvnDto;

public interface NibssInterfaceService {
     NibssBankUserDto bvnGenerator(CreateBvnDto createBvnDto);

     boolean isNibssAvailable();

     NibssBankUserDto nibssInterbankDeposit(NibssInterbankDto nibssDepositRequest);
}
