create table posts(
    post_id int primary key auto_increment,
    user_id int not null,
    constraint foreign key(user_id) references users(id) on delete restrict on update cascade,
    post_text varchar(3000),
    post_date datetime
);