package com.nibss.eazibank.controller;

import com.nibss.eazibank.services.NibssInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> bvnGenerator() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
