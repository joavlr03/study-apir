# Study APIR

API de estudo de Spring Boot.

## Instruções de uso

### Iniciando o Maven

```
mvn spring-boot:run
```

ou

````
./mvnw.cmd spring-boot:run
````

### Iniciando o banco de dados (docker)
```
docker run -d --name mysql --rm -e MYSQL_ROOT_PASSWORD=root_pwd -e MYSQL_USER=new_user -e MYSQL_PASSWORD=my_pwd -p 3306:3306 mysql
```


## Aula 3/8

Imagem da minha API Java (Dockerfile)

### 1 - sistema operacional Linux

**************************************************************************
Dockerfile:
  
RUN echo ok!
RUN echo test2!

------------------

RUN apt update && \
apt upgrade -y && \
apt install openjdk-17-jdk -y

FROM ubuntu:latest
**************************************************************************
* local: 

docker run -it ubuntu:latest bash

### 2 - java instalado
**************************************************************************
* Dockerfile:

RUN apt update 
RUN apt upgrade -y 
RUN apt install openjdk-17-jdk -y
**************************************************************************
* local: 
apt update
apt upgrade -y
apt list *jdk*17*
apt install openjdk-17-jdk -y

3 - maven instalado

**************************************************************************
* Dockerfile:
RUN apt install -y maven

**************************************************************************
* local: 
apt install -y maven

4 - codigo fonte da aplicação
5 - compilar - mvn package
6 - executar a aplicação - java -jar app.jar

Criar imagem

docker build -t minha-imagem .
docker images

extra: kubernets (ver)
 
-----------
````
Dockerfile
````


FROM ubuntu:latest

RUN apt update && \
    apt upgrade -y && \
    apt install openjdk-17-jdk -y && \
    apt install -y maven

WORKDIR /opt/app

COPY . .

RUN mvn clean package

CMD [ "java", "-jar", "target/app.jar" ]


-----comando para gerar imagem---
docker build -t minha-imagem:1.5 .
docker run minha-imagem:1.5
docker images
