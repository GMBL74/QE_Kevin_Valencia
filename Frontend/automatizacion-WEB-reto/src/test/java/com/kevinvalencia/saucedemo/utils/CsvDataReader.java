package com.kevinvalencia.saucedemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CsvDataReader {

    private CsvDataReader() {
    }

    public static String getValue(String fileName, String keyColumn, String keyValue, String columnName) {
        return getRow(fileName, keyColumn, keyValue).get(columnName);
    }

    public static Map<String, String> getRow(String fileName, String keyColumn, String keyValue) {
        List<Map<String, String>> rows = read(fileName);

        for (Map<String, String> row : rows) {
            if (keyValue.equals(row.get(keyColumn))) {
                return row;
            }
        }

        throw new IllegalArgumentException(
                "No se encontro el valor '" + keyValue + "' en la columna '"
                        + keyColumn + "' del archivo " + fileName
        );
    }

    public static Map<String, String> getRow(String fileName, String caseId) {
        return getRow(fileName, "case", caseId);
    }

    private static List<Map<String, String>> read(String fileName) {
        String resourcePath = "testdata/" + fileName;

        try (InputStream input = CsvDataReader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "No se encontro el archivo de datos: " + resourcePath
                );
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8))) {

                String headerLine = reader.readLine();

                if (headerLine == null || headerLine.isBlank()) {
                    throw new IllegalArgumentException(
                            "El CSV esta vacio o no contiene encabezados: " + fileName
                    );
                }

                List<String> headers = parseCsvLine(headerLine);
                List<Map<String, String>> rows = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) {
                        continue;
                    }

                    List<String> values = parseCsvLine(line);

                    if (values.size() != headers.size()) {
                        throw new IllegalArgumentException(
                                "La fila del CSV no tiene la misma cantidad de columnas que el encabezado: "
                                        + line
                        );
                    }

                    Map<String, String> row = new LinkedHashMap<>();

                    for (int i = 0; i < headers.size(); i++) {
                        row.put(headers.get(i).trim(), values.get(i).trim());
                    }

                    rows.add(row);
                }

                return rows;
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer el archivo CSV: " + resourcePath,
                    e
            );
        }
    }

    private static List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);

            if (character == '"') {
                if (insideQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else if (character == ',' && !insideQuotes) {
                values.add(current.toString());
                current.setLength(0);
            } else {
                current.append(character);
            }
        }

        if (insideQuotes) {
            throw new IllegalArgumentException(
                    "CSV invalido: comillas sin cerrar en la linea: " + line
            );
        }

        values.add(current.toString());
        return values;
    }
}
