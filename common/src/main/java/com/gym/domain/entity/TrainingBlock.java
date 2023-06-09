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
        "programId", "workouts"
})
@ToString(exclude = {
        "programId", "workouts"
})

@Entity
@Table(name = "training_block")
public class TrainingBlock {

    @Id
    @Column(name = "training_block_id")
    @GeneratedValue(generator = "training_block_training_block_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "training_block_training_block_id_seq",
            sequenceName = "training_block_training_block_id_seq", allocationSize = 1, initialValue = 100)
    private Long blockId;

    @Positive
    @NotNull
    @Column(name = "num_week")
    private int numWeek;

    @NotNull
    @Column(name = "important_points_block")
    private String importantPointsBlock;

    @NotNull
    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @NotNull
    @Column(name = "actively")
    private boolean actively;

    @OneToMany(mappedBy = "trainingBlockId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<Workout> workouts = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "parameter_target_id")
    @JsonBackReference
    private LProgram programId;
}
