package com.kevinvalencia.saucedemo.stepdefinitions;

import com.kevinvalencia.saucedemo.steps.ProductSteps;
import io.cucumber.java.en.When;

public class ProductStepDefinitions {

    private final ProductSteps productSteps;

    public ProductStepDefinitions(ProductSteps productSteps) {
        this.productSteps = productSteps;
    }

    @When("agrego el producto {string} al carrito")
    public void agregoElProductoAlCarrito(String productName) {
        productSteps.addProductToCart(productName);
    }

    @When("agrego los productos {string} al carrito")
    public void agregoLosProductosAlCarrito(String products) {
        productSteps.addProductsToCart(products);
    }

    @When("cargo los datos del caso {string} desde {string}")
    public void cargoLosDatosDelCasoDesde(String caseId, String fileName) {
        productSteps.loadProductsFromCsv(fileName, caseId);
    }

    @When("agrego los productos cargados al carrito")
    public void agregoLosProductosCargadosAlCarrito() {
        productSteps.addLoadedProductsToCart();
    }

    @When("abro el carrito de compras")
    public void abroElCarritoDeCompras() {
        productSteps.openCart();
    }
}
