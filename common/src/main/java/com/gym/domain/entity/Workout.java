package com.gym.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "trainingBlockId"
})
@ToString(exclude = {
        "trainingBlockId"
})

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @Column(name = "workout_id")
    @GeneratedValue(generator = "workouts_workout_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "workouts_workout_id_seq",
            sequenceName = "workouts_workout_id_seq", allocationSize = 1, initialValue = 100)
    private Long blockId;

    @Column(name = "num_day")
    @Positive
    @NotNull
    private Integer numTraining;

    @Column(name = "target_workout")
    @NotNull
    private String targetWorkout;

    @Column(name = "created")
    @NotNull
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<WorkoutSet> workoutSets = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "training_block_id")
    @JsonBackReference
    private TrainingBlock trainingBlockId;
}
