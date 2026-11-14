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

## Desafio: Biblioteca

Exercício para praticar relacionamentos, queries e paginação. O `Task` acima é só o modelo de estrutura; aqui você implementa tudo, incluindo as migrations (`V2__...sql` em diante), e cada entidade ganha sua pasta como `task/`.

### Relacionamentos

```
Author ──N:N── Book ──1:N── Loan ──N:1── Member ──1:1── MemberCard
```

| Tipo | Entidades |
|---|---|
| N:N | `Book` ↔ `Author` |
| N:1 | `Loan` → `Book` e `Loan` → `Member` |
| 1:1 | `Member` ↔ `MemberCard` |

### Entidades e campos

**Author**: `id`, `name`, `email` (único), `nationality`, `birthDate`, `createdAt`

**Book**: `id`, `title`, `isbn` (único), `publishedYear`, `pages`, `price`, `status` (`AVAILABLE`, `BORROWED`, `UNAVAILABLE`), `createdAt`

**Member**: `id`, `name`, `email` (único), `phone`, `createdAt`

**MemberCard**: `id`, `cardNumber` (único), `issuedAt`, `expiresAt`, `active`

**Loan**: `id`, `loanDate`, `dueDate`, `returnDate` (nulo até devolver), `status` (`ACTIVE`, `RETURNED`, `LATE`), `createdAt`

### Endpoints a implementar

**CRUD** em `/api/authors`, `/api/books`, `/api/members` e `/api/loans`: criar, buscar por id, atualizar, remover e listar (a listagem sempre paginada).

**Relacionamentos**

- [ ] `POST /api/books` recebendo `authorIds`
- [ ] `PUT /api/books/{id}/authors`: substitui os autores do livro
- [ ] `POST /api/books/{id}/authors/{authorId}` e `DELETE` na mesma rota
- [ ] `POST /api/members`: cria o sócio já com a carteirinha
- [ ] `GET /api/members/{id}/card`
- [ ] `POST /api/loans` com `bookId` e `memberId`: só empresta livro disponível e marca o livro como emprestado
- [ ] `PATCH /api/loans/{id}/return`: registra a devolução e libera o livro

**Busca por nome de método (derived queries)**

- [ ] `GET /api/books?title=`: título contendo o texto, sem diferenciar maiúsculas
- [ ] `GET /api/books?yearFrom=&yearTo=`: livros num intervalo de anos
- [ ] `GET /api/books?status=`
- [ ] `GET /api/books/by-author?name=`: livros cujo autor contém o nome (atravessa a N:N)
- [ ] `GET /api/books/top-expensive`: os 5 livros mais caros
- [ ] `GET /api/members/by-card/{number}`: sócio pelo número da carteirinha (atravessa a 1:1)
- [ ] `GET /api/loans?memberId=&status=`
- [ ] `GET /api/authors/exists?email=`: retorna se o e-mail já existe

**`@Query` (JPQL)**

- [ ] `GET /api/loans/overdue`: empréstimos ativos com prazo vencido, carregando livro e sócio sem causar N+1
- [ ] `GET /api/books/search?q=`: busca no título ou no nome do autor, sem repetir livros

**Native queries**

- [ ] `GET /api/books/most-borrowed?limit=`: livros mais emprestados, com a contagem
- [ ] `GET /api/members/inactive?days=`: sócios sem empréstimo há N dias
- [ ] `GET /api/authors/book-count`: todos os autores com a quantidade de livros, inclusive os com zero

**Projections**

- [ ] `GET /api/books/summary`: apenas título e ISBN (interface projection)
- [ ] `GET /api/members/loan-count`: nome do sócio e total de empréstimos (projection com agregação)
- [ ] `GET /api/loans/report`: relatório montado direto em um record (DTO projection)
- [ ] `GET /api/authors/{id}/books-summary`: projection aninhada

**Paginação**

- [ ] Listagens com `page`, `size` e `sort`
- [ ] Uma listagem com contagem total e outra sem (compare o SQL gerado)
- [ ] Uma native query paginada

### Ordem sugerida

1. `Author` e `Book` com CRUD e N:N
2. `Member` e `MemberCard` com 1:1 e cascade
3. `Loan` com N:1 e as regras de negócio
4. Busca por nome de método
5. `@Query`, depois native queries
6. Projections e paginação sobre as queries anteriores

Dica: ative `spring.jpa.show-sql` e confira o SQL gerado para enxergar N+1 e a diferença entre `LAZY` e `JOIN FETCH`.

## Ideias para evoluir

Spring Security + JWT, paginação e filtros, relacionamentos entre entidades, testes de integração com Testcontainers, cache com Redis, auditoria.
