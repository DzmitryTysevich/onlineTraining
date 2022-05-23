-- DML
INSERT INTO COURSE (name) VALUES ('MATH');
INSERT INTO COURSE (name) VALUES ('BIOLOGY');
INSERT INTO COURSE (name) VALUES ('ENGLISH');
INSERT INTO COURSE (name) VALUES ('CHEMISTRY');
INSERT INTO USERS (role, firstname, lastname, email, password, course, assessment, review) VALUES ('STUDENT', 'DZMITRY', 'TYSEVICH', 'DZTYSEVICH@GMAIL.COM', 1234, 2, 8, 'GOOD JOB');
INSERT INTO USERS (role, firstname, lastname, email, password, course, assessment, review) VALUES ('STUDENT', 'RAMAN', 'RAMANAVICH', 'RAMAN@GMAIL.COM', 1234, 1, 9, 'GOOD JOB');
INSERT INTO USERS (role, firstname, lastname, email, password, course, assessment, review) VALUES ('TEACHER', 'IVAN', 'IVANOU', 'IIVANOU@GMAIL.COM', 1234, 1, NULL, NULL);

COMMIT;
