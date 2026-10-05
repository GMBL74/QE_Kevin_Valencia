package com.kevinvalencia.saucedemo.stepdefinitions;

import com.kevinvalencia.saucedemo.steps.CheckoutSteps;
import io.cucumber.java.en.*;

public class CheckoutStepDefinitions {
    private final CheckoutSteps checkoutSteps;
    public CheckoutStepDefinitions(CheckoutSteps checkoutSteps) {
        this.checkoutSteps = checkoutSteps;
    }

    @When("completo los datos del cliente")
    public void completoLosDatosDelCliente() {
        checkoutSteps.enterCustomerInformation();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        checkoutSteps.finishPurchase();
    }

    @Then("deberia visualizar la confirmacion de compra")
    public void deberiaVisualizarLaConfirmacionDeCompra() {
        checkoutSteps.assertPurchaseConfirmation();
    }
}
