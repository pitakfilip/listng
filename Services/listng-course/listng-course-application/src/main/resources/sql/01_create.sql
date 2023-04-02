CREATE TABLE PERIOD (
        period_id SERIAL PRIMARY KEY,
        name VARCHAR(128),
        start date
);

CREATE TABLE COURSE (
        course_id SERIAL PRIMARY KEY,
        name VARCHAR(128),
        period_id INTEGER NOT NULL REFERENCES PERIOD(period_id) ON DELETE CASCADE 
);

CREATE TABLE ROOM (
        room_id SERIAL PRIMARY KEY,
        name VARCHAR(32),
        capacity INTEGER
);

CREATE TABLE COURSE_GROUP (
        group_id SERIAL PRIMARY KEY,
        name VARCHAR(32),
        course_id INTEGER NOT NULL REFERENCES COURSE(course_id) ON DELETE CASCADE
);

CREATE TABLE CLASS (
        class_id SERIAL PRIMARY KEY,
        room_id INTEGER NOT NULL REFERENCES ROOM(room_id) ON DELETE CASCADE ,
        group_id INTEGER NOT NULL REFERENCES COURSE_GROUP(group_id) ON DELETE CASCADE ,
        day INTEGER NOT NULL,
        time TIME NOT NULL,
        duration INTEGER DEFAULT 45
);
