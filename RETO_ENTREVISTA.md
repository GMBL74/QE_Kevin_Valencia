# Reto técnico en vivo – Mantenimiento de frameworks de automatización

**Duración sugerida:** 60 minutos (25 min Backend · 25 min Frontend · 10 min conversación)

## Contexto

Estos son **tus** frameworks (ServeRest API con Karate DSL y Sauce Demo con Playwright Java + Cucumber).
Mientras estabas de vacaciones, otro QA del equipo hizo un commit:

> `chore: ajustes de configuración, datos y trazas`

Desde entonces el pipeline está en **rojo** en ambas capas: fallan **todas** las pruebas. Tu tarea es
devolverlo a **verde**.

## Reglas

1. **No se permite** borrar escenarios, marcarlos como `@ignore`, ni debilitar las validaciones
   (quitar `match`, `status` o `assert`). El objetivo es arreglar la causa raíz.
2. Puedes usar la documentación oficial (Karate, Playwright, Cucumber) y el navegador (DevTools).
3. **Piensa en voz alta**: explica qué ves, qué hipótesis tienes y cómo la confirmas.
4. Al terminar cada capa, muestra `git diff` y ejecuta la suite completa.

---

## Capa 1 – Backend (Karate DSL · ServeRest)

```bash
cd BackEnd/automatizacion-API
mvn clean test
```

**Nota del QA que hizo el cambio:** *"Ajusté los timeouts, saqué el `path` común al Background de la
búsqueda por ID para no repetirlo, y corregí el dato de 'email duplicado' en la factory, que me pareció
que estaba mal."*

**Entregable:** `mvn clean test` con **10/10 escenarios en verde**.

---

## Capa 2 – Frontend (Playwright Java + Cucumber · Sauce Demo)

```bash
cd Frontend/automatizacion-WEB-reto
mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install chromium"
mvn clean test
```

**Nota del QA que hizo el cambio:** *"Dejé el timeout de QA más legible, negocio actualizó
`products.csv` desde Excel, y agregué una traza al inicio de cada escenario para el pipeline."*

**Entregable:** `mvn clean test` con **7/7 escenarios en verde**.
**Restricción:** negocio seguirá editando `products.csv` desde Excel.

---

## Conversación final (si queda tiempo)

- ¿Qué harías para que estos problemas **no vuelvan a llegar** al pipeline?
- ¿Qué cambiarías de tu propio diseño original después de ver estos fallos?
