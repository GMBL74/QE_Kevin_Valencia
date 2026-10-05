package com.kevinvalencia.saucedemo.stepdefinitions;

import com.kevinvalencia.saucedemo.steps.CartSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartStepDefinitions {

    private final CartSteps cartSteps;

    public CartStepDefinitions(CartSteps cartSteps) {
        this.cartSteps = cartSteps;
    }

    @Then("deberia visualizar el producto {string} en el carrito")
    public void deberiaVisualizarElProductoEnElCarrito(String productName) {
        cartSteps.assertProductIsInCart(productName);
    }

    @Then("deberia visualizar los productos {string} en el carrito")
    public void deberiaVisualizarLosProductosEnElCarrito(String products) {
        cartSteps.assertProductsAreInCart(products);
    }

    @Then("deberia visualizar los productos cargados en el carrito")
    public void deberiaVisualizarLosProductosCargadosEnElCarrito() {
        cartSteps.assertLoadedProductsAreInCart();
    }

    @When("continuo al checkout")
    public void continuoAlCheckout() {
        cartSteps.proceedToCheckout();
    }

    @When("elimino el producto cargado del carrito")
    public void eliminoElProductoCargadoDelCarrito() {
        cartSteps.removeLoadedProductFromCart();
    }

    @Then("no deberia visualizar el producto cargado en el carrito")
    public void noDeberiaVisualizarElProductoCargadoEnElCarrito() {
        cartSteps.assertRemovedProductIsNotInCart();
    }

}
