package com.deloitte.excel;

import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.data.Record;
import com.salesforce.functions.jvm.sdk.data.RecordQueryResult;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;

import java.util.List;

public class BaseDataIterator implements DataIterator {
    protected final Context context;
    private final String soqlQuery;

    private final DataModel dataModel;
    private boolean done;

    protected RecordQueryResult recordQueryResult;
    private String name;

    public BaseDataIterator(Context context, String soqlQuery, String name) {
        this.context = context;
        this.soqlQuery = soqlQuery;
        this.dataModel = new DataModel();
        this.name = name;
    }

    @Override
    public boolean hasNext() {
        return !done;
    }

    @Override
    public List<Record> next() throws DataApiException {
        System.out.println("soqlQuery => " + soqlQuery);
        if(recordQueryResult == null) {
            recordQueryResult = context.getOrg().get().getDataApi().query(soqlQuery);
        } else {
            recordQueryResult = context.getOrg().get().getDataApi().queryMore(recordQueryResult);
        }
        done = recordQueryResult.isDone();
        System.out.println("Total size records => " + recordQueryResult.getTotalSize());

        List<Record> records = recordQueryResult.getRecords();
        System.out.println("records.size => " + records.size());
        return records;
    }

    @Override
    public long getTotalSize() {
        return recordQueryResult.getTotalSize();
    }

    @Override
    public DataModel getModel() {
        return dataModel;
    }

    @Override
    public String getName() {
        return name;
    }
}

