Feature: Factory de datos de usuario

Scenario: Generar usuario único
  * def timestamp = java.lang.System.currentTimeMillis()
  * def baseUser = read('classpath:data/users.json')
  * def user =
  """
  {
    "nome": "#(baseUser.validUser.nome)",
    "email": "#('qa_' + timestamp + '@example.com')",
    "password": "#(baseUser.validUser.password)",
    "administrador": "#(baseUser.validUser.administrador)"
  }
  """
  * def updatedUser =
  """
  {
    "nome": "#(baseUser.updatedUser.nome)",
    "email": "#(user.email)",
    "password": "#(baseUser.updatedUser.password)",
    "administrador": "#(baseUser.updatedUser.administrador)"
  }
  """
  * def secondUser =
  """
  {
    "nome": "Second QA User",
    "email": "#('qa_second_' + timestamp + '@example.com')",
    "password": "Test@456",
    "administrador": "false"
  }
  """

  * def duplicateEmailUpdate =
  """
  {
    "nome": "Attempted Update",
    "email": "#(secondUser.email)",
    "password": "Changed@123",
    "administrador": "false"
  }
  """