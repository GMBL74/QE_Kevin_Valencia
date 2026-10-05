# ServeRest API Automation - Karate DSL

Proyecto para el reto técnico QA Backend.

## Alcance
Automatización de usuarios:
- GET /usuarios
- POST /usuarios
- GET /usuarios/{_id}
- PUT /usuarios/{_id}|
- DELETE /usuarios/{_id}

Incluye positivos y negativos, schemas JSON, datos únicos, ambientes y README.

## Stack
Java 17 + Maven + Karate DSL 1.5.2 + JUnit 5.

## Arquitectura
```text
src/test/
├── java/com/qaautomation/api/runners/ApiTest.java
└── resources/
    ├── config/environments.json
    ├── data/users.json
    ├── features/
    │   ├── common/user-schema.feature
    │   ├── common/user-test-data.feature
    │   └── users/
    │       ├── get-users.feature
    │       ├── create-user.feature
    │       ├── get-user-by-id.feature
    │       ├── update-user.feature
    │       └── delete-user.feature
    └── karate-config.js
```

## Patrón
Se usa **Factory para datos de prueba** mediante `user-test-data.feature`, además de separación de responsabilidades:
configuración -> datos -> schemas -> features por endpoint.

No usamos Page Object Model porque es un patrón de UI; Karate ya resuelve HTTP, JSON y assertions directamente.

## Ambientes
`dev`: https://serverest.dev

Cambia las URLs de ambiente en `src/test/resources/config/environments.json`.

## Ejecución
```bash
mvn clean test
mvn clean test "-Dkarate.env=dev"
mvn clean test "-Dkarate.options=--tags @smoke"
mvn clean test "-Dkarate.options=--tags @negative"
```

Reportes: `target/karate-reports/`

## Casos
GET: lista y valida schema.
POST: alta válida + email duplicado.
GET ID: usuario existente + ID inexistente.
PUT: actualización + email duplicado; luego GET para verificar persistencia.
DELETE: eliminación + eliminación inexistente.

## Datos
Los escenarios crean sus propios usuarios y usan email con timestamp. Esto evita depender de IDs existentes.

