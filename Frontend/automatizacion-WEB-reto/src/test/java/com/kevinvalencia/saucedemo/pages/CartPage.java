package com.kevinvalencia.saucedemo.pages;

import com.kevinvalencia.saucedemo.utils.Routes;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;
    private final Locator cartItems;
    private final Locator checkoutButton;
    private final Locator removeButton;

    public CartPage(Page page) {
        this.page = page;
        cartItems = page.locator(".cart_item");
        checkoutButton = page.locator("[data-test='checkout']");
        removeButton = page.locator("button[data-test^='remove']");
    }

    public void waitUntilLoaded() {
        page.waitForURL("**" + Routes.CART);
        checkoutButton.waitFor();
    }

    public boolean containsProduct(String productName) {
        return findProduct(productName).count() > 0;
    }

    public void removeProduct(String productName) {
        Locator product = findProduct(productName);
        product.waitFor();
        product.locator(removeButton).click();
    }


    public void clickCheckout() {
        checkoutButton.click();
        page.waitForURL("**" + Routes.CHECKOUT_STEP_ONE);

    }

    private Locator findProduct(String productName) {
        return cartItems.filter(
                new Locator.FilterOptions().setHasText(productName)
        );
    }
}
