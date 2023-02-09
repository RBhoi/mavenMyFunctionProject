package com.deloitte.excel;
import com.salesforce.functions.jvm.sdk.data.Record;

public class DataModel {
    public Object getValue(Record record, ColumnType columnType, String key) {
        Object obj = null;
        if(ColumnType.STRING.equals(columnType)) {
            if(record.getStringField(key).isPresent())
                obj = record.getStringField(key).get();
        } else if(ColumnType.INT.equals(columnType)) {
            if(record.getIntField(key).isPresent())
                obj = record.getIntField(key).get();
        } else if(ColumnType.DOUBLE.equals(columnType)) {
            if(record.getDoubleField(key).isPresent())
                obj = record.getDoubleField(key).get();
        }
        return obj;
    }
}

