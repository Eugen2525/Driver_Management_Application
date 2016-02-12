DELETE FROM user_roles;
DELETE FROM trips;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password, calories_per_day)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 2005);

-- admin
INSERT INTO users (name, email, password, calories_per_day)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', 1900);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO trips (date_time, description, calories, driver, user_id) VALUES
  ('2015-05-30 10:00:00', 'Офис-МИД', 1,1, 100000),
  ('2015-05-30 13:00:00', 'Офис-Аэропорт', 1,1, 100000),
  ('2015-05-30 20:00:00', 'Офис-ЮНЕСКО', 2,0, 100000),
  ('2015-05-31 10:00:00', 'МИД-Офис', 3, 2,100000),
  ('2015-05-31 13:00:00', 'Аэропорт-Отель Узбекистан-Офис', 2,1, 100000),
  ('2015-05-31 20:00:00', 'ПРООН-Офис', 2,1, 100000),
  ('2015-06-01 14:00:00', 'СДС-Офис', 1, 0,100001),
  ('2015-06-01 21:00:00', 'МинЗдрав-Офис', 4,2, 100001);
