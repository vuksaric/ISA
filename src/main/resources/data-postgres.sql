insert into public.address(id,state,street,town,latitude, longitude) values
(1,'Serbia','Karanfilska 1','Sremska Kamenica','45.254287003813374', '19.84158883404359'),
(2,'Serbia','Karanfilska 2','Sremska Kamenica','45.254287003813374', '19.84158883404359'),
(3,'Serbia','Karanfilska 3','Sremska Kamenica','45.254287003813374', '19.84158883404359');

insert into public.user_entity(id,activated,date_of_birth,email,gender,name,password,password_changed,phone,surname,user_type,address_id) values
(1,true,'2011-07-01 06:30:30','vuk.saric12@gmail.com',1,'Admin','$2a$10$9T24I7c/E.WDHB9KLgJRve0pKhBBAi9xcc.Gn/O1TVgSzp8CN3tQa',true,'12345','Admin',3,1),
(2,true,'2011-07-01 06:30:30','vuk.saric122@gmail.com',1,'Dermatolog','dermatolog',true,'12345','Dermatolog',1,1),
(3,true,'2011-07-01 06:30:30','vuk.saric123@gmail.com',1,'Farmaceut','farmaceut',true,'12345','Farmaceut',0,1),
(4,true,'2011-07-01 06:30:30','vuk.saric124@gmail.com',1,'Pacijent','pacijent',true,'12345','Pacijent',4,1),
(5,true,'2011-07-01 06:30:30','vuk.saric125@gmail.com',1,'Dermatolog2','dermatolog',true,'12345','Dermatolog2',1,1),
(6,true,'2011-07-01 06:30:30','vuk.saric126@gmail.com',1,'Dobavljac','dobavljac',true,'12345','Dobavljac',5,1);

insert into public.medicine(id,issuing_mode,manufacturer,name,notes,points,shape, therapy_per_day,type) values
(1,1,'Proizvodjac1','Brufen','beleska1',3,'oblik1',2,'tip1'),
(2,1,'Proizvodjac2','Panklav','beleska2',5,'oblik2',3,'tip2'),
(3,1,'Proizvodjac3','Kafetin','beleska3',2,'oblik3',4,'tip3'),
(4,1,'Proizvodjac3','Proba','Proba',2,'oblik3',4,'tip3');

insert into public.pharmacy(id,consultation_price,description,mark,name,address_id) values
(1,100,'opis1',4.2,'Apoteka1',1);

insert into public.bill(id,date,price,medicine_id,pharmacy_id) values
(1,'2021-07-01 06:30:30',100,1,1),
(2,'2021-07-01 06:30:30',100,1,1),
(3,'2011-05-01 06:30:30',100,1,1);

insert into public.dermatologist(id,mark,user_id) values
(1,3.9,2),
(2,5.0,5);

insert into public.patient_chart(id) values
(1);

insert into public.patient(id,patient_chart_id,loyalty_points,user_id) values
(1,1,0,4);

insert into public.pharmacist(id,mark,pharmacy_id,user_id,pharmacy_list_id) values
(1,4.5,1,3,1);

insert into public.pharmacy_dermatologist(pharmacy_id,dermatologist_id) values
(1,1),
(1,2);

insert into public.period(id,end_date,start_date) values
(1,'2021-06-01 12:00:00','2021-06-01 11:30:00'),
(2,'2021-04-01 12:00:00','2021-04-01 11:30:00'),
(3,'2021-07-01 12:00:00','2021-07-01 11:30:00'),
(4,'2021-06-19 12:00:00','2021-07-01 11:30:00'),
(5,'2021-06-19 14:00:00','2021-07-01 13:30:00'),
(6,'2021-06-10 12:15:00','2021-07-01 11:30:00');

insert into public.workday_dermatologist(id,date,dermatologist_id) values
(1,'2021-06-19',1),
(2,'2021-06-10',2);

insert into public.examination(id,diagnosis,done,free,price,date_id,dermatologist_id,patient_id,pharmacy_id,patient_chart_id,workday_dermatologist_id) values
(1,'dijagnoza1',true,false,150,4,1,1,1,1,1),
(2,'dijagnoza2',true,false,130,6,1,1,1,1,2),
(3,'dijagnoza3',true,false,120,5,1,1,1,1,1);

insert into public.medicine_quantity(id,quantity,medicine_id,pharmacy_id) values
(1,10,1,1),
(2,2,2,1),
(3,0,3,1);

insert into public.working_hours(id,end_time,start_time,pharmacy_id,dermatologist_id) values
(1,'15:00:00','10:30:00',1,1),
(2,'15:00:00','10:30:00',1,2);

insert into public.pharmacy_patient(pharmacy_id,patient_id) values
(1,1);

insert into public.pharmacy_admin(id,pharmacy_id,user_id) values
(1,1,1);

insert into public.order_list(id,admin_id,due_date,pharmacy_id,medicine_id,status) values
(1,1,'2021-06-19 06:30:30',1,1,0);

insert into public.supplier(user_id) values
(6);

insert into public.offer(id,due_date,price,supplier_id,order_list_id) values
(1,'2021-06-25 06:30:30',150.0,1,1),
(2,'2021-06-26 06:30:30',140.0,1,1);

insert into public.medicine_notification(pharmacy_id,medicine_id) values
(1,1),
(1,2);

alter sequence user_entity_id_seq restart with 7;
alter sequence address_id_seq restart with 4;
alter sequence pharmacist_id_seq restart with 2;
alter sequence dermatologist_id_seq restart with 3;
alter sequence period_id_seq restart with 8;
alter sequence examination_id_seq restart with 4;
alter sequence workday_dermatologist_id_seq restart with 3;
alter sequence medicine_quantity_id_seq restart with 4;
alter sequence order_list_id_seq restart with 2;
alter sequence medicine_id_seq restart with 5;