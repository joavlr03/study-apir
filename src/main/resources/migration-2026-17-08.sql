create table clientes (
    id bigint not null,
    nome_cliente char(100) not null,
    primary key (id)
) engine = InnoDB;

create table produtos (
    id bigint not null,
    nome varchar(255),
    valor decimal(38, 2),
    primary key (id)
) engine = InnoDB;

create table produtos_seq (next_val bigint) engine = InnoDB;

insert into produtos_seq (next_val) values (1);