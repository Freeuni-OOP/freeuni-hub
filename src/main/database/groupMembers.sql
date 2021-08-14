create table groupMembers
(
    group_id  int not null,
    member_id int not null,
    constraint foreign key (group_id) references groupsInfo (id) on delete restrict on update cascade,
    constraint foreign key (member_id) references users (id) on delete restrict on update cascade
);