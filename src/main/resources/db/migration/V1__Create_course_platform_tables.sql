-- V1__Create_course_platform_tables.sql

-- 1. Users (для JWT)
CREATE TABLE users (
                       id              BIGSERIAL PRIMARY KEY,
                       username        VARCHAR(50)  NOT NULL UNIQUE,
                       email           VARCHAR(100) NOT NULL UNIQUE,
                       password        VARCHAR(255) NOT NULL,
                       role            VARCHAR(20)  NOT NULL DEFAULT 'USER',
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Instructors
CREATE TABLE instructors (
                             id              BIGSERIAL PRIMARY KEY,
                             full_name       VARCHAR(100) NOT NULL,
                             bio             TEXT,
                             created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Courses
CREATE TABLE courses (
                         id                  BIGSERIAL PRIMARY KEY,
                         title               VARCHAR(200) NOT NULL,
                         description         TEXT,
                         duration_hours      INTEGER,
                         level               VARCHAR(20),           -- BEGINNER, INTERMEDIATE, ADVANCED
                         published           BOOLEAN DEFAULT FALSE,
                         thumbnail_path      VARCHAR(255),
                         instructor_id       BIGINT,
                         created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_course_instructor FOREIGN KEY (instructor_id)
                             REFERENCES instructors(id) ON DELETE SET NULL
);

-- 4. Students
CREATE TABLE students (
                          id              BIGSERIAL PRIMARY KEY,
                          full_name       VARCHAR(100) NOT NULL,
                          email           VARCHAR(100) UNIQUE NOT NULL,
                          phone           VARCHAR(20),
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Student Profiles
CREATE TABLE student_profiles (
                                  id              BIGSERIAL PRIMARY KEY,
                                  student_id      BIGINT UNIQUE NOT NULL,
                                  university      VARCHAR(100),
                                  specialty       VARCHAR(100),
                                  course_year     INTEGER,
                                  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                  CONSTRAINT fk_profile_student FOREIGN KEY (student_id)
                                      REFERENCES students(id) ON DELETE CASCADE
);

-- 6. Enrollments
CREATE TABLE enrollments (
                             id                  BIGSERIAL PRIMARY KEY,
                             student_id          BIGINT NOT NULL,
                             course_id           BIGINT NOT NULL,
                             enrollment_date     DATE DEFAULT CURRENT_DATE,
                             completion_date     DATE,
                             completed           BOOLEAN DEFAULT FALSE,
                             created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id)
                                 REFERENCES students(id) ON DELETE CASCADE,
                             CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id)
                                 REFERENCES courses(id) ON DELETE CASCADE,
                             CONSTRAINT uk_student_course UNIQUE (student_id, course_id)
);

-- Индексы
CREATE INDEX idx_courses_instructor ON courses(instructor_id);
CREATE INDEX idx_enrollments_student ON enrollments(student_id);
CREATE INDEX idx_enrollments_course ON enrollments(course_id);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_username ON users(username);