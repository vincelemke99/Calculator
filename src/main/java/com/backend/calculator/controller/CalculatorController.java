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

// @RestController indicates that this class handles HTTP requests and is a web controller.
// @RequestMapping specifies the base URL path ("calculation") that this controller handles.
@RestController
@RequestMapping("/calculation")
public class CalculatorController {
    // @Autowired injects CalculationService instance into this class, eliminating the need for manual instantiation.
    @Autowired
    private CalculationService calculationService;

    // @PostMapping handles the HTTP POST requests matched with the specified URI ("/add").
    @PostMapping("/add")
    public Calculator add(@RequestBody Calculator calculator) {
        try {
            // Input validation: Checks if the calculator's input is null or empty, and throws an exception if it is.
            if (calculator.getInput() == null || calculator.getInput().isEmpty()) {
                throw new IllegalArgumentException("Input expression cannot be empty");
            }

            // Saves the calculation to the database and returns the Calculator object with the result.
            return calculationService.saveCalculation(calculator);

        } catch (Exception e) {
            // Logs the exception and throws a new ResponseStatusException with HTTP status 500 (Internal Server Error).
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in backend", e);
        }
    }

    // @GetMapping handles the HTTP GET requests for "/getAll".
    @GetMapping("/getAll")
    public Object getRecentResults() {
        // Retrieves all calculation results from the service.
        List<Calculator> recentResults = calculationService.getAllCalculationResult();
        // Sorts the results by timestamp in descending order (most recent first).
        Collections.sort(recentResults, (a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
        // Limits the list to the last 10 entries and returns it.
        return recentResults.stream().limit(10).collect(Collectors.toList());
    }
}
