package com.kevinvalencia.saucedemo.testdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.io.InputStream;

public final class TestUsers {

    private static final JsonNode USERS = loadUsers();

    private TestUsers() {
    }

    public static String username(String userType) {
        return value(userType, "username");
    }

    public static String password(String userType) {
        return value(userType, "password");
    }

    private static JsonNode loadUsers() {
        try (InputStream input = TestUsers.class.getClassLoader()
                .getResourceAsStream("testdata/users.json")) {

            if (input == null) {
                throw new IllegalStateException("No se encontro testdata/users.json");
            }

            return JsonMapper.builder()
                    .addModule(new Jdk8Module())
                    .build()
                    .readTree(input);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer users.json", e);
        }
    }

    private static String value(String userType, String key) {
        JsonNode user = USERS.get(userType);

        if (user == null || !user.isObject()) {
            throw new IllegalArgumentException("El usuario de prueba no esta definido: " + userType);
        }

        JsonNode value = user.get(key);

        if (value == null || value.isNull()) {
            throw new IllegalStateException(
                    "La propiedad '" + key + "' no esta definida para el usuario '" + userType + "'."
            );
        }

        return value.asText();
    }
}
