package com.irene.poxylari.emicalculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
public class Input{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "This value must be positive")
    private float loanAmount;

    @Min(value = 0, message = "This value must be positive")
    @Max(value = 100, message = "This value must be less than 100")
    private float yearlyInterestRate;

    @Min(value = 0, message = "This value must be positive")
    @Max(value = 30, message = "This value must less than 30")
    private int loanTermsInYears;

    @Email(message = "Please provide a valid email address")
    private String emailAddress;

    private float result;

    public Input(Long id, float loanAmount, float yearlyInterestRate, int loanTermsInYears, String emailAddress, float result) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.yearlyInterestRate = yearlyInterestRate;
        this.loanTermsInYears = loanTermsInYears;
        this.emailAddress = emailAddress;
        this.result = result;
    }

    public Input() {

    }
}
