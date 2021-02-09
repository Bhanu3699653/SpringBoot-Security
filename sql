CREATE TABLE user (user_id int(11) primary key,username varchar(45) NOT NULL, password varchar(64) NOT NULL,
enabled tinyint(4) DEFAULT 1,role_id int references role(role_id));

create table role(role_id int primary key,name varchar(20));

mysql> insert into role values(1,'USER');

mysql> insert into role values(2,'ADMIN');