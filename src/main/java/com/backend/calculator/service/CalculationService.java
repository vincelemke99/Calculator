package com.backend.calculator.service;

import com.backend.calculator.model.Calculator;

import java.util.List;

public interface CalculationService {
    Calculator saveCalculation(String inputJson);

    public Calculator saveCalculation(Calculator calculator);
    public List<Calculator> getAllCalculationResult();


}