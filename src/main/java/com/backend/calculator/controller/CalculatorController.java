package com.backend.calculator.controller;


import com.backend.calculator.model.Calculator;
import com.backend.calculator.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculation")
public class CalculatorController {
    @Autowired
    private CalculationService calculationService;

    @PostMapping("/add")
    public Calculator add(@RequestBody Calculator calculator) {
        try {
            if (calculator.getInput() == null || calculator.getInput().isEmpty()) {
                throw new IllegalArgumentException("Input expression cannot be empty");  // Validate the input
            }

            return calculationService.saveCalculation(calculator);  // Return the saved calculator instance

        } catch (Exception e) {
            // Log error and return an appropriate response
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in backend", e);
        }
    }

    @GetMapping("/getAll")
    public Object getRecentResults() {
        // Assuming CalculationService has a method to retrieve the last 10 results
        List<Calculator> recentResults = calculationService.getAllCalculationResult();  // Get all results
        Collections.sort(recentResults, (a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));  // Sort by timestamp, latest first
        return recentResults.stream().limit(10).collect(Collectors.toList());  // Return the last 10 results
    }

}