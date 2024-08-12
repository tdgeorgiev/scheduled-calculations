package com.example.scheduled_calculations.controller;

import com.example.scheduled_calculations.entity.CalculationResult;
import com.example.scheduled_calculations.repository.CalculationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calculation-results")
@CrossOrigin(origins = "http://localhost:8081")
public class CalculationResultController {

    private final CalculationResultRepository calculationResultRepository;

    @Autowired
    public CalculationResultController(CalculationResultRepository calculationResultRepository) {
        this.calculationResultRepository = calculationResultRepository;
    }

    // Get all calculation results
    @GetMapping
    public List<CalculationResult> getAllCalculationResults() {
        return calculationResultRepository.findAll();
    }

    // Get the most recent calculation result
    @GetMapping("/latest")
    public ResponseEntity<CalculationResult> getLatestCalculationResult() {
        Optional<CalculationResult> latestResult = calculationResultRepository.findTopByOrderByTimestampDesc();
        return latestResult.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
