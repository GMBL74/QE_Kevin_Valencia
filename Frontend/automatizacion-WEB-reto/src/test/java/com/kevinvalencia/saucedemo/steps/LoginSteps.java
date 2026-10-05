package com.kevinvalencia.saucedemo.steps;

import com.kevinvalencia.saucedemo.context.TestContext;
import com.kevinvalencia.saucedemo.pages.LoginPage;
import com.kevinvalencia.saucedemo.pages.ProductsPage;
import com.kevinvalencia.saucedemo.testdata.TestUsers;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final LoginPage loginPage;
    private final ProductsPage productsPage;

    public LoginSteps(TestContext context) {
        this.loginPage = context.loginPage();
        this.productsPage = context.productsPage();
    }

    public void openLogin() {
        loginPage.navigate();
    }

    public void loginAs(String userType) {
        String username = TestUsers.username(userType);
        String password = TestUsers.password(userType);
        loginAs(username, password);
    }

    private void loginAs(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    public void assertLoginResult(String result) {
        switch (result) {
            case "success" -> assertProductsPageIsDisplayed();
            case "locked" -> assertLoginErrorContains(
                    "Epic sadface: Sorry, this user has been locked out.");
            case "invalid" -> assertLoginErrorContains(
                    "Epic sadface: Username and password do not match any user in this service");
            default -> throw new IllegalArgumentException(
                    "Resultado de login no soportado: " + result);
        }
    }

    public void assertProductsPageIsDisplayed() {
        productsPage.waitUntilLoaded();
        assertTrue(productsPage.isDisplayed(),
                "La pagina de productos no esta visible despues del inicio de sesion.");
    }

    public void assertLoginErrorContains(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "El mensaje de error no contiene: " + expectedMessage);
    }

    public void assertLoginFormIsVisible() {
        loginPage.waitUntilLoaded();
        assertTrue(loginPage.isLoginFormDisplayed(),
                "El formulario de login no esta visible.");
    }
}
