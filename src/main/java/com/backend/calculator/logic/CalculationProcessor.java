package com.backend.calculator.logic;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;

// @Component annotation indicates that this class is a Spring-managed bean.
// It is automatically detected during classpath scanning.
@Component
public class CalculationProcessor {

    /**
     * Performs the calculation based on the given input which is expected to be a mathematical expression.
     * This method is designed to evaluate the expression and return the result.
     *
     * @param inputJson The input string, presumably JSON, containing the mathematical expression.
     *                  This method currently expects a direct mathematical expression in the input string.
     *                  For actual JSON handling, additional parsing would be required to extract the expression.
     * @return The result of the evaluated expression as a string, or an error message if evaluation fails.
     */
    public String performCalculation(String inputJson) {
        try {
            // Builds a new expression instance using the input string.
            Expression expression = new ExpressionBuilder(inputJson).build();

            // Evaluates the expression and returns the result as a double.
            double result = expression.evaluate();

            // Converts the result from double to String for consistent API response format.
            return String.valueOf(result);
        } catch (Exception e) {
            // Returns an error message if there's any issue during the expression evaluation,
            // such as syntax errors or illegal mathematical operations.
            return "Error in expression: " + e.getMessage();
        }
    }
}
