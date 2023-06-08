-- insert into public.users (user_id, first_name, surname, gender, birthday, height, user_password, user_email,
--                           user_phone_number, created, changed, actively)
-- values (2, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000',
--         165, 'QWERTY23', 'helga@gym.com', 375254568715, '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064',
--         true),
--        (3, 'Helg', 'Rat', 'FEMALE', '1986-12-31 05:26:28.000000', 150,
--         'QWERTY23', 'helg@gym.com', 375254568716, '2023-05-20 12:28:25.030006', '2023-05-20 12:28:25.032002', true),
--        (16, 'Goga', 'Deper', 'MALE', '1990-03-02 15:13:08.000000', 160,
--         'QWERTY23', 'goga@gym.com', 375254568719, '2023-05-20 20:40:39.716302', null, true),
--        (17, 'Go', 'Cha', 'MALE', '1986-12-31 05:26:28.000000', 170,
--         'QWERTY23', 'gocha@gym.com', 375254568729, '2023-05-20 20:59:13.894917', null, true),
--        (18, 'Michel', 'Ci', 'MALE', '1986-12-31 05:26:28.000000', 170,
--         'QWERTY23', 'Michel@gym.com', 375254568720, '2023-05-20 21:33:35.656801', null, true),
--        (21, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185,
--         'QWERTY23', 'AliKuk1@gym.com', 375254568723, '2023-05-21 17:22:39.952847', null, true),
--        (1, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000', 165,
--         'QWERTY23', 'helg1@gym.com', 375254568745, '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064', true),
--        (19, 'Alik', 'Kuki', 'MALE', '1996-07-03 11:46:28.000000', 195,
--         'QWERTY23', 'AliKuk@gym.com', 375254568721, '2023-05-21 00:08:27.517644', '2023-05-21 17:22:45.623482', true),
--        (5, 'Baja', 'Jao', 'MALE', '2008-06-03 09:53:39.000000', 169,
--         'QWERTY23', 'stewanShops@gym.com', 375299999998, '2023-06-03 15:12:45.275309', '2023-06-03 15:19:47.747622',
--         true),
--        (7, 'Helga', 'Paklit', 'NOT_SELECTED', '2023-06-03 12:53:39.000000', 185,
--         'QWERty23', 'Bonjo2021@gmail.com', 375238297231, '2023-06-05 15:52:34.630092', null, true),
--        (20, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185,
--         'QWERTY23', 'AliKuk12@gym.com', 375254568724, '2023-05-21 17:18:25.299393', null, true);

alter table public.users
    add unique (user_email),
    add unique (user_phone_number);

alter table public.workouts
    rename column num_day to num_training;

alter table public.workouts
    add constraint workouts_pk
        unique (training_block_id, num_training);

alter table public.training_block
    add constraint training_block_pk
        unique (parameter_target_id, num_week);

alter table public.log_workout
    add num_set integer not null;

alter table public.log_workout
    add constraint log_workout_pk
        unique (workout_set_id, num_set);

alter table public.l_muscles_exercises
    add id serial;

alter table public.l_muscles_exercises
    add primary key (id);

alter table public.l_muscles_exercises
    add unique (id);

alter table roles
    add constraint roles_pk
        unique (user_id, role_name);







