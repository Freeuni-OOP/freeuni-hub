create table groupsInfo(
	id int primary key auto_increment,
    group_name varchar(100) unique,
    admin_id int not null,
    constraint foreign key (admin_id) references users(id) on delete restrict on update cascade
);