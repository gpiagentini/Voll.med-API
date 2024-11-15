create table users(

    id bigint not null auto_increment,
    login varchar(20) not null,
    password varchar(20) not null unique,

    primary key(id)
);