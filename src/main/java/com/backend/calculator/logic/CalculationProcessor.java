package com.backend.calculator.logic;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;

@Component
public class CalculationProcessor {

    /**
     * Performs the calculation based on the given input JSON which contains a mathematical expression.
     * @param inputJson The input JSON string containing the mathematical expression.
     * @return The result of the evaluated expression as a string.
     */
    public String performCalculation(String inputJson) {
        try {
            // Assuming inputJson directly contains the expression as a plain string
            // In real scenarios, you might need to parse it from a JSON object.
            Expression expression = new ExpressionBuilder(inputJson).build();
            double result = expression.evaluate();
            return String.valueOf(result);  // Convert the result to String to store in JSON format
        } catch (Exception e) {
            // Handle potential exceptions such as illegal arguments or math errors
            return "Error in expression: " + e.getMessage();
        }
    }
}