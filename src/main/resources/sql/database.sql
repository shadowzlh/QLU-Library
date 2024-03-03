create table user
(
    username varchar(20) null,
    password varchar(20) null,
    email    varchar(40) null,
    user_id  int auto_increment
        primary key
);

create table userArea
(
    user_id int          not null,
    area    varchar(100) not null,
    primary key (area, user_id)
);

create table userDate
(
    date    date not null,
    user_id int  not null,
    primary key (user_id, date)
);

create table userSeat
(
    user_id int not null,
    seat    int not null,
    primary key (user_id, seat)
);

create table userSeatStr
(
    user_id int          not null,
    seatstr varchar(100) not null,
    primary key (user_id, seatstr)
);

