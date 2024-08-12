package com.example.scheduled_calculations;

import com.example.scheduled_calculations.entity.SchedulingPeriod;
import com.example.scheduled_calculations.repository.SchedulingPeriodRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SchedulingPeriodInitializer implements ApplicationRunner {

    private final SchedulingPeriodRepository schedulingPeriodRepository;

    public SchedulingPeriodInitializer(SchedulingPeriodRepository schedulingPeriodRepository) {
        this.schedulingPeriodRepository = schedulingPeriodRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (schedulingPeriodRepository.count() == 0) {
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime endTime = startTime.plusHours(2);

            LocalDateTime currentPeriodStartTime = startTime;
            while (currentPeriodStartTime.isBefore(endTime)) {
                LocalDateTime currentPeriodEndTime = currentPeriodStartTime.plusMinutes(30);

                SchedulingPeriod newSchedulingPeriod = new SchedulingPeriod();
                newSchedulingPeriod.setStartTime(currentPeriodStartTime);
                newSchedulingPeriod.setEndTime(currentPeriodEndTime);

                schedulingPeriodRepository.save(newSchedulingPeriod);

                currentPeriodStartTime = currentPeriodEndTime;
            }
        }
    }
}

