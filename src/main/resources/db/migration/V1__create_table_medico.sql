create table medico (
    id bigint not null auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    especialidad varchar(255) not null,
    calle varchar(255) not null,
    barrio varchar(255) not null,
    ciudad varchar(255) not null,
    primary key (id)
);