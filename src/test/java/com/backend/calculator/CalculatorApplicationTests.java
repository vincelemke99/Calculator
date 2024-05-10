package com.backend.calculator;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

// Import your main Spring Boot application class if necessary
import com.backend.calculator.application.CalculatorApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CalculatorApplication.class) // Make sure CalculatorApplication is your main @SpringBootApplication class
public class CalculatorApplicationTests {

	// Your test methods here

}
