package com.kevinvalencia.saucedemo.steps;

import com.kevinvalencia.saucedemo.context.TestContext;
import com.kevinvalencia.saucedemo.context.TestData;
import com.kevinvalencia.saucedemo.pages.CartPage;
import com.microsoft.playwright.Page;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private final CartPage cartPage;
    private final TestData testData;

    public CartSteps(TestContext context) {
        this.cartPage = context.cartPage();
        this.testData = context.testData();
    }

    public void assertProductIsInCart(String productName) {
        cartPage.waitUntilLoaded();
        assertTrue(
                cartPage.containsProduct(productName),
                "El producto no se encuentra en el carrito: " + productName
        );
    }

    public void assertProductsAreInCart(String products) {
        cartPage.waitUntilLoaded();
        for (String productName : products.split(",")) {
            String name = productName.trim();
            if (!name.isBlank()) {
                assertTrue(
                        cartPage.containsProduct(name),
                        "El producto no se encuentra en el carrito: " + name
                );
            }
        }
    }

    public void assertLoadedProductsAreInCart() {
        assertProductsAreInCart(testData.get("products"));
    }

    public void removeProduct(String productName) {
        cartPage.removeProduct(productName);
    }

    public void removeLoadedProductFromCart() {
        cartPage.removeProduct(testData.get("removeProduct"));
    }

    public void assertProductIsNotInCart(String productName) {
        cartPage.waitUntilLoaded();
        assertFalse(
                cartPage.containsProduct(productName),
                "El producto no deberia encontrarse en el carrito: " + productName
        );
    }

    public void assertRemovedProductIsNotInCart() {
        assertProductIsNotInCart(testData.get("removeProduct"));
    }

    public void proceedToCheckout() {
        cartPage.clickCheckout();
    }
}
