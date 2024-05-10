package com.backend.calculator.service;

import com.backend.calculator.model.Calculator;
import java.util.List;

// This interface defines the contract for the calculation-related services.
// It outlines the methods that any implementing class must provide to manage Calculator entities.
public interface CalculationService {

    // Saves a calculation based on a string input, presumably in JSON format.
    // This method should parse the input, perform the calculation, and save the result.
    Calculator saveCalculation(String inputJson);

    // Saves or updates a Calculator entity in the database.
    // This method directly takes a Calculator instance, performs any necessary calculations or updates, and persists it.
    public Calculator saveCalculation(Calculator calculator);

    // Retrieves all calculation results stored in the database.
    // This method should return a list of all Calculator instances, potentially after applying some sort of sorting or filtering.
    public List<Calculator> getAllCalculationResult();
}
