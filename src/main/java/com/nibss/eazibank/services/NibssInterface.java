package com.nibss.eazibank.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public interface NibssInterface { ;
     String bvnGenerator();

     boolean isNibssAvailable();
}
