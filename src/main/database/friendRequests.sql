create table friendRequests
(
    id           int primary key auto_increment,
    requester_id int     not null,
    receiver_id  int     not null,
    accepted     boolean not null,
    constraint foreign key (requester_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id) references users (id) on delete restrict on update cascade
);