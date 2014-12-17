# --- Sample dataset

# --- !Ups

INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (1, 'Богданов Александр Владимирович', 5, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (2, 'Бугаева Екатерина Викторовна', 3, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (3, 'Грибков Иван Вячеславович', 1, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (4, 'Корсун Денис Дмитриевич', 0, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (5, 'Костиков Константин Александрович', 0, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (6, 'Островский Денис Дмитриевич', 7, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (7, 'Паневин Андрей Викторович', 2, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (8, 'Пикалов Николай Алексеевич', 0, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (9, 'Решетников Александр Дмитриевич', 5, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (10, 'Трегубов Алексей Геннадьевич', 1, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (11, 'Чернышев Антон Владиславович', 0, '2011-09-01', null, 1);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (13, 'Шутенко Ольга Валерьевна', 6, '2011-09-01', null, 1);

INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (14, 'Андрусенко Галина Андреевна', 1, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (18, 'Аралова Алёна Александровна ', 5, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (19, 'Атаулин Хасан Русланович', 0, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (20, 'Давтян Каринэ Викторовна', 12, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (21, 'Коновалова Мария Александровна', 0, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (22, 'Ленивкин Никита Алексеевич', 8, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (24, 'Лесняк Антон Алексеевич', 2, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (25, 'Лысенко Мария Анатольевна', 0, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (26, 'Плотникова Анна Андреевна', 5, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (30, 'Семенов Александр Анатольевич', 4, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (31, 'Фролова Евгения Эдуардовна', 2, '2011-09-01', null, 2);
INSERT INTO student (id, name, debts, introduced, discontinued, department_id) VALUES (32, 'Яковлев Александр Алексеевич', 1, '2011-09-01', null, 2);

# --- !Downs

DELETE FROM student;
