
# A thing

## Run it

```bash
 export $(grep -v '^#' .env | xargs) && ./mvnw spring-boot:run -Dspring-boot.run.arguments="sample-data.json"
```

## Env variables

| var name              | example value                                 |
| ---                   | ---                                           |
| DATABASE_URL          | jdbc:mysql://localhost:3306/useful_todo_list  |
| DATABASE_USERNAME     | useful                                        |
| DATABASE_PASSWORD     | password                                      |
| MYSQL_ROOT_PASSWORD   | password                                      |
| MYSQL_DATABASE        | useful_todo_list                              |
| MYSQL_USER            | useful                                        |
| MYSQL_PASSWORD        | password                                      |
| MYSQL_PORT            | 3306                                          |
