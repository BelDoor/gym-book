package com.gym.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "workoutSet"
})
@ToString(exclude = {
        "workoutSet"
})

@Entity
@Table(name = "log_workout")
public class LogWorkout {

    @Id
    @Column(name = "log_workout_id")
    @GeneratedValue(generator = "log_workout_log_workout_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "log_workout_log_workout_id_seq",
            sequenceName = "log_workout_log_workout_id_seq", allocationSize = 1, initialValue = 100)
    private Long workoutId;

    @Column(name = "log_weight")
    @NotNull
    @Positive
    private Double logWeight;

    @Column(name = "log_repetitions")
    @NotNull
    @Positive
    private Integer logRepetitions;

    @Column(name = "created")
    @NotNull
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;


    @ManyToOne
    @JoinColumn(name = "workout_set_id")
    @JsonBackReference
    private WorkoutSet workoutSet;

}
