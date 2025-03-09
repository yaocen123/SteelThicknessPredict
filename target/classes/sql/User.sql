-- auto-generated definition
create table users
(
    id          bigint auto_increment
        primary key,
    username    varchar(50)                         not null,
    password    varchar(255)                        not null,
    create_time timestamp default CURRENT_TIMESTAMP null,
    constraint username
        unique (username)
);

