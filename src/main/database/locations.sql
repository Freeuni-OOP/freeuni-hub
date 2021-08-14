create table locations
(
    id          int primary key auto_increment,
    name        varchar(300) not null unique,
    numStudents int
);