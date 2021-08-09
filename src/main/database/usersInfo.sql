create table usersInfo(
	user_id int not null,
    constraint foreign key(user_id) references users(id) on delete restrict on update cascade,
    user_name varchar(100) ,
    user_last_name varchar(100),
    sqesi varchar(100),
    course varchar(100),
    courseNum int,
    image longBlob
);