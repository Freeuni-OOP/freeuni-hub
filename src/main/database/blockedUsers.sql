create table blockedUsers(
	id int primary key auto_increment,
    blocker_id int not null,
    blocked_id int not null,
    constraint foreign key (blocker_id) references users(id) on delete restrict on update cascade,
    constraint foreign key (blocked_id) references users(id) on delete restrict on update cascade
);