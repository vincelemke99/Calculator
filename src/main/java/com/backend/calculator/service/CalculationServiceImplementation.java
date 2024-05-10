package com.backend.calculator.service;

import com.backend.calculator.logic.CalculationProcessor;
import com.backend.calculator.model.Calculator;
import com.backend.calculator.repository.CalculationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// @Service marks this class as a Spring service, which is a stereotype for the service layer.
// It holds business logic and calls methods in the repository layer.
@Service
public class CalculationServiceImplementation implements CalculationService {

    // Autowired components are automatically injected by the Spring container.
    @Autowired
    private CalculationResultRepository calculationResultRepository;

    @Autowired
    private CalculationProcessor calculationProcessor;

    // Implements saveCalculation to handle a string input (presumably JSON).
    // It processes the input, performs the calculation, and saves the result.
    @Override
    public Calculator saveCalculation(String inputJson) {
        // Perform the calculation using the CalculationProcessor
        String outputJson = calculationProcessor.performCalculation(inputJson);

        // Create a new Calculator instance and set its properties
        Calculator calculator = new Calculator();
        calculator.setInput(inputJson);
        calculator.setOutput(outputJson);
        calculator.setTimestamp(LocalDate.now()); // Set the current date as the timestamp

        // Save the Calculator instance to the database using the repository
        return calculationResultRepository.save(calculator);
    }

    // Implements saveCalculation to save or update a Calculator instance.
    @Override
    public Calculator saveCalculation(Calculator calculator) {
        if (calculator.getInput() != null && !calculator.getInput().isEmpty()) {
            // Calculate output if input is valid
            String output = calculationProcessor.performCalculation(calculator.getInput());
            calculator.setOutput(output);  // Set the calculated output
            calculator.setTimestamp(LocalDate.now());  // Update the timestamp

            // Save the Calculator instance to the database
            return calculationResultRepository.save(calculator);
        } else {
            // Throw an exception if the input is invalid
            throw new IllegalArgumentException("Calculator input cannot be null or empty");
        }
    }

    // Implements getAllCalculationResult to retrieve all stored results.
    @Override
    public List<Calculator> getAllCalculationResult() {
        // Retrieve all calculator results from the repository
        return calculationResultRepository.findAll();
    }
}
