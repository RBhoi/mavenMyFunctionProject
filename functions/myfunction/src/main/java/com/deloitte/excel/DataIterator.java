package com.deloitte.excel;

import com.salesforce.functions.jvm.sdk.data.Record;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;

import java.util.List;

public interface DataIterator {

    String getName();

    boolean hasNext();

    long getTotalSize();

    List<Record> next() throws DataApiException;

    DataModel getModel();
}
