create table changeLocationRequest
(
    requester_id          int not null,
    requester_location_id int not null,
    receiver_id           int not null,
    receiver_location_id  int not null,
    constraint foreign key (requester_id)
        references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id)
        references users (id) on delete restrict on update cascade,
    constraint foreign key (requester_location_id)
        references locations (id) on delete restrict on update cascade,
    constraint foreign key (receiver_location_id)
        references locations (id) on delete restrict on update cascade,
    answer                boolean default false
);