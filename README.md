# Study APIR

API de estudo de Spring Boot.

## Instruções de uso

### Iniciando o Maven

```
mvn spring-boot:run
```

### Iniciando o banco de dados (docker)
```
docker run -d --name mysql --rm -e MYSQL_ROOT_PASSWORD=root_pwd -e MYSQL_USER=new_user -e MYSQL_PASSWORD=my_pwd -p 3306:3306 mysql
```
