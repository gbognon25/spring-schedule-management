create table schedule
(
    id bigint primary key auto_increment not null,
    title varchar(200) not null,
    task text not null,
    author varchar(50) not null,
    password varchar(50) not null,
    createdAt datetime not null,
    updatedAt datetime not null
);