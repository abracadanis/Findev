create table projectEntity(
    id Long not null primary key ,
    title varchar(255),
    description(255)
);

create table userEntity(
    id Long not null primary key ,
    name varchar(255),
    surname varchar(255)
);