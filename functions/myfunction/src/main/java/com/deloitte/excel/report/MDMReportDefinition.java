package com.deloitte.excel.report;
import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class MDMReportDefinition implements ReportDefinition {

    private final List<ColumnDefinition> columnDefinitions;
    private final String soqlQuery;

    public MDMReportDefinition() {
        this.columnDefinitions = new ArrayList<>();
        columnDefinitions.add(new ColumnDefinition("Id", "Id", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Name", "Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_PartySearched__c", "Party Searched", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_EntityMatchFound__c", "Match Found", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_RuleSet__c", "Rule Set", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Designation__c", "Designation", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_GUP_Name__c", "GUP Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_PartnerContact__c", "Partner", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Result_Determination__c", "Result Outcome", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Designation_Description__c", "Designation Description", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Country_Name__c", "Country of entity", ColumnType.STRING));

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
        queryBuilder.append(" FROM GCCS_MDM_Federated_Search_Result__c where GCCS_Federated_Search_Entity_Junction_ID__c = ");
        soqlQuery = queryBuilder.toString();
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public DataIterator getDataModel(Context context, String entityId) {
        String currentSoqlQuery = soqlQuery + "'" + entityId + "' ";
        return new BaseDataIterator(context, currentSoqlQuery, "MDM");
    }
}
