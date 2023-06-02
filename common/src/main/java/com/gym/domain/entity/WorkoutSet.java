package com.gym.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "workout", "exercise"
})
@ToString(exclude = {
        "workout", "exercise"
})

@Entity
@Table(name = "workout_set")
public class WorkoutSet {

    @Id
    @Column(name = "workout_set_id")
    @GeneratedValue(generator = "workout_set_workout_set_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "workout_set_workout_set_id_seq",
            sequenceName = "workout_set_workout_set_id_seq", allocationSize = 1, initialValue = 100)
    private Long workoutId;


    @Column(name = "quantity_set")
    @NotNull
    private Integer quantitySet;

    @Column(name = "max_repetitions")
    @NotNull
    private Integer maxRepetitions;

    @Column(name = "min_repetitions")
    @NotNull
    private Integer minRepetitions;

    @Column(name = "max_weight")
    @NotNull

    private Double maxWeight;

    @Column(name = "min_weight")
    @NotNull
    private Double minWeight;

    @Column(name = "rest_time")
    @NotNull
    private Timestamp restTime;

    @Column(name = "created")
    @NotNull
    @JsonIgnore
    private Timestamp created;

    @Column(name = "changed")
    @JsonIgnore
    private Timestamp changed;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonBackReference
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonBackReference
    private CExercise exercise;

}
