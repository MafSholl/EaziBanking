package com.nibss.eazibank.controller;

import com.nibss.eazibank.data.models.Customer;
import com.nibss.eazibank.dto.request.CreateCustomerRequest;
import com.nibss.eazibank.services.NibssInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/nibss")
public class NibssController {

    @Autowired
    private NibssInterface nibssInterface;
    @GetMapping("/is-nibss")
    public ResponseEntity<?> isNibssAvailable() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bvn-generator")
    public ResponseEntity<?> bvnGenerator(@RequestBody CreateCustomerRequest request) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
