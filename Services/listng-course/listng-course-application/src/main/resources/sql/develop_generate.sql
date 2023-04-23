INSERT INTO list_course.period (period_id, name_sk, name_en, period_start, period_end, is_active) VALUES (1, 'Zim. Sem. 2022/2023', 'Zim. Sem. 2022/2023 EN', '2022-09-20', '2023-02-19', false);
INSERT INTO list_course.period (period_id, name_sk, name_en, period_start, period_end, is_active) VALUES (2, 'Let. Sem. 2022/2023', 'Let. Sem. 2022/2023 EN', '2023-02-20', '2023-07-01', true);

INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (1, 'dummy predmet 1', 'dummy course EN', 'dummy1', 'dummy1 EN', 1);
INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (2, 'dummy predmet 2', 'dummy course 2 EN', 'dummy2', 'dummy2 EN', 1);
INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (3, 'dummy predmet 3', 'dummy course 3 EN', 'dummy3', 'dummy3 EN', 2);
INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (4, 'dummy predmet 4', 'dummy course 4 EN', 'dummy4', 'dummy4 EN', 2);
INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (5, 'dummy predmet 5', 'dummy course 5 EN', 'dummy5', 'dummy5 EN', 2);
INSERT INTO list_course.course (course_id, name_sk, name_en, abbreviation_sk, abbreviation_en, period_id) VALUES (6, 'dummy predmet 6', 'dummy course 6 EN', 'dummy6', 'dummy6 EN', 2);

INSERT INTO list_course.room (room_id, name, capacity) VALUES (1, 'H3', 20);
INSERT INTO list_course.room (room_id, name, capacity) VALUES (2, 'H6', 45);
INSERT INTO list_course.room (room_id, name, capacity) VALUES (3, 'M-III', 20);

INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (5, 'VSETCI', 'ALL', 5);
INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (6, 'VSETCI', 'ALL', 6);
INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (3, 'KRUZOK1', 'GROUP1', 4);
INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (1, 'KRUZOK1', 'GROUP1', 3);
INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (4, 'KRUZOK2', 'GROUP2', 4);
INSERT INTO list_course.course_group (group_id, name_sk, name_en, course_id) VALUES (2, 'KRUZOK2', 'GROUP2', 3);

INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (1, 1, 1, 1, '08:50:00', 45);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (2, 1, 1, 3, '08:50:00', 90);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (3, 1, 2, 0, '08:50:00', 45);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (4, 1, 2, 2, '08:50:00', 90);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (5, 2, 3, 3, '08:50:00', 45);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (6, 2, 4, 4, '13:10:00', 45);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (7, 3, 5, 2, '08:50:00', 135);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (8, 3, 6, 1, '08:50:00', 135);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (9, 2, 3, 0, '13:10:00', 45);
INSERT INTO list_course.class (class_id, room_id, group_id, day, time, duration) VALUES (10, 1, 4, 2, '13:10:00', 45);
