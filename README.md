# spring-boot-base

Projeto base para treino: Spring Boot 4, Java 17, Web MVC, Validation, JPA, Flyway, PostgreSQL, Lombok, MapStruct, Actuator, DevTools.

## Rodar

```bash
./mvnw spring-boot:run   # sobe o Postgres via compose.yaml automaticamente
./mvnw test
```

Requer Docker. Variáveis opcionais em `.env` (veja `.env.example`).

## Exemplo incluído: CRUD de `Task`

`GET/POST /api/tasks`, `GET/PUT/DELETE /api/tasks/{id}` — entity, repository, DTOs (records), mapper, service, controller, handler de erros (ProblemDetail) e migration `V1`.

Para treinar: crie novas migrations em `src/main/resources/db/migration` (`V2__...sql`), novas entidades, relacionamentos, paginação, testes de integração.
