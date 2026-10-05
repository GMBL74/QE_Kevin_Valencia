package com.kevinvalencia.saucedemo.steps;

import com.kevinvalencia.saucedemo.context.TestContext;
import com.kevinvalencia.saucedemo.context.TestData;
import com.kevinvalencia.saucedemo.pages.ProductsPage;

public class ProductSteps {

    private final ProductsPage productsPage;
    private final TestData testData;

    public ProductSteps(TestContext context) {
        this.productsPage = context.productsPage();
        this.testData = context.testData();
    }

    public void addProductToCart(String productName) {
        productsPage.addProduct(productName);
    }

    public void addProductsToCart(String products) {
        productsPage.addProducts(products);
    }

    public void loadProductsFromCsv(String fileName, String caseId) {
        testData.load(fileName, caseId);
    }

    public void addLoadedProductsToCart() {
        productsPage.addProducts(testData.get("products"));
    }

    public void openCart() {
        productsPage.openCart();
    }
}
