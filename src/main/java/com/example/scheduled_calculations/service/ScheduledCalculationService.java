package com.example.scheduled_calculations.service;

import com.example.scheduled_calculations.entity.CalculationResult;
import com.example.scheduled_calculations.entity.SchedulingPeriod;
import com.example.scheduled_calculations.repository.CalculationResultRepository;
import com.example.scheduled_calculations.repository.SchedulingPeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ScheduledCalculationService {

    private final SchedulingPeriodRepository schedulingPeriodRepository;
    private final CalculationResultRepository calculationResultRepository;

    @Autowired
    public ScheduledCalculationService(SchedulingPeriodRepository schedulingPeriodRepository, CalculationResultRepository calculationResultRepository) {
        this.schedulingPeriodRepository = schedulingPeriodRepository;
        this.calculationResultRepository = calculationResultRepository;
    }

    @Scheduled(fixedRate = 900000)
    public void executeScheduledCalculations() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<SchedulingPeriod> schedulingPeriods = schedulingPeriodRepository.findAll();

        for (SchedulingPeriod schedulingPeriod : schedulingPeriods) {
            if (currentTime.isAfter(schedulingPeriod.getStartTime()) &&
                    currentTime.isBefore(schedulingPeriod.getEndTime())) {

                CalculationResult result = performCalculation(schedulingPeriod);
                calculationResultRepository.save(result);
                System.out.println("Performing calculation for period starting at: " + schedulingPeriod.getStartTime());
            }
        }
    }

    private CalculationResult performCalculation(SchedulingPeriod schedulingPeriod) {
        Random random = new Random();
        LocalDateTime timestamp = LocalDateTime.now().withSecond(0).withNano(0);

        double num1 = random.nextDouble();
        double num2 = random.nextDouble();
        double randomNumsSum = num1 + num2;

        CalculationResult result = new CalculationResult();
        result.setTimestamp(timestamp);
        result.setResult(randomNumsSum);
        result.setSchedulePeriodId(schedulingPeriod.getId());
        return result;
    }
}
