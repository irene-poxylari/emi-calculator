package com.irene.poxylari.emicalculator.service;

import com.irene.poxylari.emicalculator.model.Input;
import com.irene.poxylari.emicalculator.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputService {
    private final InputRepository inputRepository;

    @Autowired
    public InputService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public float calculateEmi(Input input){

        float principal = input.getLoanAmount();
        float annualInterestRate = input.getYearlyInterestRate()/100;
        int loanTermInYears = input.getLoanTermsInYears();
        float monthlyInterestRate = annualInterestRate / 12;
        int numberOfPayments = loanTermInYears * 12;


        return (float) ((principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                       / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));

    }
    public Input addInput (Input input){

        return this.inputRepository.save(input);

    }

    public List<Input> findAllInputs() {

        return this.inputRepository.findAll();

    }
}
