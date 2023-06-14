drop table if exists PERIOD cascade;
drop table if exists COURSE_GROUP cascade;
drop table if exists ROOM cascade;
drop table if exists CLASS cascade;
drop table if exists COURSE cascade;

CREATE TABLE PERIOD (
        period_id BIGSERIAL PRIMARY KEY,
        name_sk VARCHAR(128),
        name_en VARCHAR(128),
        period_start date,
        period_end date,
        is_active bool
);

CREATE TABLE COURSE (
        course_id BIGSERIAL PRIMARY KEY,
        name_sk VARCHAR(128),
        name_en VARCHAR(128),
        abbreviation_sk VARCHAR(128),
        abbreviation_en VARCHAR(128),
        period_id INTEGER NOT NULL REFERENCES PERIOD(period_id) ON DELETE CASCADE 
);

CREATE TABLE ROOM (
        room_id BIGSERIAL PRIMARY KEY,
        name VARCHAR(32),
        capacity INTEGER
);

CREATE TABLE COURSE_GROUP (
        group_id BIGSERIAL PRIMARY KEY,
        course_id INTEGER NOT NULL REFERENCES COURSE(course_id) ON DELETE CASCADE,
        name_sk VARCHAR(32),
        name_en VARCHAR(32),
        capacity INTEGER,
        is_default bool default false
);

CREATE TABLE CLASS (
        class_id BIGSERIAL PRIMARY KEY,
        room_id INTEGER NOT NULL REFERENCES ROOM(room_id) ON DELETE CASCADE ,
        group_id INTEGER NOT NULL REFERENCES COURSE_GROUP(group_id) ON DELETE CASCADE ,
        day INTEGER NOT NULL,
        time TIME NOT NULL,
        duration INTEGER DEFAULT 45
);
