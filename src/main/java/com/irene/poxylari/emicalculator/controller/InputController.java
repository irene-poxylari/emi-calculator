package com.irene.poxylari.emicalculator.controller;


import com.irene.poxylari.emicalculator.model.Input;
import com.irene.poxylari.emicalculator.service.InputService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/input")
public class InputController {

    private final InputService inputService;
    private static final Logger log = LoggerFactory.getLogger(Input.class);


    public InputController(InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Input>> getAllInputs () {
        List<Input> inputs = this.inputService.findAllInputs();
        return new ResponseEntity<>(inputs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Input> addInput (@RequestBody Input input){
        Input newInput = inputService.addInput(input);
        return new ResponseEntity<>(newInput, HttpStatus.CREATED);

    }
    @PostMapping("/calculate-emi")
    public ResponseEntity<Float> calculateEmi(@Valid @RequestBody Input input, Errors errors) {
        if(errors.hasErrors()){
            log.error("Inputs are invalid:" + errors.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        float emi = inputService.calculateEmi(input);
        return new ResponseEntity<>(emi, HttpStatus.OK);
    }
}