Feature: Contratos JSON de usuarios

  Scenario: Definir schemas reutilizables

    * def userSchema =
      """
      {
        "nome": "#string",
        "email": "#string",
        "password": "#string",
        "administrador": "#string",
        "_id": "#string"
      }
      """

    * def usersListSchema =
      """
      {
        "quantidade": "#number",
        "usuarios": "#[]"
      }
      """

    * def createResponseSchema =
      """
      {
        "message": "#string",
        "_id": "#string"
      }
      """

    * def messageSchema =
      """
      {
        "message": "#string"
      }
      """