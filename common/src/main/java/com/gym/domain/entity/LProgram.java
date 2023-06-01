package com.gym.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gym.domain.entity.parameter.ParameterGym;
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
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {"targetGymId", "parameterGymId", "trainingBlock"})
@ToString(exclude = {"targetGymId", "parameterGymId", "trainingBlock"})

@Entity
@Table(name = "l_program")
public class LProgram {

    @Id
    @Column(name = "id_parameter_target")
    @GeneratedValue(generator = "l_program_id_parameter_target_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "l_program_id_parameter_target_seq",
            sequenceName = "l_program_id_parameter_target_seq", allocationSize = 1, initialValue = 100)
    private Long programId;

    @Column(name = "data_start")
    @NotNull
    private Timestamp dataStart;

    @Column(name = "data_end")
    @NotNull
    private Timestamp dataEnd;

    @Column(name = "gym_program")
    @NotNull
    private String gymProgram;

    @Column(name = "created")
    @NotNull
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "actively")
    @NotNull
    private boolean actively;

    @OneToMany(mappedBy = "programId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<TrainingBlock> trainingBlock = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "target_id")
    @JsonBackReference
    private TargetGym targetGymId;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    @JsonBackReference
    private ParameterGym parameterGymId;

}
