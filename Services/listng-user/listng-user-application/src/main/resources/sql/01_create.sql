DROP TABLE  IF EXISTS USER_COURSE_PERMISSION;
DROP TABLE  IF EXISTS LIST_USER;

CREATE TABLE LIST_USER (
        user_id SERIAL PRIMARY KEY,
        name VARCHAR(128) UNIQUE,
        email VARCHAR(128),
        password VARCHAR(512),
        role VARCHAR(32)
);

CREATE TABLE USER_COURSE_PERMISSION (
        permission_id BIGSERIAL PRIMARY KEY,
        user_id INTEGER NOT NULL REFERENCES LIST_USER(user_id) ON DELETE CASCADE,
        course_id INTEGER not null,
        course_role VARCHAR(32),
        status VARCHAR(32),
        group_id INTEGER
);
