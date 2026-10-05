# language: en
@cart
Feature: Carrito de compras
  Como cliente autenticado
  Quiero consultar mi carrito
  Para verificar los productos seleccionados

  @smoke
  Scenario Outline: Visualizar productos agregados en el carrito
    Given que estoy en la pagina de inicio de sesion
    When inicio sesion con el usuario "standard"
    And cargo los datos del caso "<caso>" desde "<archivo>"
    And agrego los productos cargados al carrito
    And abro el carrito de compras
    Then deberia visualizar los productos cargados en el carrito

    Examples:
      | archivo      | caso  |
      | products.csv | TC001 |

  @negative
  Scenario Outline: Eliminar un producto del carrito
    Given que estoy en la pagina de inicio de sesion
    When inicio sesion con el usuario "standard"
    And cargo los datos del caso "<caso>" desde "<archivo>"
    And agrego los productos cargados al carrito
    And abro el carrito de compras
    When elimino el producto cargado del carrito
    Then no deberia visualizar el producto cargado en el carrito

    Examples:
      | archivo      | caso  |
      | products.csv | TC002 |
