package com.gym.repository;

import com.gym.domain.entity.TargetGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TargetGymRepository extends JpaRepository<TargetGym, Long> {

    @Query( value = "DELETE target_gym  WHERE target_id = :id", nativeQuery = true)
    void deleteTargetGym(Long id);
}
