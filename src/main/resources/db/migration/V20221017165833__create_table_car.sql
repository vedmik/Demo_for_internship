CREATE TABLE IF NOT EXISTS car (
    car_id int not null auto_increment primary key,
    car_brand varchar(20) not null,
    car_desc varchar(160),
    car_model varchar(20) not null,
    car_year int,
    user_id int
);
