package com.gym.repository;

import com.gym.domain.entity.LogWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogWorkoutRepository extends JpaRepository<LogWorkout, Long> {

}
