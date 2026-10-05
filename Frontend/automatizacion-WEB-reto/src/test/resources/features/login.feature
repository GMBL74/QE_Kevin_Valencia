# language: en
@login
Feature: Inicio de sesion en SauceDemo
  Como cliente de SauceDemo
  Quiero iniciar sesion
  Para poder acceder a los productos

  @smoke
  Scenario Outline: Validar inicio de sesion con diferentes usuarios
    Given que estoy en la pagina de inicio de sesion
    When inicio sesion con el usuario "<usuario>"
    Then deberia obtener el resultado de login "<resultado>"

    Examples:
      | usuario   | resultado |
      | standard  | success   |
      | lockedOut | locked    |
      | invalid   | invalid   |
