# language: en
@product
Feature: Gestion de productos
  Como cliente autenticado
  Quiero agregar productos al carrito
  Para poder comprarlos

  @smoke
  Scenario Outline: Agregar productos al carrito
    Given que estoy en la pagina de inicio de sesion
    When inicio sesion con el usuario "standard"
    And cargo los datos del caso "<caso>" desde "<archivo>"
    And agrego los productos cargados al carrito
    Then deberia visualizar la pagina de productos

    Examples:
      | archivo      | caso  |
      | products.csv | TC003 |
