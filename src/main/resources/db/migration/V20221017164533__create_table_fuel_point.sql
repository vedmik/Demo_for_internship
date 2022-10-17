CREATE TABLE IF not exists fuel_point (
   id int not null auto_increment primary key,
   odometer int,
   price int,
   refueling int,
   car_id int
);
