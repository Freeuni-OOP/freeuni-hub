create table users
(
    id          int primary key auto_increment,
    first_name  varchar(64)  not null,
    last_name   varchar(64)  not null,
    user_name   varchar(64) unique,
    password    varchar(300) not null,
    email       varchar(64) unique,
    profile_pic longtext
);

create table friends
(
    id              int primary key auto_increment,
    requester_id    int not null,
    receiver_id     int not null,
    connection_date date,
    constraint foreign key (requester_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id) references users (id) on delete restrict on update cascade

);

create table friendRequests
(
    id           int primary key auto_increment,
    requester_id int     not null,
    receiver_id  int     not null,
    accepted     boolean not null,
    constraint foreign key (requester_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id) references users (id) on delete restrict on update cascade
);

create table groupsInfo
(
    id         int primary key auto_increment,
    group_name varchar(100) unique,
    admin_id   int not null,
    constraint foreign key (admin_id) references users (id) on delete restrict on update cascade
);

create table groupMembers
(
    group_id  int not null,
    member_id int not null,
    constraint foreign key (group_id) references groupsInfo (id) on delete restrict on update cascade,
    constraint foreign key (member_id) references users (id) on delete restrict on update cascade
);

create table usersInfo
(
    user_id        int not null,
    constraint foreign key (user_id) references users (id) on delete restrict on update cascade,
    user_name      varchar(100),
    user_last_name varchar(100),
    sqesi          varchar(100),
    course         varchar(100),
    courseNum      int,
    image          longBlob
);

create table messages
(
    id           int primary key auto_increment,
    sender_id    int not null,
    receiver_id  int not null,
    text_message varchar(3000),
    constraint foreign key (sender_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (receiver_id) references users (id) on delete restrict on update cascade
);

create table posts
(
    post_id   int primary key auto_increment,
    user_id   int not null,
    constraint foreign key (user_id) references users (id) on delete restrict on update cascade,
    post_text varchar(3000),
    post_date datetime
);

create table comments
(
    comment_id   int primary key auto_increment,
    user_id      int not null,
    constraint foreign key (user_id) references users (id) on delete restrict on update cascade,
    post_id      int not null,
    constraint foreign key (post_id) references posts (post_id) on delete restrict on update cascade,
    comment_text varchar(3000),
    comment_date datetime
);

create table blockedUsers
(
    id         int primary key auto_increment,
    blocker_id int not null,
    blocked_id int not null,
    constraint foreign key (blocker_id) references users (id) on delete restrict on update cascade,
    constraint foreign key (blocked_id) references users (id) on delete restrict on update cascade
);


create table locations
(
    id          int primary key auto_increment,
    name        varchar(300) not null unique,
    numStudents int
);

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

create table locationMembers
(
    location_id int not null,
    user_id     int not null unique,
    constraint foreign key (location_id) references locations (id) on delete restrict on update cascade,
    constraint foreign key (user_id) references users (id) on delete restrict on update cascade
);

insert into locations (name, numStudents)
values ('Fari2', 0);
insert into locations (name, numStudents)
values ('Baxmaro2', 0);
insert into locations (name, numStudents)
values ('Qvabisxevi2', 0);
insert into locations (name, numStudents)
values ('Fari3', 0);
insert into locations (name, numStudents)
values ('Baxmaro3', 0);
insert into locations (name, numStudents)
values ('Qvabisxevi3', 0);