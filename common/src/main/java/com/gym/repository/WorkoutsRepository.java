package com.gym.repository;

import com.gym.domain.entity.Workouts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutsRepository extends JpaRepository<Workouts, Long> {

}
