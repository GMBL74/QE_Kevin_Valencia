package com.kevinvalencia.saucedemo.context;

import com.kevinvalencia.saucedemo.utils.CsvDataReader;

import java.util.Map;

public class TestData {

    private String fileName;
    private String keyColumn;
    private String keyValue;
    private Map<String, String> data;

    public void load(String fileName, String keyColumn, String keyValue) {
        this.fileName = fileName;
        this.keyColumn = keyColumn;
        this.keyValue = keyValue;
        this.data = CsvDataReader.getRow(fileName, keyColumn, keyValue);
    }

    public void load(String fileName, String caseId) {
        load(fileName, "case", caseId);
    }

    public String get(String columnName) {
        if (data == null) {
            throw new IllegalStateException("No se han cargado datos de prueba para el escenario.");
        }

        String value = data.get(columnName);
        if (value == null) {
            throw new IllegalArgumentException(
                    "La columna '" + columnName + "' no existe en el registro '" + keyValue
                            + "' de la columna '" + keyColumn + "' del archivo '" + fileName + "'."
            );
        }

        return value.trim();
    }

    public String fileName() {
        return fileName;
    }

    public String keyColumn() {
        return keyColumn;
    }

    public String keyValue() {
        return keyValue;
    }
}
