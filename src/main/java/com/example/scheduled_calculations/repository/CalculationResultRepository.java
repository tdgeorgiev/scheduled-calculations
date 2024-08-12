package com.example.scheduled_calculations.repository;

import com.example.scheduled_calculations.entity.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculationResultRepository extends JpaRepository<CalculationResult, Long> {

    Optional<CalculationResult> findTopByOrderByTimestampDesc();
}
