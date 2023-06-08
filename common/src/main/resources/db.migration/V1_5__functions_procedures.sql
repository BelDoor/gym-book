-- INSERT INTO public.users (user_id, first_name, surname, gender, birthday, height, user_password, user_email,
--                           user_phone_number, created, changed, actively)
-- VALUES (2, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000', 165, 'QWERTY23', 'helga@gym.com', 375254568715,
--         '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064', true),
--        (3, 'Helg', 'Rat', 'FEMALE', '1986-12-31 05:26:28.000000', 15, 'QWERTY23', 'helg@gym.com', 375254568716,
--         '2023-05-20 12:28:25.030006', '2023-05-20 12:28:25.032002', true),
--        (16, 'Goga', 'Deper', 'MALE', '1990-03-02 15:13:08.000000', 160, 'QWERTY23', 'goga@gym.com', 375254568719,
--         '2023-05-20 20:40:39.716302', null, true),
--        (17, 'Go', 'Cha', 'MALE', '1986-12-31 05:26:28.000000', 170, 'QWERTY23', 'gocha@gym.com', 375254568729,
--         '2023-05-20 20:59:13.894917', null, true),
--        (18, 'Michel', 'Ci', 'MALE', '1986-12-31 05:26:28.000000', 170, 'QWERTY23', 'Michel@gym.com', 375254568720,
--         '2023-05-20 21:33:35.656801', null, true),
--        (20, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185, 'QWERTY23', 'AliKuk1@gym.com', 375254568723,
--         '2023-05-21 17:18:25.299393', null, true),
--        (21, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185, 'QWERTY23', 'AliKuk1@gym.com', 375254568723,
--         '2023-05-21 17:22:39.952847', null, true),
--        (1, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000', 165, 'QWERTY23', 'helg1@gym.com', 375254568745,
--         '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064', true),
--        (19, 'Alik', 'Kuki', 'MALE', '1996-07-03 11:46:28.000000', 195, 'QWERTY23', 'AliKuk@gym.com', 375254568721,
--         '2023-05-21 00:08:27.517644', '2023-05-21 17:22:45.623482', true);
insert into public.users (user_id, first_name, surname, gender, birthday, height, user_password, user_email,
                          user_phone_number, created, changed, actively)
values (2, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000',
        165, 'QWERTY23', 'helga@gym.com', 375254568715, '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064',
        true),
       (3, 'Helg', 'Rat', 'FEMALE', '1986-12-31 05:26:28.000000', 150,
        'QWERTY23', 'helg@gym.com', 375254568716, '2023-05-20 12:28:25.030006', '2023-05-20 12:28:25.032002', true),
       (16, 'Goga', 'Deper', 'MALE', '1990-03-02 15:13:08.000000', 160,
        'QWERTY23', 'goga@gym.com', 375254568719, '2023-05-20 20:40:39.716302', null, true),
       (17, 'Go', 'Cha', 'MALE', '1986-12-31 05:26:28.000000', 170,
        'QWERTY23', 'gocha@gym.com', 375254568729, '2023-05-20 20:59:13.894917', null, true),
       (18, 'Michel', 'Ci', 'MALE', '1986-12-31 05:26:28.000000', 170,
        'QWERTY23', 'Michel@gym.com', 375254568720, '2023-05-20 21:33:35.656801', null, true),
       (21, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185,
        'QWERTY23', 'AliKuk1@gym.com', 375254568723, '2023-05-21 17:22:39.952847', null, true),
       (1, 'Helga', 'Rat', 'FEMALE', '1990-03-02 15:13:08.000000', 165,
        'QWERTY23', 'helg1@gym.com', 375254568745, '2023-05-20 12:26:39.736980', '2023-05-20 12:26:40.485064', true),
       (19, 'Alik', 'Kuki', 'MALE', '1996-07-03 11:46:28.000000', 195,
        'QWERTY23', 'AliKuk@gym.com', 375254568721, '2023-05-21 00:08:27.517644', '2023-05-21 17:22:45.623482', true),
       (5, 'Baja', 'Jao', 'MALE', '2008-06-03 09:53:39.000000', 169,
        'QWERTY23', 'stewanShops@gym.com', 375299999998, '2023-06-03 15:12:45.275309', '2023-06-03 15:19:47.747622',
        true),
       (7, 'Helga', 'Paklit', 'NOT_SELECTED', '2023-06-03 12:53:39.000000', 185,
        'QWERty23', 'Bonjo2021@gmail.com', 375238297231, '2023-06-05 15:52:34.630092', null, true),
       (20, 'Ali', 'Kuk', 'MALE', '1993-05-03 01:59:48.000000', 185,
        'QWERTY23', 'AliKuk12@gym.com', 375254568724, '2023-05-21 17:18:25.299393', null, true);

INSERT INTO public.roles (role_id, role_name, user_id, created, changed)
VALUES (1, 'ROLE_COACH', 1, '2023-05-18 14:24:20.000000', null),
       (2, 'ROLE_USER', 2, '2023-05-18 14:25:20.000000', null),
       (3, 'ROLE_USER', 1, '2023-05-18 14:25:52.000000', null),
       (10, 'ROLE_COACH', 17, '2023-05-22 16:32:43.509101', '2023-05-22 16:32:43.510099'),
       (11, 'ROLE_COACH', 16, '2023-05-22 16:34:15.314689', '2023-05-22 16:34:15.314689'),
       (12, 'ROLE_MODERATOR', 1, '2023-05-22 16:35:46.673419', '2023-05-22 16:35:46.673419'),
       (13, 'ROLE_MODERATOR', 2, '2023-05-22 16:41:14.461649', '2023-05-22 16:41:14.461649');

INSERT INTO public.parameters_gym (parameter_id, user_id, weight, fat_percent, max_bench, max_squat, max_traction,
                                   created, changed)
VALUES (1, 16, 111, 25, 95, 110, 120, '2023-05-22 23:06:28.000000', null),
       (2, 2, 58, 5, 65, 78, 75, '2023-05-22 23:16:27.000000', null),
       (4, 1, 78, 110, 110, 110, 110, '2023-05-23 14:23:55.905133', null),
       (5, 1, 90, 98, 85, 110, 110, '2023-05-23 14:31:41.611975', null);

INSERT INTO public.target_gym (target_id, user_id, target_weight, target_fat_percent, target_bench,
                               target_squat, target_traction, created, changed)
VALUES (1, 16, 110, 15, 95, 110, 120, '2023-05-22 23:06:28.000000', null),
       (2, 2, 60, 10, 65, 78, 75, '2023-05-22 23:16:27.000000', null),
       (4, 1, 75, 15, 110, 110, 110, '2023-05-23 14:23:55.905133', null),
       (5, 1, 80, 30, 85, 110, 110, '2023-05-23 14:31:41.611975', null);




