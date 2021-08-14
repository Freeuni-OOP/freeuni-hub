create table messages
(
    id           int primary key auto_increment,
    sender_id    int not null,
    receiver_id  int not null,
    text_message varchar(3000),
    constraint foreign key (sender_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id) references users (id) on delete restrict on update cascade
);