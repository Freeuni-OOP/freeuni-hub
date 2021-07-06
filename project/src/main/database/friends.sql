drop table if exists friends;

create table friends(
	id int primary key auto_increment,
    requester_id int not null,
    receiver_id int not null,
    connection_date date,
    constraint foreign key(requester_id) references usersTable(id) on delete restrict on update cascade,
    constraint foreign key(receiver_id) references usersTable(id) on delete restrict on update cascade
)