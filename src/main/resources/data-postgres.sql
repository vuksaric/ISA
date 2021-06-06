INSERT INTO public.address( latitude, longitude, state, street, town) VALUES
(45.254287003813374, 19.84158883404359, 'Serbia', 'Jevrejska 1', 'Novi Sad'),
(45.26323920925486, 19.83130254431588, 'Serbia', 'Bulevar Oslobodjenja 11', 'Novi Sad'),
(45.2440687683819, 19.81898351363017, 'Serbia', 'Bulevar Evrope 23', 'Novi Sad'),
(45.25395107570744, 19.802236269452237, 'Serbia', 'Kace Dejanovic 6', 'Novi Sad'),
(44.81255349229934, 20.460405496427786, 'Serbia', 'Balkanska 5', 'Belgrade'),
(44.82077366154073, 20.421397713620234, 'Serbia', 'Bulevar Mihajla Pupina 16a', 'Belgrade'),
(44.8174368271364, 20.476002471291444, 'Serbia', 'Bulevar Despota Stefana 91', 'Belgrade'),
(44.821385880215544, 20.468061069441973, 'Serbia', 'Zorza Klemensoa 3', 'Belgrade'),
(44.660476298229746, 20.926456398273817, 'Serbia', 'Prote Mateje Nenadovica 10', 'Smederevo'),
(44.66043467323015, 20.93197706943819, 'Serbia', 'Sindjeliceva 21', 'Smederevo'),
(44.65635781979519, 20.930712354095412, 'Serbia', 'Mice Stojkovica 2', 'Smederevo'),
(43.31672013986419, 21.88976179950783, 'Serbia', 'Toplicina 3', 'Nis'),
(43.318856040676536, 21.88583096392567, 'Serbia', 'Drainceva 30', 'Nis'),
(43.31970715388286, 21.89633644183655, 'Serbia', 'Svetozara Markovica 16', 'Nis'),
(43.31599875583015, 21.896939477418705, 'Serbia', 'Ucitelj Tasina 19', 'Nis'),
(44.01214219802794, 20.903180409902316, 'Serbia', 'Bozane Prpic 12', 'Kragujevac'),
(44.01425040075846, 20.893912084765855, 'Serbia', 'Sutjeska 7', 'Kragujevac'),
(44.012481348642154, 20.90474216942325, 'Serbia', 'Cice od Romanije', 'Kragujevac'),
(44.01308668284171, 20.907610498258872, 'Serbia', 'Srpskih dobrovoljaca 9', 'Kragujevac');

--sve sifre 123
INSERT INTO public.user_entity(activated, date_of_birth, email, gender, name, password, password_changed, phone, surname, user_type, address_id) VALUES
(false, '1990-05-03', 'ppacijentovic@gmail.com', 1, 'Petra', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38165299485', 'Petrovic', 4, 1),
(false, '1995-07-13', 'milica@gmail.com', 1, 'Milica', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38164589624', 'Milicic', 4, 5),
(false, '1980-10-04', 'stefan@gmail.com', 0, 'Stefan', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38162215695', 'Markovic', 4, 9),
(false, '1960-05-05', 'petar@gmail.com', 0, 'Petar', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38165339485', 'Stefanovic', 4, 13),
(false, '1997-05-13', 'eva@gmail.com', 1, 'Eva', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38165299485', 'Peric', 4, 17),
(false, '1991-09-23', 'nikola@gmail.com', 0, 'Nikola', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', true, '+38165299485', 'Jovanovic', 4, 19),
(false, '1995-05-23', 'dermatolog', 0, 'Milan', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Urban', 1, 1),
(false, '1992-10-15', 'lejla@gmail.com', 1, 'Lejla', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299225', 'Maric', 1, 2),
(false, '1991-01-11', 'ana@gmail.com', 1, 'Ana', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299411', 'Djuric', 1, 6),
(false, '1990-05-03', 'ivan@gmail.com', 0, 'Ivan', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38163499485', 'Ivanovic', 1, 11),
(false, '1990-05-03', 'aapotekarovic@gmail.com', 1, 'Srdjana', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165233485', 'Golubovic', 0, 12),
(false, '1990-05-03', 'vukasin@gmail.com', 0, 'Vukasin', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Vukasinovic', 0, 1),
(false, '1990-05-03', 'jovo@gmail.com', 0, 'Jovo', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Saric', 0, 1),
(false, '1990-05-03', 'pera@gmail.com', 0, 'Pera', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Peric', 0, 3),
(false, '1990-05-03', 'sava@gmail.com', 0, 'Sava', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Marinkovic', 0, 3),
(false, '1990-05-03', 'mira@gmail.com', 1, 'Mira', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Milosevic', 0, 3),
(false, '1990-05-03', 'suzana@gmail.com', 1, 'Suzana', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Savic', 0, 2),
(false, '1990-05-03', 'jovana@gmail.com', 1, 'Jovana', '$2a$10$2Wnb5XikAHuZH/zNOTI8guCilSoqZNOqJccQapXBd4N1pX9RCYFuO', false, '+38165299485', 'Jelic', 0, 5);

INSERT INTO public.patient_chart(id) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

INSERT INTO public.patient(loyalty_points, patient_category, patient_chart_id, user_id) VALUES
(0, 0, 1, 1),
(0, 1, 2, 2),
(0, 2, 3, 3),
(0, 1, 4, 4),
(0, 2, 5, 5),
(0, 0, 6, 6);

INSERT INTO public.dermatologist(mark, user_id) VALUES
( 5, 7),
( 9, 8),
( 7, 9),
( 6, 10);

INSERT INTO public.pharmacy(consultation_price, description, mark, name, address_id) VALUES
( 1800, 'pharmacy description', 10, 'Apoteka Benu', 2),
( 2300, 'pharmacy description', 8, 'Apoteka Jankovic', 9),
( 1650, 'pharmacy description', 4, 'Apoteka GalenPharm', 8);

INSERT INTO public.medicine(issuing_mode, manufacturer, name, notes, points, shape, therapy_per_day, type) VALUES
(1, 'SANOFI PASTEUR - MARCY L´ETOILE - Francuska', 'Ibuprofen', 'medicine notes', 10, 'pills', 0, 'antiseptic'),
(0, 'DEUTSCHE HOMOOPATHIE-UNION DHU-ARZNEIMITTEL GMBH + CO. KG - Nemačka', 'Midol', 'medicine notes', 5, 'tablets',0, 'antibiotic'),
(0, 'MERCK SHARP & DOHME B.V. - Holandija', 'Brufen', 'medicine notes', 15, 'powder',0, 'analgetic'),
(1, 'GLAXO WELLCOME OPERATIONS - Velika Britanija', 'Andol', 'medicine notes', 10, 'pills', 0, 'analgetic'),
(1, 'JANSSEN CILAG S.P.A. - Italija', 'Aspirin', 'medicine notes', 15, 'gel', 0, 'antibiotic');

INSERT INTO public.medicine_quantity(quantity, medicine_id, supplier_id, pharmacy_id, order_list_id, erecipe_id) VALUES
( 11, 3, null, 1, null, null),
( 7, 2, null, 2, null, null),
( 8, 1, null, 3, null, null),
( 4, 5, null, 1, null, null),
( 10, 4, null, 2, null, null),
( 22, 3, null, 3, null, null),
( 15, 2, null, 1, null, null),
( 11, 1, null, 2, null, null);

INSERT INTO public.replacements (medicine_id, replacement) VALUES
(5, 3),
(5, 1),
(3, 1),
(3, 5),
(1, 3),
(1, 5);

INSERT INTO public.working_hours ( end_time, start_time, pharmacy_id, dermatologist_id) VALUES
('16:00','08:00', 1, 1),
('14:00','09:00', 1, 2),
('20:00','18:00', 2, 2),
('16:00','08:00', 3, 3),
('22:00','16:00', 3, 4),
('14:00','09:00', 1, 4),
('22:00','18:00', 2, 3),
('16:00', '08:00', 1, null),
('18:00', '10:00', 2, null),
('22:00', '16:00', 3, null);

INSERT INTO public.pharmacist(mark, pharmacy_id, user_id, working_hours_id, pharmacy_list_id) VALUES
( 5, 1, 11, 8, 1),
( 5, 2, 12, 9, 2),
( 5, 3, 13, 10, 3);

INSERT INTO public.workday_dermatologist(date, dermatologist_id) VALUES
('2021-05-28', 1),--1
('2021-06-01', 1),--2
('2021-06-07', 1),--3
('2021-06-08', 1),--4
('2021-06-09', 1),--5
('2021-06-10', 1),
('2021-06-11', 1),
('2021-06-12', 1),
('2021-06-13', 1),
('2021-06-14', 1),
('2021-06-15', 1),
('2021-06-16', 1),
('2021-06-17', 1),
('2021-06-18', 1),
('2021-06-19', 1),
('2021-06-20', 1),
('2021-06-21', 1),
('2021-06-22', 1),
('2021-06-23', 1),
('2021-06-24', 1),
('2021-06-25', 1),
('2021-06-26', 1),
('2021-06-27', 1),
('2021-06-28', 1),
('2021-06-29', 1),
('2021-06-30', 1),
('2021-05-28', 2),
('2021-06-01', 2),
('2021-06-07', 2),
('2021-06-08', 2),
('2021-06-09', 2),
('2021-06-10', 2),
('2021-06-11', 2),
('2021-06-12', 2),
('2021-06-13', 2),
('2021-06-14', 2),
('2021-06-15', 2),
('2021-06-16', 2),
('2021-06-17', 2),
('2021-06-18', 2),
('2021-06-19', 2),
('2021-06-20', 2),
('2021-06-21', 2),
('2021-06-22', 2),
('2021-06-23', 2),
('2021-06-24', 2),
('2021-06-25', 2),
('2021-06-26', 2),
('2021-06-27', 2),
('2021-06-28', 2),
('2021-06-29', 2),
('2021-06-30', 2),
('2021-05-28', 3),
('2021-06-01', 3),
('2021-06-07', 3),
('2021-06-08', 3),
('2021-06-09', 3),
('2021-06-10', 3),
('2021-06-11', 3),
('2021-06-12', 3),
('2021-06-13', 3),
('2021-06-14', 3),
('2021-06-15', 3),
('2021-06-16', 3),
('2021-06-17', 3),
('2021-06-18', 3),
('2021-06-19', 3),
('2021-06-20', 3),
('2021-06-21', 3),
('2021-06-22', 3),
('2021-06-23', 3),
('2021-06-24', 3),
('2021-06-25', 3),
('2021-06-26', 3),
('2021-06-27', 3),
('2021-06-28', 3),
('2021-06-29', 3),
('2021-06-30', 3),
('2021-05-28', 4),
('2021-06-01', 4),
('2021-06-07', 4),
('2021-06-08', 4),
('2021-06-09', 4),
('2021-06-10', 4),
('2021-06-11', 4),
('2021-06-12', 4),
('2021-06-13', 4),
('2021-06-14', 4),
('2021-06-15', 4),
('2021-06-16', 4),
('2021-06-17', 4),
('2021-06-18', 4),
('2021-06-19', 4),
('2021-06-20', 4),
('2021-06-21', 4),
('2021-06-22', 4),
('2021-06-23', 4),
('2021-06-24', 4),
('2021-06-25', 4),
('2021-06-26', 4),
('2021-06-27', 4),
('2021-06-28', 4),
('2021-06-29', 4),
('2021-06-30', 4);

INSERT INTO public.workday_pharmacist(date, pharmacist_id) VALUES
('2021-06-01', 1),
('2021-05-28', 1),
('2021-06-07', 1),
('2021-06-08', 1),
('2021-06-09', 1),
('2021-06-10', 1),
('2021-06-11', 1),
('2021-06-12', 1),
('2021-06-13', 1),
('2021-06-14', 1),
('2021-06-15', 1),
('2021-06-16', 1),
('2021-06-17', 1),
('2021-06-18', 1),
('2021-06-19', 1),
('2021-06-20', 1),
('2021-06-21', 1),
('2021-06-22', 1),
('2021-06-23', 1),
('2021-06-24', 1),
('2021-06-25', 1),
('2021-06-26', 1),
('2021-06-27', 1),
('2021-06-28', 1),
('2021-06-29', 1),
('2021-06-30', 1),
('2021-05-28', 2),
('2021-06-01', 2),
('2021-06-07', 2),
('2021-06-08', 2),
('2021-06-09', 2),
('2021-06-10', 2),
('2021-06-11', 2),
('2021-06-12', 2),
('2021-06-13', 2),
('2021-06-14', 2),
('2021-06-15', 2),
('2021-06-16', 2),
('2021-06-17', 2),
('2021-06-18', 2),
('2021-06-19', 2),
('2021-06-20', 2),
('2021-06-21', 2),
('2021-06-22', 2),
('2021-06-23', 2),
('2021-06-24', 2),
('2021-06-25', 2),
('2021-06-26', 2),
('2021-06-27', 2),
('2021-06-28', 2),
('2021-06-29', 2),
('2021-06-30', 2),
('2021-05-28', 3),
('2021-06-01', 3),
('2021-06-07', 3),
('2021-06-08', 3),
('2021-06-09', 3),
('2021-06-10', 3),
('2021-06-11', 3),
('2021-06-12', 3),
('2021-06-13', 3),
('2021-06-14', 3),
('2021-06-15', 3),
('2021-06-16', 3),
('2021-06-17', 3),
('2021-06-18', 3),
('2021-06-19', 3),
('2021-06-20', 3),
('2021-06-21', 3),
('2021-06-22', 3),
('2021-06-23', 3),
('2021-06-24', 3),
('2021-06-25', 3),
('2021-06-26', 3),
('2021-06-27', 3),
('2021-06-28', 3),
('2021-06-29', 3),
('2021-06-30', 3);

INSERT INTO public.therapy(duration, medicine_id) VALUES
( 2, 1),
( 1, 2),
( 4, 3),
( 2, 5);

INSERT INTO public.report(information, therapy_id) VALUES
( 'appointment information', 1),
( 'appointment information', 2),
( 'appointment information', 3),
( 'appointment information', 4);

INSERT INTO public.period(end_date, start_date, workday_dermatologist_id) VALUES
('2021-06-01 11:30', '2021-06-01 11:00', null ), --dermatolog 1 pharmacy 1 PRETHODNE
('2021-05-28 19:30', '2021-05-28 19:00',null ),--dermatolog 3 pharmacy 2
('2021-05-28 13:30', '2021-05-28 13:00', null), --dermatolog 4 pharmacy 1
('2021-06-01 11:30', '2021-06-01 11:00', null), --dermatolog 3 pharmacy 3
('2021-06-28 11:30', '2021-06-28 11:00', null), --dermatolog 1 pharmacy 1 BUDUCE
('2021-06-28 19:30', '2021-06-28 19:00', null), --dermatolog 3 pharmacy 2
('2021-06-27 13:30', '2021-06-27 13:00', null), --dermatolog 4 pharmacy 1
('2021-06-29 11:30', '2021-06-29 11:00', null), --dermatolog 3 pharmacy 3
('2021-06-29 12:30', '2021-06-29 12:00', null); --dermatolog 3 pharmacy 3

INSERT INTO public.consultation(done, patient_id, period_id, pharmacist_id, pharmacy_id, report_id, workday_pharmacist_id
                               , patient_chart_id, patient_chart_future_id) VALUES
( true, 1, 1, 1, 1, 1, 1, 1, null ),
( true, 1, 2, 3, 3, 2, 53, 1, null ),
( true, 2, 3, 2, 2, 3, 27, 2, null ),
( false, 1, 5, 1, 1, null, 1, null, 1 ),
( false, 1, 6, 3, 3, null, 76, null, 1 ),
( false, 2, 7, 2, 2, null, 49, null, 2 ),
( false, 1, 8, 2, 2, null, 51, null, 1 );


INSERT INTO public.examination(diagnosis, done, free, price, date_id, dermatologist_id, patient_id,
pharmacy_id, report_id, workday_dermatologist_id, patient_chart_id, patient_chart_future_id) VALUES
('diagnosis', true, false, 1500, 1, 1, 1, 1, 1, 2, 1, null),
('diagnosis', true, false, 1700, 2, 3, 2, 2, 2, 53, 2, null),
('diagnosis', true, false, 2100, 3, 4, 1, 1, 3, 79, 1, null),
('diagnosis', true, false, 2500, 4, 3, 1, 3, 4, 54, 1, null),
('diagnosis', false, false, 1500, 5, 1, 1, 1, null, 24, null, 1),
('diagnosis', false, false, 1900, 6, 3, 1, 2, null, 76, null, 1),
('diagnosis', false, true, 2300, 7, 4, null, 1, null, 101, null, null),
('diagnosis', false, true, 2300, 9, 3, null, 3, null, 77, null, null),
('diagnosis', false, true, 1700, 8, 3, null, 3, null, 77, null, null);

INSERT INTO public.reservation(canceled, due_date, issued, penalty, serial_number, medicine_id, patient_id, pharmacy_id, patient_chart_id) VALUES
(false, '2021-06-30', false, false, '15lky', 1, 1, 1, 1),
(false, '2021-06-20', false, false, 'slo55', 2, 1, 2, 1),
(false, '2021-06-18', false, false, 'qwe4', 3, 1, 3, 1),
(false, '2021-06-22', false, false, '586ft', 4, 1, 2, 1),
(false, '2021-06-27', false, false, '12gy4', 5, 1, 3, 2),
(false, '2021-06-26', false, false, '123ff', 2, 1, 1, 1);

INSERT INTO public.ingredient(name, medicine_id) VALUES
('ingredient 1', 1),
('ingredient 1', 2),
('ingredient 2', 2),
('ingredient 3', 3),
('ingredient 1', 3),
('ingredient 2', 2),
('ingredient 1', 4),
('ingredient 1', 5),
('ingredient 2', 5);

INSERT INTO public.erecipe(date, e_recipe_status, patient_id, pharmacy_id, patient_chart_id) VALUES
('2021-05-28', 0, 1, 1, 1),
('2021-05-22', 1, 1, 2, 1),
('2021-05-27', 1, 1, 3, 1),
('2021-05-28', 0, 1, 1, 1),
('2021-05-23', 0, 1, 1, 1),
('2021-05-21', 2, 1, 2, 1);

INSERT INTO public.medicine_quantity(quantity, medicine_id, supplier_id, pharmacy_id, order_list_id, erecipe_id) VALUES
( 2, 3, null, null, null, 1),
( 3, 2, null, null, null, 1),
( 1, 1, null, null, null, 2),
( 2, 5, null, null, null, 3),
( 1, 4, null, null, null, 4),
( 2, 3, null, null, null, 5),
( 2, 2, null, null, null, 6),
( 1, 1, null, null, null, 2);

INSERT INTO public.pricelist_medicine( price, medicine_id, pharmacy_id, validity_id) VALUES
( 250, 3, 1, null),
( 250, 5, 1, null),
( 350, 2, 1, null),
( 280, 2, 2, null),
( 260, 4, 2, null),
( 320, 1, 2, null),
( 280, 1, 3, null),
( 250, 3, 3, null);

INSERT INTO public.vacation(end_date, pharmacy_id, start_date, user_id, user_type, pharmacist_vacation_id, dermatologist_vacation_id) VALUES
('2021-06-20', 1, '2021-06-16', 1, 0, 1, null ),
('2021-06-20', 1, '2021-06-16', 1, 1, null, 1 ),
('2021-06-20', 1, '2021-06-17', 2, 0, 1, null );

INSERT INTO public.pharmacy_patient(pharmacy_id, patient_id) VALUES
(1, 1),
(2, 1),
(1, 2),
(3, 2),
(3, 3),
(2, 3);

INSERT INTO public.penalty_points(patient_id, date) VALUES
(1, '2021-06-03'),
(2, '2021-06-03'),
(2, '2021-06-02');














