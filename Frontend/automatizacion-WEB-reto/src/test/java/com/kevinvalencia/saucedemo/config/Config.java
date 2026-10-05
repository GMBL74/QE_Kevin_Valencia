package com.kevinvalencia.saucedemo.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public final class Config {

    private static final String DEFAULT_ENVIRONMENT = "qa";
    private static final JsonNode ENVIRONMENT = loadEnvironment();

    public static final String ENV = getEnvironmentName();
    public static final String BASE_URL = value("baseUrl");
    public static final String BROWSER = value("browser").toLowerCase(Locale.ROOT);
    public static final boolean HEADLESS = Boolean.parseBoolean(value("headless"));
    public static final int TIMEOUT = Integer.parseInt(value("timeout"));

    private Config() {
    }

    private static JsonNode loadEnvironment() {
        String environment = getEnvironmentName();

        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("config/environments.json")) {

            if (input == null) {
                throw new IllegalStateException("No se encontro config/environments.json");
            }

            JsonNode root = JsonMapper.builder()
                    .addModule(new Jdk8Module())
                    .build()
                    .readTree(input);
            JsonNode selected = root.get(environment);

            if (selected == null || !selected.isObject()) {
                throw new IllegalStateException(
                        "El ambiente '" + environment + "' no esta definido en environments.json"
                );
            }

            return selected;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer environments.json", e);
        }
    }

    private static String getEnvironmentName() {
        String environment = System.getProperty("env");

        if (environment == null || environment.isBlank()) {
            environment = System.getenv("TEST_ENV");
        }

        return environment == null || environment.isBlank()
                ? DEFAULT_ENVIRONMENT
                : environment.trim().toLowerCase(Locale.ROOT);
    }

    private static String value(String key) {
        JsonNode node = ENVIRONMENT.get(key);

        if (node == null || node.isNull()) {
            throw new IllegalStateException(
                    "La propiedad '" + key + "' no esta definida en environments.json"
            );
        }

        return node.asText();
    }
}
