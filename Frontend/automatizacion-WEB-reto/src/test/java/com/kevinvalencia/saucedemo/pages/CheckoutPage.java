package com.kevinvalencia.saucedemo.pages;

import com.kevinvalencia.saucedemo.utils.Routes;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {

    private final Page page;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButton;
    private final Locator finishButton;
    private final Locator confirmationTitle;

    public CheckoutPage(Page page) {
        this.page = page;
        firstNameInput = page.locator("#first-name");
        lastNameInput = page.locator("#last-name");
        postalCodeInput = page.locator("#postal-code");
        continueButton = page.locator("[data-test='continue']");
        finishButton = page.locator("[data-test='finish']");
        confirmationTitle = page.locator("[data-test='complete-header']");
    }

    public void waitUntilLoaded() {
        page.waitForURL("**" + Routes.CHECKOUT_STEP_ONE);
        firstNameInput.waitFor();
    }

    public void completeCustomerInformation(String firstName, String lastName, String postalCode) {
        waitUntilLoaded();
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        postalCodeInput.fill(postalCode);
        continueButton.click();
        page.waitForURL("**" + Routes.CHECKOUT_STEP_TWO);
    }

    public void finishPurchase() {
        finishButton.waitFor();
        finishButton.click();
        page.waitForURL("**" + Routes.CHECKOUT_COMPLETE);
    }

    public String getPurchaseConfirmationMessage() {
        confirmationTitle.waitFor();
        return confirmationTitle.innerText().trim();
    }
}
