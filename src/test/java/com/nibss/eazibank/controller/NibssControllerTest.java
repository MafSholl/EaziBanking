package com.nibss.eazibank.controller;

import com.nibss.eazibank.data.repositories.NibssRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
class NibssControllerTest {

    @Test
    void nibssControllerExistTest() {
        NibssController nibssController = new NibssController();
        assertThat(nibssController).isNotNull();
    }
}