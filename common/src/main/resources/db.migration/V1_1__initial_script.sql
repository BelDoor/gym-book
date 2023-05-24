create table public.users
(
    user_id           bigserial
        primary key
        unique,
    first_name        varchar(100) not null,
    surname           varchar(100) not null,
    gender            varchar      not null,
    birthday          timestamp    not null,
    height            integer      not null,
    user_password     varchar(200) not null,
    user_email        varchar(100) not null,
    user_phone_number bigint       not null,
    created           timestamp    not null,
    changed           timestamp,
    actively          boolean      not null
);

alter table public.users
    owner to postgres;

create index users_first_name_surname_index
    on public.users (first_name, surname);

create index users_user_id_index
    on public.users (user_id);

create table public.roles
(
    role_id   bigserial
        primary key,
    role_name varchar(100) not null,
    user_id   bigint       not null
        constraint role_users_user_id_fk
            references public.users
            on update cascade on delete cascade,
    created   timestamp    not null,
    changed   timestamp
);

alter table public.roles
    owner to postgres;

create unique index roles_role_name_user_id_uindex
    on public.roles (role_name, user_id);

create table public.parameters_gym
(
    parameter_id bigserial
        primary key,
    user_id      bigint    not null
        constraint parameters_gym_users_user_id_fk
            references public.users
            on update cascade on delete cascade,
    weight       real      not null,
    fat_percent  real,
    max_bench    real      not null,
    max_squat    real      not null,
    max_traction real      not null,
    created      timestamp not null,
    changed      timestamp
);

alter table public.parameters_gym
    owner to postgres;

create index parameters_gym_user_id_created_index
    on public.parameters_gym (user_id, created);

create table public.target_gym
(
    target_id          bigserial
        primary key
        unique,
    user_id            bigint    not null
        constraint target_gym_users_user_id_fk
            references public.users
            on update cascade on delete cascade,
    target_weight      real      not null,
    target_fat_percent real,
    target_bench       real      not null,
    target_squat       real      not null,
    target_traction    real,
    created            timestamp not null,
    changed            timestamp
);

alter table public.target_gym
    owner to postgres;

create index target_gym_target_id_index
    on public.target_gym (target_id);

create table public.l_program
(
    id_parameter_target bigserial
        primary key
        unique,
    parameter_id        bigint    not null
        constraint l_parameters_target_gym_parameters_gym_parameter_id_fk
            references public.parameters_gym
            on update cascade on delete cascade,
    target_id           bigint    not null
        constraint l_parameters_target_gym_target_gym_target_id_fk
            references public.target_gym,
    data_start          timestamp not null,
    data_end            timestamp,
    gym_program         text,
    created             timestamp not null,
    changed             timestamp,
    actively            boolean   not null
);

alter table public.l_program
    owner to postgres;

create index l_program_id_parameter_target_index
    on public.l_program (id_parameter_target);

create unique index l_program_target_id_parameter_id_uindex
    on public.l_program (target_id, parameter_id);

create table public.training_block
(
    training_block_id      bigserial
        primary key,
    parameter_target_id    bigint    not null
        constraint training_block_l_program_id_parameter_target_fk
            references public.l_program
            on update cascade on delete cascade,
    num_week               integer   not null,
    important_points_block text      not null,
    created                timestamp not null,
    changed                timestamp,
    actively               boolean   not null
);

alter table public.training_block
    owner to postgres;

create table public.c_exercises
(
    exercise_id   bigserial
        primary key,
    exercise_name varchar(100) not null
        unique,
    exercise      text         not null
);

alter table public.c_exercises
    owner to postgres;

create index c_exercises_exercise_name_index
    on public.c_exercises (exercise_name);

create table public.c_muscles
(
    muscle_id   bigserial
        primary key,
    muscle_name varchar(200) not null,
    muscle      text         not null
);

alter table public.c_muscles
    owner to postgres;

create index c_muscles_muscle_name_index
    on public.c_muscles (muscle_name);

create table public.l_muscles_exercises
(
    muscle_id   bigint not null
        constraint l_muscles_exercises_c_muscles_muscle_id_fk
            references public.c_muscles,
    exercise_id bigint not null
        constraint l_muscles_exercises_c_exercises_exercise_id_fk
            references public.c_exercises
            on update cascade on delete cascade
);

alter table public.l_muscles_exercises
    owner to postgres;

create unique index l_muscles_exercises_exercise_id_muscle_id_uindex
    on public.l_muscles_exercises (exercise_id, muscle_id);

create table public.workouts
(
    workout_id       bigserial
        primary key
        unique,
    training_block_id bigint                                                       not null
        constraint workouts_training_block_training_block_id_fk
            references public.training_block
            on update cascade on delete cascade,
    num_day           integer                                                      not null,
    target_workout    text                                                         not null,
    created           timestamp                                                    not null,
    changed           timestamp
);

alter table public.workouts
    owner to postgres;

create table public.workout_set
(
    workout_set_id  bigserial
        primary key,
    workout_id      bigint    not null
        constraint workout_set_workouts_workout_id_fk
            references public.workouts,
    exercise_id     bigint    not null
        constraint workout_set_c_exercises_exercise_id_fk
            references public.c_exercises,
    quantity_set    integer   not null,
    max_repetitions integer   not null,
    min_repetitions integer   not null,
    max_weight      real      not null,
    min_weight      real      not null,
    rest_time       timestamp not null,
    created         timestamp not null,
    changed         timestamp
);

alter table public.workout_set
    owner to postgres;

create index workout_set_workout_id_index
    on public.workout_set (workout_id, exercise_id);

create table public.log_workout
(
    log_workout_id  bigserial
        primary key,
    workout_set_id  bigint
        constraint log_workout_workout_set_workout_set_id_fk
            references public.workout_set,
    log_weight      real      not null,
    log_repetitions integer   not null,
    created         timestamp not null,
    changed         timestamp
);

alter table public.log_workout
    owner to postgres;

create index log_workout_workout_set_id_index
    on public.log_workout (workout_set_id);

