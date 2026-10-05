package com.kevinvalencia.saucedemo.steps;

import com.kevinvalencia.saucedemo.context.TestContext;
import com.kevinvalencia.saucedemo.pages.CheckoutPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {

    private static final String EXPECTED_CONFIRMATION_MESSAGE = "Thank you for your order!";

    private final CheckoutPage checkoutPage;

    public CheckoutSteps(TestContext context) {
        this.checkoutPage = context.checkoutPage();
    }

    public void enterCustomerInformation() {
        checkoutPage.completeCustomerInformation(
                "Kevin",
                "QA",
                "15001"
        );
    }

    public void finishPurchase() {
        checkoutPage.finishPurchase();
    }

    public void assertPurchaseConfirmation() {
        String actualMessage = checkoutPage.getPurchaseConfirmationMessage();

        assertEquals(
                EXPECTED_CONFIRMATION_MESSAGE,
                actualMessage,
                "El mensaje de confirmacion de compra no es el esperado."
        );
    }
}
