CREATE SCHEMA IF NOT EXISTS faculty;
SET search_path TO faculty;

CREATE TABLE IF NOT EXISTS user_roles
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32)
    );

CREATE TABLE IF NOT EXISTS address
(
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(64),
    street VARCHAR(64),
    house VARCHAR(64),
    apartment VARCHAR(64)
    );

CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    email VARCHAR(64) UNIQUE,
    password VARCHAR(64),
    address BIGSERIAL REFERENCES address(id),
    role BIGSERIAL REFERENCES user_roles(id)
    );

-- CREATE TABLE IF NOT EXISTS course
-- (
--     id BIGSERIAL PRIMARY KEY,
--     name VARCHAR(255),
--     teacher_id BIGSERIAL,
--     course_description VARCHAR(255),
--     FOREIGN KEY (teacher_id) REFERENCES teacher(id)
--     );
--
-- CREATE TABLE IF NOT EXISTS address
-- (
--     id BIGSERIAL PRIMARY KEY,
--     city VARCHAR(64),
--     street VARCHAR(64),
--     house VARCHAR(64),
--     apartment VARCHAR(64)
--     );
--
-- CREATE TABLE IF NOT EXISTS teacher
-- (
--     id BIGSERIAL PRIMARY KEY,
--     first_name VARCHAR(128),
--     last_name VARCHAR(128),
--     email VARCHAR(64) UNIQUE,
--     password VARCHAR(64),
--     address BIGSERIAL REFERENCES address(id)
--     );
--
-- CREATE TABLE IF NOT EXISTS student
-- (
--     id BIGSERIAL PRIMARY KEY,
--     first_name VARCHAR(128) NOT NULL ,
--     last_name VARCHAR(128) NOT NULL,
--     email VARCHAR(64) UNIQUE,
--     password VARCHAR(64)
--     );
--
-- CREATE TABLE IF NOT EXISTS archive
-- (
--     id BIGSERIAL PRIMARY KEY,
--     student_id BIGSERIAL REFERENCES student(id),
--     course_id BIGSERIAL REFERENCES course(id),
--     mark INT,
--     date_of_exam DATE
--     );
--
-- CREATE TABLE IF NOT EXISTS students_courses
-- (
--     student_id BIGSERIAL REFERENCES student(id),
--     course_id BIGSERIAL REFERENCES course(id)
--     );
--
-- CREATE TABLE IF NOT EXISTS students_teachers
-- (
--     student_id BIGSERIAL REFERENCES student(id),
--     teacher_id BIGSERIAL REFERENCES teacher(id)
--     );

