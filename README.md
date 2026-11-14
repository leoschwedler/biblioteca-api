# spring-boot-base

Projeto base em **Spring Boot 4 + Java 17** para treinar e iniciar APIs REST. Já vem com as dependências essenciais configuradas, banco PostgreSQL via Docker, migrations versionadas e um CRUD de exemplo pronto para servir de modelo.

## Stack

| Área | Tecnologias |
|---|---|
| Web | Spring Web MVC, Bean Validation |
| Persistência | Spring Data JPA (Hibernate), PostgreSQL |
| Migrations | Flyway |
| Produtividade | Lombok, MapStruct, DevTools |
| Documentação | springdoc-openapi (Swagger UI) |
| Observabilidade | Spring Boot Actuator |
| Infra local | Docker Compose (sobe o Postgres sozinho) |
| Testes | JUnit 5, Spring Boot Test, Testcontainers |

## Como rodar

Requisitos: Java 17+ e Docker.

```bash
cp .env.example .env      # ajuste os valores se quiser
./mvnw spring-boot:run    # sobe o Postgres e a API
./mvnw test               # roda os testes
```

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/actuator/health

## Configuração

As variáveis ficam no `.env` (ignorado pelo Git). O `.env.example` é o modelo versionado.

| Variável | Descrição | Padrão |
|---|---|---|
| `DB_NAME` | Nome do banco | `base` |
| `DB_USER` | Usuário do Postgres | `postgres` |
| `DB_PASSWORD` | Senha do Postgres | `postgres` |
| `DB_URL` | URL JDBC | `jdbc:postgresql://localhost:5432/base` |
| `PORT` | Porta da API | `8080` |

## Estrutura

```
src/main/java/com/example
├── BaseApplication.java
├── exception            # ErrorResponse e exceções personalizadas
├── handler              # GlobalExceptionHandler
└── task                 # feature: cada entidade ganha uma pasta assim
    ├── controller       # endpoints REST
    ├── service          # regras de negócio
    ├── repository       # Spring Data JPA
    ├── entity           # entidade JPA
    ├── enums            # enums do domínio
    ├── mapper           # MapStruct
    ├── request          # DTOs de entrada (records)
    └── response         # DTOs de saída (records)
src/main/resources
├── application.yml
└── db/migration         # migrations do Flyway (V1__create_task_table.sql)
```

## Exemplo incluído: CRUD de `Task`

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/tasks` | Lista as tasks |
| GET | `/api/tasks/{id}` | Busca por id |
| POST | `/api/tasks` | Cria uma task |
| PUT | `/api/tasks/{id}` | Atualiza uma task |
| DELETE | `/api/tasks/{id}` | Remove uma task |

O schema é controlado pelo Flyway (`ddl-auto: validate`): para mudar o banco, crie uma nova migration (`V2__...sql`).

## Ideias para evoluir

Spring Security + JWT, paginação e filtros, relacionamentos entre entidades, testes de integração com Testcontainers, cache com Redis, auditoria.
