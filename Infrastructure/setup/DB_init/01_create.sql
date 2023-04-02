--  INIT module users and their schemas
--  TODO prehodit toto niekam mimo infrastructure (projekt so scripts na git checkout, build, deploy, DB setup, ...)
-- TEMPLATE (add module name after each '_'):
-- CREATE USER listng_ WITH PASSWORD 'a';
-- CREATE SCHEMA list_ AUTHORIZATION listng_;
-- ALTER USER listng_ set SEARCH_PATH=list_;

CREATE USER listng_user WITH PASSWORD 'a';
CREATE SCHEMA list_user AUTHORIZATION listng_user;
ALTER USER listng_user set SEARCH_PATH=list_user;

CREATE USER listng_course WITH PASSWORD 'a';
CREATE SCHEMA list_course AUTHORIZATION listng_course;
ALTER USER listng_course set SEARCH_PATH=list_course;

CREATE USER listng_task WITH PASSWORD 'a';
CREATE SCHEMA list_task AUTHORIZATION listng_task;
ALTER USER listng_task set SEARCH_PATH=list_task;

CREATE USER listng_solution WITH PASSWORD 'a';
CREATE SCHEMA list_solution AUTHORIZATION listng_solution;
ALTER USER listng_solution set SEARCH_PATH=list_solution;

-- CREATE USER listng_log WITH PASSWORD 'a';
-- CREATE SCHEMA list_log AUTHORIZATION listng_log;
-- ALTER USER listng_log set SEARCH_PATH=list_log;

-- CREATE USER listng_test WITH PASSWORD 'a';
-- CREATE SCHEMA list_test AUTHORIZATION listng_test;
-- ALTER USER listng_test set SEARCH_PATH=list_test;

