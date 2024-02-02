package com.irene.poxylari.emicalculator.service;

import com.irene.poxylari.emicalculator.model.Input;
import com.irene.poxylari.emicalculator.repository.InputRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputServiceTest {
    private InputService inputService; //service under test
    private InputRepository inputRepository;

    @BeforeEach
    public void setUp() {
        this.inputService = new InputService(this.inputRepository);
    }

    @Test
    public void InputService_calculateEmi_ReturnEmiValue() {
        Input input = Input.builder().emailAddress("irene.poxylari@gmail.com").
                loanAmount(10000).
                yearlyInterestRate(5).loanTermsInYears(2).build();

        double result = this.inputService.calculateEmi(input);

        assertEquals(438.7083435058594, result);
    }
}
