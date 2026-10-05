@crear_usuario
Feature: POST /usuarios - Registrar usuario

Background:
  * url baseUrl
  * header Accept = 'application/json'
  * header Content-Type = 'application/json'
  * def data = call read('classpath:features/common/user-test-data.feature')
  * def schemas = call read('classpath:features/common/user-schema.feature')

@smoke
Scenario: Registrar usuario con datos validos
  Given path 'usuarios'
  And request data.user
  When method POST
  Then status 201
  And match response == schemas.createResponseSchema
  And match response.message == 'Cadastro realizado com sucesso'

@negative
Scenario: Registrar usuario con email duplicado
  Given path 'usuarios'
  And request data.user
  When method POST
  Then status 201
  * def userId = response._id
  Given path 'usuarios'
  And request data.user
  When method POST
  Then status 400
  And match response.message == 'Este email já está sendo usado'
  Given path 'usuarios', userId
  When method DELETE
  Then status 200
