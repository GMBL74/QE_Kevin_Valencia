# Reto de Automatización QA – FrontEnd

Suite de pruebas automatizadas para SauceDemo utilizando Playwright, Cucumber, Java y Maven.

## Objetivo

Automatizar el flujo principal de un usuario en SauceDemo:

* Inicio de sesion con credenciales validas e invalidas
* Agregar productos al carrito
* Validar productos en el carrito
* Eliminar productos del carrito
* Completar el proceso de compra
* Validar la confirmacion de compra

La solucion utiliza el patron Page Object Model y separa los escenarios Gherkin, los Step Definitions, los Steps y los Page Objects.

## Tecnologias

* Java 17
* Playwright
* Cucumber
* JUnit
* Maven

## Estructura del proyecto

```text id="zlm20r"
src/test
├── java
│   └── com/kevinvalencia/saucedemo
│       ├── config
│       ├── context
│       ├── factory
│       ├── hooks
│       ├── pages
│       ├── steps
│       ├── stepdefinitions
│       ├── testdata
│       ├── utils
│       └── runner
│
└── resources
    ├── config
    ├── features
    └── testdata
```

Flujo principal de ejecucion:

```text id="8w9bho"
Feature
   ↓
Step Definitions
   ↓
Steps
   ↓
Page Objects
   ↓
Playwright
```

## Escenarios automatizados

El proyecto incluye escenarios para:

* Inicio de sesion exitoso
* Usuario bloqueado
* Credenciales invalidas
* Agregar uno o varios productos
* Validar productos en el carrito
* Eliminar productos
* Completar el proceso de compra
* Validar la confirmacion de compra

Los escenarios de inicio de sesion utilizan `Scenario Outline` para ejecutar el mismo flujo con diferentes tipos de usuario.

## Datos de prueba

Las credenciales de los usuarios se encuentran en:

```text id="jjquzf"
src/test/resources/testdata/users.json
```

Los datos de productos se encuentran en:

```text id="bt6j6g"
src/test/resources/testdata/products.csv
```

Los datos de clientes se encuentran en:

```text id="8xakbg"
src/test/resources/testdata/customers.csv
```


## Configuracion de ambientes

El proyecto soporta los siguientes ambientes:

```text id="orngik"
local
qa
uat
```

El ambiente utilizado por defecto es `qa`.

### Ejecutar con el ambiente por defecto

```bash id="d6el6u"
mvn test
```

### Ejecutar en local

```bash id="3jrumu"
mvn test -Denv=local
```

### Ejecutar en UAT

```bash id="o05x06"
mvn test -Denv=uat
```

## Ejecucion por tags

Ejecutar escenarios de login:

```bash id="yjhmog"
mvn test "-Dcucumber.filter.tags=@login"
```

Ejecutar escenarios del carrito:

```bash id="p5wict"
mvn test "-Dcucumber.filter.tags=@cart"
```

Ejecutar escenarios smoke:

```bash id="imbem6"
mvn test "-Dcucumber.filter.tags=@smoke"
```

Tambien es posible combinar un tag con un ambiente:

```bash id="6le3ev"
mvn test "-Dcucumber.filter.tags=@cart" "-Denv=local"
```

## Instalacion de Playwright

Antes de ejecutar las pruebas, instalar el navegador utilizado por el proyecto:

```bash id="zj24yt"
mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install chromium"
```

Luego ejecutar:

```bash id="pj4l8q"
mvn test
```

## Reportes y evidencias

Cucumber genera reportes HTML y JSON dentro de:

```text id="b0kdu1"
target/
```

Cuando un escenario falla, el framework captura una evidencia de pantalla y la adjunta al reporte de Cucumber.

Las capturas tambien se almacenan en:

```text id="lm35qa"
screenshots/
```

Esta carpeta esta excluida del repositorio mediante `.gitignore`.

## Estrategia de automatizacion

El framework aplica los siguientes principios:

* Page Object Model para la interaccion con la interfaz
* Separacion de responsabilidades
* Steps reutilizables
* Escenarios parametrizados con Cucumber
* Datos de prueba externalizados
* Configuracion centralizada de ambientes
* Evidencias automaticas ante fallos

