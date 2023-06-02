package com.gym.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "muscle", "workoutSets"
})
@ToString(exclude = {
        "muscle", "workoutSets"
})

@Entity
@Table(name = "c_exercises")
public class CExercise {

    @Id
    @Column(name = "exercise_id")
    @GeneratedValue(generator = "c_exercises_exercise_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "c_exercises_exercise_id_seq",
            sequenceName = "c_exercises_exercise_id_seq", allocationSize = 1, initialValue = 100)
    private Long exerciseId;

    @Column(name = "exercise_name")
    @Size(min = 2, max = 80)
    private String exerciseName;

    @Column(name = "exercise")
    private String exerciseTXT;

    @ManyToMany(mappedBy = "exercises", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("exercises")
    private Set<CMuscle> muscle = Collections.emptySet();

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<WorkoutSet> workoutSets = Collections.emptySet();

}
