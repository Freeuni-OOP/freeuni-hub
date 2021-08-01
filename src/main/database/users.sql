
create table users(
	id int primary key auto_increment,
    first_name varchar(64) not null,
    last_name varchar(64) not null,
    user_name varchar(64) unique,
    email varchar(64) unique,
    password  varchar(300) not null
);
