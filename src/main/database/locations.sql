

# here will be locations table for Savele trading
# not yet finished..
create table locations(
    id int primary key auto_increment,
    name varchar(64) not null unique,
    numStudents int
);