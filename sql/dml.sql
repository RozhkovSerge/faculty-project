INSERT INTO faculty.roles (id, name)
VALUES (1, 'student'), (2, 'teacher');

INSERT INTO faculty.address (id, city, street, house, apartment)
VALUES (1, 'Rostov', 'B.Sodovaya', 14, 23),
       (2, 'Magadan', 'Lenina', 32, 12),
       (3, 'St.Peterburg', 'Pushkina', 45, 15);

INSERT INTO faculty.course (id, name, description)
VALUES  (1, 'Math', 'Math course'),
        (2, 'Physics', 'Physics course'),
        (3, 'Biology', 'Biology course');

INSERT INTO faculty.users (id, first_name, last_name, email, password, address, role)
VALUES  (1, 'Bob', 'Ivanov', 'bob@mail.ru', '123', 1, 1),
        (2, 'Sam', 'Petrov', 'sam@mail.ru', '456', 2, 2),
        (3, 'Maria', 'Sidorov', 'maria@mail.ru', '789', 3, 1);

INSERT INTO faculty.archive (id, user_id, course_id, mark, date_of_exam)
VALUES  (1, 1, 1, 5, '01-01-2022'),
        (2, 3, 3, 4, '11-01-2022');

INSERT INTO faculty.users_courses (user_id, course_id)
VALUES (1, 1),
       (2,3);

