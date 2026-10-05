package com.kevinvalencia.saucedemo.context;

import com.kevinvalencia.saucedemo.config.Config;
import com.kevinvalencia.saucedemo.factory.BrowserFactory;
import com.kevinvalencia.saucedemo.pages.*;
import com.microsoft.playwright.*;

public class TestContext {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private final TestData testData = new TestData();

    public void start() {
        playwright = BrowserFactory.createPlaywright();
        browser = BrowserFactory.createBrowser(playwright);
        browserContext = BrowserFactory.createContext(browser);
        page = browserContext.newPage();
        page.setDefaultTimeout(Config.TIMEOUT);
        page.setDefaultNavigationTimeout(Config.TIMEOUT);

        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
    }

    public void stop() {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page page() {
        return page;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public ProductsPage productsPage() {
        return productsPage;
    }

    public CartPage cartPage() {
        return cartPage;
    }

    public CheckoutPage checkoutPage() {
        return checkoutPage;
    }

    public TestData testData() {
        return testData;
    }
}
