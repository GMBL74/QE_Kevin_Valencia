@listar_usuario_id
Feature: GET /usuarios/{_id} - Buscar usuario por ID

Background:
  * url baseUrl
  * path 'usuarios'
  * header Accept = 'application/json'
  * header Content-Type = 'application/json'
  * def data = call read('classpath:features/common/user-test-data.feature')
  * def schemas = call read('classpath:features/common/user-schema.feature')

@smoke
Scenario: Buscar usuario existente por ID
  Given path 'usuarios'
  And request data.user
  When method POST
  Then status 201
  * def userId = response._id
  Given path 'usuarios', userId
  When method GET
  Then status 200
  And match response == schemas.userSchema
  And match response._id == userId
  And match response.email == data.user.email
  Given path 'usuarios', userId
  When method DELETE
  Then status 200

@negative
Scenario: Buscar usuario con ID inexistente
  Given path 'usuarios', 'id-inexistente-qa'
  When method GET
  Then status 400
  And match response.id == 'id deve ter exatamente 16 caracteres alfanuméricos'

  @negative
  Scenario: Buscar usuario con ID válido pero inexistente
    Given path 'usuarios', 'abcdefghijklmnop'
    When method GET
    Then status 400
    And match response.message == 'Usuário não encontrado'