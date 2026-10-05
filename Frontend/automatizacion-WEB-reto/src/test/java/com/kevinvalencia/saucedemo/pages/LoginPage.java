package com.kevinvalencia.saucedemo.pages;

import com.kevinvalencia.saucedemo.config.Config;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class LoginPage {

    private final Page page;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        usernameInput = page.locator("#user-name");
        passwordInput = page.locator("#password");
        loginButton = page.locator("#login-button");
        errorMessage = page.locator("[data-test='error']");
    }

    public void navigate() {
        page.navigate(Config.BASE_URL);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        loginButton.waitFor();
    }

    public void enterUsername(String username) { usernameInput.fill(username); }
    public void enterPassword(String password) { passwordInput.fill(password); }
    public void clickLogin() { loginButton.click(); }

    public String getErrorMessage() {
        errorMessage.waitFor();
        return errorMessage.innerText();
    }

    public boolean isLoginFormDisplayed() {
        loginButton.waitFor();
        return loginButton.isVisible();
    }

    public void waitUntilLoaded() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        loginButton.waitFor();
    }
}
