package com.example.scheduled_calculations.repository;

import com.example.scheduled_calculations.entity.SchedulingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingPeriodRepository extends JpaRepository<SchedulingPeriod, Long> {

}
