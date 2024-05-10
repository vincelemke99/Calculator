package com.backend.calculator.service;


import com.backend.calculator.logic.CalculationProcessor;
import com.backend.calculator.model.Calculator;
import com.backend.calculator.repository.CalculationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculationServiceImplementation implements CalculationService {

    @Autowired
    private CalculationResultRepository calculationResultRepository;

    @Autowired
    private CalculationProcessor calculationProcessor;
    @Override
    public Calculator saveCalculation(String inputJson) {
        String outputJson = calculationProcessor.performCalculation(inputJson);

        Calculator calculator = new Calculator();
        calculator.setInput(inputJson);
        calculator.setOutput(outputJson);
        calculator.setTimestamp(LocalDate.now());
        return calculationResultRepository.save(calculator);
    }


    @Override
    public Calculator saveCalculation(Calculator calculator) {
        if (calculator.getInput() != null && !calculator.getInput().isEmpty()) {
            String output = calculationProcessor.performCalculation(calculator.getInput());
            calculator.setOutput(output);  // Set the calculated output
            calculator.setTimestamp(LocalDate.now());
            return calculationResultRepository.save(calculator);
        } else {
            throw new IllegalArgumentException("Calculator input cannot be null or empty");
        }
    }


    @Override
    public List<Calculator> getAllCalculationResult() {

        return calculationResultRepository.findAll();
    }
}