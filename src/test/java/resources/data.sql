insert into user_account(id, username, email, password, role, enabled) values (nextval('user_accounts_seq'), 'mike', 'mike@gmail.com', 'test1', 'USER', 'TRUE');
insert into user_account(id, username, email, password, role, enabled) values (nextval('user_accounts_seq'), 'John', 'john@gmail.com', 'test2', 'USER', 'TRUE')

insert into body_measure(id, right_bicep, left_bicep, waist, chest, left_thigh, right_thigh, date_of_measure, user_id) values (nextval('body_measure_seq'), 33.5, 33.5, 85, 105, 58, 58.5, '2020-10-12', 1);
insert into body_measure(id, right_bicep, left_bicep, waist, chest, left_thigh, right_thigh, date_of_measure, user_id) values (nextval('body_measure_seq'), 34, 34, 86, 107, 58.5, 59, '2020-11-14', 1);
insert into body_measure(id, right_bicep, left_bicep, waist, chest, left_thigh, right_thigh, date_of_measure, user_id) values (nextval('body_measure_seq'), 35, 35, 91, 109, 59.5, 60, '2020-11-10', 2);

insert into weight_measure(id, weight, date_of_measure, user_id) values (nextval('weight_measure_seq'), 80, '2020-10-14', 1);
insert into weight_measure(id, weight, date_of_measure, user_id) values (nextval('weight_measure_seq'), 81.5, '2020-11-16', 1);
insert into weight_measure(id, weight, date_of_measure, user_id) values (nextval('weight_measure_seq'), 88, '2020-10-10', 2);
insert into weight_measure(id, weight, date_of_measure, user_id) values (nextval('weight_measure_seq'), 89, '2020-11-10', 2)