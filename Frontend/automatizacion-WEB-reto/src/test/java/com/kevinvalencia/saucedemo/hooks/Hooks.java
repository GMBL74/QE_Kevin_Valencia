package com.kevinvalencia.saucedemo.hooks;

import com.kevinvalencia.saucedemo.context.TestContext;
import io.cucumber.java.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        context.start();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && context.page() != null) {
            try {
                Path screenshots = Path.of("screenshots");
                Files.createDirectories(screenshots);

                byte[] image = context.page().screenshot(
                        new com.microsoft.playwright.Page.ScreenshotOptions()
                                .setFullPage(true)
                );

                scenario.attach(image, "image/png", "Evidencia del fallo");
                Files.write(
                        screenshots.resolve(sanitize(scenario.getName()) + ".png"),
                        image
                );
            } catch (Exception ignored) {
                // La evidencia no debe impedir el cierre del escenario.
            }
        }

        context.stop();
    }

    private String sanitize(String name) {
        return name.replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}
