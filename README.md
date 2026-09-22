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



# Provisionamento Azure SQL + Azure Container Apps

## 1. Variáveis

### PowerShell

```powershell
$env:RESOURCE_GROUP="rs-microservice"
$env:SQL_SERVER="sql-study-apir-dev"
$env:APP_CONTAINER="study-apir-dev"
$env:CONTAINERAPPS_ENVIRONMENT="study-apir-dev"

$env:DB_SERVER_URL="sql-study-apir-dev.database.windows.net"
$env:DB_SERVER_PORT="1433"
$env:DB_SCHEMA="api"
$env:DB_USER="sqladmin"
$env:DB_PWD="<senha>"

$env:SPRING_PROFILES_ACTIVE="prd"
$env:DOCKER_IMAGE="acnaweb/study-apir"
```

### Bash

```bash
export RESOURCE_GROUP=rs-microservice
export SQL_SERVER=sql-study-apir-dev
export APP_CONTAINER=study-apir-dev
export CONTAINERAPPS_ENVIRONMENT=study-apir-dev

export DB_SERVER_URL=sql-study-apir-dev.database.windows.net
export DB_SERVER_PORT=1433
export DB_SCHEMA=api
export DB_USER=sqladmin
export DB_PWD="<senha>"

export SPRING_PROFILES_ACTIVE=prd
export DOCKER_IMAGE=acnaweb/study-apir
```

## 2. Criar Resource Group

```bash
az group create \
  --name $RESOURCE_GROUP \
  --location brazilsouth
```

## 3. Criar SQL Server

```bash
az sql server create \
  --name $SQL_SERVER \
  --resource-group $RESOURCE_GROUP \
  --location brazilsouth \
  --admin-user $DB_USER \
  --admin-password $DB_PWD
```

## 4. Criar SQL Database

```bash
az sql db create \
  --name $DB_SCHEMA \
  --resource-group $RESOURCE_GROUP \
  --server $SQL_SERVER \
  --edition GeneralPurpose \
  --family Gen5 \
  --capacity 2 \
  --compute-model Serverless \
  --use-free-limit \
  --free-limit-exhaustion-behavior AutoPause
```

## 5. Criar regra de Firewall

```bash
az sql server firewall-rule create \
  --resource-group $RESOURCE_GROUP \
  --server $SQL_SERVER \
  --name AllowAll \
  --start-ip-address 0.0.0.0 \
  --end-ip-address 255.255.255.255
```

> Esta regra permite conexões de qualquer endereço IPv4. Use apenas para desenvolvimento/testes.

## 6. Verificar regra de Firewall

```bash
az sql server firewall-rule show \
  --resource-group $RESOURCE_GROUP \
  --server $SQL_SERVER \
  --name AllowAll
```

## 7. Obter endereço do SQL Server

```bash
az sql server show \
  --resource-group $RESOURCE_GROUP \
  --name $SQL_SERVER \
  --query fullyQualifiedDomainName \
  --output tsv
```

## 8. Obter Connection String ADO.NET

```bash
az sql db show-connection-string \
  --client ado.net \
  --server $SQL_SERVER \
  --name $DB_SCHEMA
```

## 9. Obter Connection String JDBC

```bash
az sql db show-connection-string \
  --client jdbc \
  --server $SQL_SERVER \
  --name $DB_SCHEMA
```

## 10. Criar Container Apps Environment

```bash
az containerapp env create \
  --name "$CONTAINERAPPS_ENVIRONMENT" \
  --resource-group "$RESOURCE_GROUP" \
  --location brazilsouth
```

## 11. Criar Container App

```bash
az containerapp create \
  --name "$APP_CONTAINER" \
  --resource-group "$RESOURCE_GROUP" \
  --environment "$CONTAINERAPPS_ENVIRONMENT" \
  --image "$DOCKER_IMAGE" \
  --ingress external \
  --target-port 8080 \
  --min-replicas 1 \
  --max-replicas 1 \
  --secrets "db-pwd=$DB_PWD" \
  --env-vars \
    DB_SERVER_URL="$DB_SERVER_URL" \
    DB_SERVER_PORT="$DB_SERVER_PORT" \
    DB_SCHEMA="$DB_SCHEMA" \
    DB_USER="$DB_USER" \
    DB_PWD="secretref:db-pwd" \
    SPRING_PROFILES_ACTIVE="$SPRING_PROFILES_ACTIVE"
```

## 12. Obter URL da aplicação

```bash
az containerapp show \
  --name "$APP_CONTAINER" \
  --resource-group "$RESOURCE_GROUP" \
  --query properties.configuration.ingress.fqdn \
  --output tsv
```

## 13. Verificar status da aplicação

```bash
az containerapp show \
  --name "$APP_CONTAINER" \
  --resource-group "$RESOURCE_GROUP" \
  --query "properties.runningStatus" \
  --output tsv
```

## 14. Forçar redeploy — Bash

```bash
az containerapp update \
  --name "$APP_CONTAINER" \
  --resource-group "$RESOURCE_GROUP" \
  --image "$DOCKER_IMAGE" \
  --revision-suffix "$(date +%Y%m%d%H%M%S)"
```

## 15. Forçar redeploy — PowerShell

```powershell
az containerapp update `
  --name "$env:APP_CONTAINER" `
  --resource-group "$env:RESOURCE_GROUP" `
  --image "$env:DOCKER_IMAGE" `
  --revision-suffix "$(Get-Date -Format 'yyyyMMddHHmmss')"
```

## 16. Consultar logs

```bash
az containerapp logs show \
  --name "$APP_CONTAINER" \
  --resource-group "$RESOURCE_GROUP" \
  --follow
```

## 17. Estrutura dos recursos

```text
Azure
│
└── Resource Group: rs-microservice
    │
    ├── SQL Server
    │   └── sql-study-apir-dev
    │       └── Database: api
    │
    └── Container Apps Environment
        └── study-apir-dev
            └── Container App
                └── study-apir-dev
                    └── Image: acnaweb/study-apir
```

## 18. Fluxo da aplicação

```text
Internet
   │
   ▼
Azure Container App
study-apir-dev
   │
   │ sql-study-apir-dev.database.windows.net:1433
   ▼
Azure SQL Server
sql-study-apir-dev
   │
   ▼
Azure SQL Database
api
```
