package com.backend.calculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

// @Entity annotation marks this class as a JPA entity, meaning it will be mapped to a database table.
@Entity
public class Calculator {

    // @Id marks the 'id' field as the primary key in the database table.
    // @GeneratedValue indicates that the ID should be generated automatically; here, using the identity column method.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 'input' field to store the mathematical expression entered by the user.
    private String input;

    // 'output' field to store the result of the calculated expression.
    private String output;

    // 'timestamp' field to store the date when the calculation was made.
    private LocalDate timestamp;

    // Default constructor required by JPA.
    public Calculator() {
    }

    // Getter for timestamp.
    public LocalDate getTimestamp() {
        return timestamp;
    }

    // Setter for timestamp, allowing modification of the timestamp value.
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    // Getter for ID.
    public int getId() {
        return id;
    }

    // Setter for ID, allowing modification of the ID.
    public void setId(int id) {
        this.id = id;
    }

    // Getter for input.
    public String getInput() {
        return input;
    }

    // Setter for input, allowing modification of the input string.
    public void setInput(String input) {
        this.input = input;
    }

    // Getter for output.
    public String getOutput() {
        return output;
    }

    // Setter for output, allowing modification of the output string.
    public void setOutput(String output) {
        this.output = output;
    }
}
