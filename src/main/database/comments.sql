create table comments(
	comment_id int primary key auto_increment,
    user_id int not null,
    constraint foreign key(user_id) references users(id) on delete restrict on update cascade,
    post_id int not null,
    constraint foreign key(post_id) references posts(post_id) on delete restrict on update cascade,
    comment_text varchar(3000),
    comment_date datetime
);