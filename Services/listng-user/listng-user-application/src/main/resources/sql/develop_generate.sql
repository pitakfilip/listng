-- passwords for all users = 'a', encoded with BCrypt algorithm
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (1, 'Jozef Mrkvicka', 'mrkvicka1@uniba.sk', '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'STUDENT');
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (2, 'Frantisek Stofka', 'stofka1@uniba.sk', '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'STUDENT');
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (3, 'Filomen Filo', 'filo1@uniba.sk', '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'STUDENT');
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (4, 'Jano Frantik', 'frantik1@uniba.sk', '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'TEACHER');
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (5, 'Mikulas Murko', 'murko@uniba.sk', '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'ROOT');
INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (6, 'Dummy Dummy', 'dummy@ddd.sk', '$2a$10$5ewxhR1yrSafkR7na7Re2umJrdd/STLDeTsUIOQibc2w.k4XB6uu2', 'ROOT');

do $$
    BEGIN
        FOR num in 7..250 loop
            INSERT INTO list_user.list_user (user_id, name, email, password, role) VALUES (num, concat('Dummy', num), concat('dummy', num, '@uniba.sk'), '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW', 'STUDENT');
        END loop;
    END;
$$;

INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 1, 'ATTENDEE', 'ACTIVE', 1);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 2, 'ATTENDEE', 'ACTIVE', 1);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 3, 'ATTENDEE', 'ACTIVE', 1);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 4, 'ATTENDEE', 'ACTIVE', 1);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 5, 'ATTENDEE', 'ACTIVE', 1);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (1, 6, 'ATTENDEE', 'PENDING', null);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (4, 3, 'OWNER', null, null);
INSERT INTO list_user.user_course_permission (user_id, course_id, course_role, status, group_id) VALUES (4, 5, 'OWNER', null, null);


UPDATE list_user.list_user SET password = '$2a$10$OjfNkMFiwVwYiKV8Oj59fe/3BSnxyTyTpzaI.3JOxRWkeWuARgfEW';
