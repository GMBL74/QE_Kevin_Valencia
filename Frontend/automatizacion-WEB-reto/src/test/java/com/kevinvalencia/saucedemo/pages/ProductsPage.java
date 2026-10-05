package com.kevinvalencia.saucedemo.pages;

import com.kevinvalencia.saucedemo.utils.Routes;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Arrays;
import java.util.List;

public class ProductsPage {

    private final Page page;

    // Locators
    private final Locator inventoryContainer;
    private final Locator cartLink;
    private final Locator addProductButton;
    private final Locator inventoryItem;

    public ProductsPage(Page page) {
        this.page = page;
        inventoryContainer = page.locator(".inventory_list");
        cartLink = page.locator("[data-test='shopping-cart-link']");
        addProductButton = page.locator("button[data-test^='add-to-cart']");
        inventoryItem = page.locator(".inventory_item");
    }

    public void waitUntilLoaded() {
        page.waitForURL("**" + Routes.PRODUCTS);
        inventoryContainer.waitFor();
    }

    public boolean isDisplayed() {
        return inventoryContainer.isVisible();
    }

    public void addProduct(String productName) {
        Locator product = findProduct(productName);
        product.waitFor();
        product.locator(addProductButton).click();
    }

    public void addProducts(String products) {
        List<String> productNames = Arrays.stream(products.split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .toList();

        if (productNames.isEmpty()) {
            throw new IllegalArgumentException("Debe indicar al menos un producto.");
        }

        for (String productName : productNames) {
            addProduct(productName);
        }
    }

    public void openCart() {
        cartLink.click();
        page.waitForURL("**" + Routes.CART);
    }

    private Locator findProduct(String productName) {
        return inventoryItem
                .filter(new Locator.FilterOptions().setHasText(productName));
    }
}
