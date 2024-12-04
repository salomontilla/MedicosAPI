create table usuario (
    id bigint not null auto_increment,
    nombre varchar(255) not null,
    clave varchar(300) not null,
    primary key (id)
);