@listar_usuarios
Feature: GET /usuarios - Listar usuarios

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * def schemas = call read('classpath:features/common/user-schema.feature')
    * def usersListSchema = schemas.usersListSchema

  @smoke
  Scenario: Listar todos los usuarios
    Given path 'usuarios'
    When method GET
    Then status 200

    And match response == usersListSchema
    And match response.quantidade == '#number'
    And match response.usuarios == '#[]'
    And match each response.usuarios == schemas.userSchema