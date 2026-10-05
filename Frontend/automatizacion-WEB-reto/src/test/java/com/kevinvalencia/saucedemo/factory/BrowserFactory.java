package com.kevinvalencia.saucedemo.factory;

import com.kevinvalencia.saucedemo.config.Config;
import com.microsoft.playwright.*;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static Playwright createPlaywright() {
        return Playwright.create();
    }

    public static Browser createBrowser(Playwright playwright) {
        BrowserType browserType = switch (Config.BROWSER) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            case "chromium" -> playwright.chromium();
            default -> throw new IllegalArgumentException(
                    "Browser no soportado: " + Config.BROWSER
            );
        };

        return browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(Config.HEADLESS)
        );
    }

    public static BrowserContext createContext(Browser browser) {
        return browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1440, 900)
        );
    }
}
