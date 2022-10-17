CREATE TABLE  IF NOT EXISTS user (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username varchar(20) not null,
    password varchar(50) not null,
    active boolean not null
);
