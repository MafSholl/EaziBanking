package com.eazibank.nibss.controller;

import com.eazibank.nibss.controller.response.ApiResponse;
import com.eazibank.nibss.dto.response.NibssBankUserDto;
import com.eazibank.nibss.dto.response.NibssInterbankDto;
import com.eazibank.nibss.models.CreateBvnDto;
import com.eazibank.nibss.services.NibssInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/nibss")
public class NibssController {

    @Autowired
    private NibssInterfaceService nibssInterfaceService;
    @GetMapping("/is-nibss")
    public ResponseEntity<?> isNibssAvailable() {
        if(nibssInterfaceService.isNibssAvailable()) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<> (HttpStatus.BAD_GATEWAY);
    }

    @PostMapping("/bvn-generator")
    public ResponseEntity<?> bvnGenerator(@Valid @RequestBody CreateBvnDto createBvnRequest) {
        if(nibssInterfaceService.isNibssAvailable()) {
            NibssBankUserDto newBankUser = nibssInterfaceService.bvnGenerator(createBvnRequest);
            ApiResponse response = ApiResponse.builder()
                    .status("success")
                    .message("BVN created successfully")
                    .data(newBankUser)
                    .statusCode(HttpStatus.OK.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<> (HttpStatus.BAD_GATEWAY);
    }

    @PutMapping("/nibss-transfer")
    public ResponseEntity<?> nibssInterbankDeposit(@Valid @RequestBody NibssInterbankDto interbankTransferRequest) {
        NibssBankUserDto transferResponse = nibssInterfaceService.nibssInterbankDeposit(interbankTransferRequest);
        ApiResponse response = ApiResponse.builder()
                .status("success")
                .message("Transfer successful")
                .data(transferResponse)
                .statusCode((HttpStatus.OK.value()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
