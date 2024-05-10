package com.backend.calculator.repository;



import com.backend.calculator.model.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationResultRepository extends JpaRepository<Calculator, Integer> {
}
