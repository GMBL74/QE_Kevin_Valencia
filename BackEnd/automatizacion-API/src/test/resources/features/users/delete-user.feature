@eliminar_usuario
Feature: DELETE /usuarios/{_id} - Eliminar usuario

Background:
  * url baseUrl
  * header Accept = 'application/json'
  * header Content-Type = 'application/json'
  * def data = call read('classpath:features/common/user-test-data.feature')
  * def schemas = call read('classpath:features/common/user-schema.feature')

@smoke
Scenario: Eliminar usuario existente
  Given path 'usuarios'
  And request data.user
  When method POST
  Then status 201
  * def userId = response._id
  Given path 'usuarios', userId
  When method DELETE
  Then status 200
  And match response == schemas.messageSchema
  And match response.message == 'Registro excluído com sucesso'
  Given path 'usuarios', userId
  When method DELETE
  Then status 200
  And match response.message == 'Nenhum registro excluído'

@negative
Scenario: Eliminar usuario inexistente
  Given path 'usuarios', 'id-inexistente-qa'
  When method DELETE
  Then status 200
  And match response.message == 'Nenhum registro excluído'
