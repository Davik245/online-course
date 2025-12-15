-- 1. Таблица teachers
CREATE TABLE teachers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- 2. Таблица students
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    fist_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    registrationDate DATE
);

-- 3. Таблица courses
CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    hours INTEGER,
    price NUMERIC(10,2),
    status VARCHAR(50),
    startDate DATE,
    endDate DATE,
    teacher_id BIGINT,
    CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

-- 4. Таблица enrollments
CREATE TABLE enrollments (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    enrollDate DATE,
    endDate DATE,
    status VARCHAR(50),
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- 5. Таблица payments
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    amount NUMERIC(10,2),
    paymentDate DATE,
    status VARCHAR(50),
    CONSTRAINT fk_payment_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_payment_course FOREIGN KEY (course_id) REFERENCES courses(id)
);
