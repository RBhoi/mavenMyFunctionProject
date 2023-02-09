package com.deloitte.excel.report;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class BRReportDefinition implements ReportDefinition {

    private final List<ColumnDefinition> columnDefinitions;
    private final String soqlQuery;

    public BRReportDefinition() {
        this.columnDefinitions = new ArrayList<>();
        columnDefinitions.add(new ColumnDefinition("Id", "Id", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Name", "Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Case_ID__c", "Case ID", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Party_Searched__c", "Party Searched", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Match_Found__c", "Match Found", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Role__c", "Role", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_BR_Category__c", "BR Category", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_BR_Sub_Type__c", "Br Sub Type", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_BR_Description__c", "BR Description", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Related_MBR__c", "Related BR", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Partner_Contact__c", "Partner", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Status__c", "Status", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Date__c", "Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Country_Name__c", "Country of entity", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Case_Manager__c", "Submitter / manager name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Is_Inbound__c", "Is it an inbound conflict check?", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Sending_Member_Firm__c", "Sending Member Firm", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Split_fee_type__c", "Split Fee type", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Match_Type__c", "Match Relevancy", ColumnType.STRING));

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("Select ");
        Iterator<ColumnDefinition> iterator = columnDefinitions.iterator();
        while(iterator.hasNext()) {
            ColumnDefinition columnDefinition = iterator.next();
            queryBuilder.append(columnDefinition.getId());
            if(iterator.hasNext()) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" FROM GCCS_BR_Paradigm_Federated_Search_Result__c where GCCS_Federated_Search_Entity_Junction_ID__c = ");
        soqlQuery = queryBuilder.toString();
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public DataIterator getDataModel(Context context, String entityId) {
        String currentSoqlQuery = soqlQuery + "'" + entityId + "' ";
        return new BaseDataIterator(context, currentSoqlQuery, "BR");
    }
}

