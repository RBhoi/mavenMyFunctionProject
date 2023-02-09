package com.deloitte.excel;

public class ColumnDefinition {
    private final String id;
    private final String name;
    private final ColumnType columnType;

    public ColumnDefinition(String id, String name, ColumnType columnType) {
        this.id = id;
        this.name = name;
        this.columnType = columnType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ColumnType getColumnType() {
        return columnType;
    }
}

