package com.example.scheduled_calculations.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calculation_results")
public class CalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "scheduling_period_id", nullable = false)
    private Long schedulePeriodId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Double result;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getResult() {
        return result;
    }

    public Long getSchedulePeriodId() {
        return schedulePeriodId;
    }

    public void setSchedulePeriodId(Long schedulePeriodId) {
        this.schedulePeriodId = schedulePeriodId;
    }
}
