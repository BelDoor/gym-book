package com.gym.repository;

import com.gym.domain.entity.parameter.ParameterGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ParameterGymRepository extends JpaRepository<ParameterGym, Long> {

    @Query( value = "DELETE FROM parameters_gym  WHERE parameter_id = :id", nativeQuery = true)
    void deleteParameter(Long id);

}
