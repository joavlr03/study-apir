# Study APIR

API de estudo de Spring Boot.

## Instruções de uso

```
mvn spring-boot:run
```

# Script MySQL (Debeaver)
insert into clientes(id, nome)
select id, nome from clientes;

select * from clientes;


update clientes 
set nome_cliente = nome
where nome_cliente is null;


docker run -d \
    --name mysql \
    --rm \
    -e MYSQL_ROOT_PASSWORD=root_pwd \
    -e MYSQL_USER=new_user \
    -e MYSQL_PASSWORD=my_pwd \
    -p 3306:3306 \
    mysql
