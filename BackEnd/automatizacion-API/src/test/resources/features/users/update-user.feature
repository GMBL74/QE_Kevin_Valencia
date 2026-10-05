@actualizar_usuario
Feature: PUT /usuarios/{_id} - Actualizar usuario

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'
    * def data = call read('classpath:features/common/user-test-data.feature')
    * def schemas = call read('classpath:features/common/user-schema.feature')

  @smoke
  Scenario: Actualizar usuario existente

    Given path 'usuarios'
    And request data.user
    When method POST
    Then status 201
    * def userId = response._id

    Given path 'usuarios', userId
    And request data.updatedUser
    When method PUT
    Then status 200
    And match response == schemas.messageSchema
    And match response.message == 'Registro alterado com sucesso'

    Given path 'usuarios', userId
    When method GET
    Then status 200
    And match response.nome == data.updatedUser.nome
    And match response.email == data.updatedUser.email
    And match response.password == data.updatedUser.password
    And match response.administrador == data.updatedUser.administrador

    Given path 'usuarios', userId
    When method DELETE
    Then status 200


  @negative
  Scenario: Actualizar usuario usando email de otro usuario

    Given path 'usuarios'
    And request data.user
    When method POST
    Then status 201
    * def firstId = response._id

    Given path 'usuarios'
    And request data.secondUser
    When method POST
    Then status 201
    * def secondId = response._id

    Given path 'usuarios', secondId
    And request data.duplicateEmailUpdate
    When method PUT
    Then status 400
    And match response.message == 'Este email já está sendo usado'

    Given path 'usuarios', firstId
    When method DELETE
    Then status 200

    Given path 'usuarios', secondId
    When method DELETE
    Then status 200