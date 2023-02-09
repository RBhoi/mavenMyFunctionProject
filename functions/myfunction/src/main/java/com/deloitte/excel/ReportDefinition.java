package com.deloitte.excel;

import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;

import java.util.List;

public interface ReportDefinition {
    List<ColumnDefinition> getColumnDefinitions();
    DataIterator getDataModel(Context context, String entityId) throws DataApiException;
}

