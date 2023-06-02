package com.gym.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.domain.entity.CExercise;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "exercises"
})
@ToString(exclude = {
        "exercises"
})

@Entity
@Table(name = "c_muscles")
public class CMuscle {

    @Id
    @Column(name = "muscle_id")
    @GeneratedValue(generator = "c_muscles_muscle_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "c_muscles_muscle_id_seq",
            sequenceName = "c_muscles_muscle_id_seq", allocationSize = 1, initialValue = 100)
    private Long muscleId;

    @Column(name = "muscle_name")
    @Size(min = 2, max = 180)
    @NotNull
    private String muscleName;

    @Column(name = "muscle")
    @NotNull
    private String muscleTXT;

    @ManyToMany
    @JoinTable(name = "l_muscles_exercises",
            joinColumns = @JoinColumn(name = "muscle_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )

    @JsonIgnoreProperties("muscle")
    private Set<CExercise> exercises = Collections.emptySet();




}
