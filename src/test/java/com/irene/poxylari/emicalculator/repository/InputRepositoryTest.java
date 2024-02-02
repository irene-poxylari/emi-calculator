package com.irene.poxylari.emicalculator.repository;

import com.irene.poxylari.emicalculator.model.Input;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InputRepositoryTest {
    @Autowired
    private InputRepository inputRepository;

    @Test
    public void InputRepository_addInput_ReturnSavedInput() {

        //Arrange
        Input input = Input.builder().emailAddress("irene.poxylari@gmail.com").
                loanAmount(100000).
                yearlyInterestRate(7).loanTermsInYears(10).build();
        //Act
        Input savedInput = inputRepository.save(input);

        //Assert
        assertEquals(savedInput, input);
    }

}
