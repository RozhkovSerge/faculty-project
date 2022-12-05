CREATE SCHEMA IF NOT EXISTS faculty;
SET search_path TO faculty;

CREATE TABLE IF NOT EXISTS roles
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
    role BIGSERIAL REFERENCES roles(id)
    );

CREATE TABLE IF NOT EXISTS course
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
    );


CREATE TABLE IF NOT EXISTS archive
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL REFERENCES users(id),
    course_id BIGSERIAL REFERENCES course(id),
    mark INT,
    date_of_exam DATE
    );

CREATE TABLE IF NOT EXISTS users_courses
(
    user_id BIGSERIAL REFERENCES users(id),
    course_id BIGSERIAL REFERENCES course(id)
    );

