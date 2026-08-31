# Study APIR

API desenvolvida para estudos de **Spring Boot**, configuração de ambientes, acesso a banco de dados e execução com Docker.

## 📋 Pré-requisitos


=======
Para executar o projeto localmente, você precisará ter instalado:

- Java
- Maven
- MySQL
- Docker (opcional)

---

## 🚀 Execução local

### 1. Configuração das variáveis de ambiente

A aplicação utiliza variáveis de ambiente para configurar a conexão com o banco de dados e o profile do Spring Boot.

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_SERVER_URL` | Endereço do servidor do banco de dados | `localhost` |
| `DB_SERVER_PORT` | Porta do banco de dados | `3306` |
| `DB_SCHEMA` | Nome do schema | `dbprd` |
| `DB_USER` | Usuário do banco de dados | `root` |
| `DB_PWD` | Senha do banco de dados | `root_pwd` |
| `SPRING_PROFILES_ACTIVE` | Profile ativo do Spring Boot | `dev` |

### Linux / macOS

```sh
export DB_SERVER_URL=localhost
export DB_SERVER_PORT=3306
export DB_SCHEMA=dbprd
export DB_USER=root
export DB_PWD=root_pwd
export SPRING_PROFILES_ACTIVE=dev
>>>>>>> 6cad413ecacc70d74881e4f8bb414d70006da00a
```

### Windows PowerShell

```powershell
$env:DB_SERVER_URL="localhost"
$env:DB_SERVER_PORT="3306"
$env:DB_SCHEMA="dbprd"
$env:DB_USER="root"
$env:DB_PWD="root_pwd"
$env:SPRING_PROFILES_ACTIVE="dev"
```

### 2. Executar a aplicação

Com Maven:

```sh
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

