package com.backend.calculator.repository;

import com.backend.calculator.model.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository indicates that this interface is a Spring-managed repository.
// It abstracts the interaction with the database, allowing for simpler data access and manipulation.
@Repository
public interface CalculationResultRepository extends JpaRepository<Calculator, Integer> {
    // This interface extends JpaRepository, providing CRUD operations for the Calculator entity.
    // Type parameters: Calculator indicates the domain type the repository manages, and Integer is the type of the id.

    // By extending JpaRepository, it inherits methods like save(), findOne(), findById(), findAll(), count(), delete(), deleteById() etc.
    // These methods are automatically implemented by Spring Data JPA, so you do not need to write any SQL code.
}
