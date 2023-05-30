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
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "userId"
})
@ToString(exclude = {
        "userId"
})

@Entity
@Table(name = "target_gym")
public class TargetGym {

    @Id
    @Column(name = "target_id")
    @GeneratedValue(generator = "target_gym_target_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "target_gym_target_id_seq",
            sequenceName = "target_gym_target_id_seq", allocationSize = 1, initialValue = 100)
    private Long targetId;

    @Column(name = "target_weight")
    @NotNull
    private double targetWeight;

    @Column(name = "target_fat_percent")
    @NotNull
    private double targetFatPercent;

    @Column(name = "target_bench")
    @NotNull
    private double targetBench;

    @Column(name = "target_squat")
    @NotNull
    private double targetSquat;

    @Column(name = "target_traction")
    @NotNull
    private double targetTraction;

    @JsonIgnore
    @Column(nullable = false)
    private Timestamp created;

    @JsonIgnore
    @Column(nullable = false)
    private Timestamp changed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userId;

}
