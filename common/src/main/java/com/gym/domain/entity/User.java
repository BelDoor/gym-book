package com.gym.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gym.domain.column.Gender;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "roles", "parameterGyms"
})
@ToString(exclude = {
        "roles", "parameterGyms"
})

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_user_id_seq",
            sequenceName = "users_user_id_seq", allocationSize = 1, initialValue = 100)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column
    private Timestamp birthday;

    @Column(name = "height")
    private Integer height;

    @Column(name = "user_password")

    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone_number")
//    @NotEmpty
    private Long userPhone;

    @JsonIgnore
    @Column(nullable = false)
    private Timestamp created;

    @JsonIgnore
    @Column(nullable = false)
    private Timestamp changed;

    @JsonIgnore
    @Column(name = "actively", nullable = false)
    private Boolean isActively = true;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<Role> roles = Collections.emptySet();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<ParameterGym> parameterGyms = Collections.emptySet();
}
