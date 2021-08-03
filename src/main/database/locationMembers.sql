create table locationMembers(
	location_id int not null,
    user_id int not null,
    constraint foreign key(location_id) references locations(id) on delete restrict on update cascade,
    constraint foreign key(user_id) references users(id) on delete restrict on update cascade
);