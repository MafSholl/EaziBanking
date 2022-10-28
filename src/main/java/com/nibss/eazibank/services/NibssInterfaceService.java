package com.nibss.eazibank.services;

import com.nibss.eazibank.dto.CreateBvnDto;
import com.nibss.eazibank.dto.NibssCustomerDto;

public interface NibssInterfaceService {
     NibssCustomerDto bvnGenerator(CreateBvnDto createBvnDto);

     boolean isNibssAvailable();
}
