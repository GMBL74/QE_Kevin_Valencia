# language: en
@checkout
Feature: Finalizacion de compra
  Como cliente de SauceDemo
  Quiero completar el proceso de compra
  Para adquirir los productos seleccionados

  @smoke
  Scenario Outline: Completar una compra con diferentes productos
    Given que estoy en la pagina de inicio de sesion
    When inicio sesion con el usuario "standard"
    And cargo los datos del caso "<caso>" desde "<archivo>"
    And agrego los productos cargados al carrito
    And abro el carrito de compras
    Then deberia visualizar los productos cargados en el carrito
    And continuo al checkout
    And completo los datos del cliente
    And finalizo la compra
    Then deberia visualizar la confirmacion de compra

    Examples:
      | archivo      | caso  |
      | products.csv | TC003 |
