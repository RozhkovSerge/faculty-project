-- CREATE DATABASE project;
CREATE SCHEMA IF NOT EXISTS faculty;
SET search_path TO faculty;

CREATE TABLE IF NOT EXISTS course(
                                  id BIGSERIAL PRIMARY KEY,
                                  course_name VARCHAR(255),
                                  course_description VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS address (
                                    id BIGSERIAL PRIMARY KEY,
                                    city VARCHAR(64),
                                    street VARCHAR(64),
                                    house VARCHAR(64),
                                    apartment VARCHAR(64)
    );

CREATE TABLE IF NOT EXISTS teacher(
                                   id BIGSERIAL PRIMARY KEY,
                                   first_name VARCHAR(128),
                                   last_name VARCHAR(128),
                                   email VARCHAR(64) UNIQUE
                                   address BIGSERIAL REFERENCES address(id),
                                   course BIGSERIAL REFERENCES course(id)
    );

CREATE TABLE IF NOT EXISTS student(
                                   id BIGSERIAL PRIMARY KEY,
                                   first_name VARCHAR(128) NOT NULL ,
                                   last_name VARCHAR(128) NOT NULL,
                                   email VARCHAR(64) UNIQUE
    );



CREATE TABLE IF NOT EXISTS archive(
                                   id BIGSERIAL PRIMARY KEY,
                                   student_id BIGSERIAL REFERENCES faculty.student(id),
                                   course_id BIGSERIAL REFERENCES faculty.course(id),
                                   mark INT,
                                   date_of_exam DATE
    );

CREATE TABLE IF NOT EXISTS faculty.students_courses(
                                                       student_id BIGSERIAL REFERENCES faculty.student(id),
                                                       course_id BIGSERIAL REFERENCES faculty.course(id)
    );

CREATE TABLE IF NOT EXISTS faculty.students_teachers(
                                                       student_id BIGSERIAL REFERENCES faculty.student(id),
                                                       teacher_id BIGSERIAL REFERENCES faculty.teacher(id)
    );
