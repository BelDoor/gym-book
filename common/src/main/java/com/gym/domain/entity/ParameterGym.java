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
import javax.validation.constraints.NotEmpty;
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
@Table(name = "parameters_gym")
public class ParameterGym {

    @Id
    @Column(name = "parameter_id")
    @GeneratedValue(generator = "parameters_gym_parameter_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "parameters_gym_parameter_id_seq",
            sequenceName = "parameters_gym_parameter_id_seq", allocationSize = 1, initialValue = 100)
    private Long parameterId;

    @Column(name = "weight")
    @NotNull
    private Double weight;

    @Column(name = "fat_percent")
    @NotNull
    private Double fatPercent;

    @Column(name = "max_bench")
    @NotNull
    private Double maxBench;

    @Column(name = "max_squat")
    @NotNull
    private Double maxSquat;

    @Column(name = "max_traction")
    @NotNull
    private Double maxTraction;

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
