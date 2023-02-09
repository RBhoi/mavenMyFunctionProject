package com.deloitte.excel.report;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DCCSReportDefinition implements ReportDefinition {

    private final List<ColumnDefinition> columnDefinitions;
    private final String soqlQuery;

    public DCCSReportDefinition() {
        this.columnDefinitions = new ArrayList<>();
        columnDefinitions.add(new ColumnDefinition("Id", "Id", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Name", "Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_DCCSRequestID__c", "DCCS ID", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_PartySearched__c", "Party Searched", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_EntityMatchFound__c", "Match Found", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_EntityRole__c", "Role", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Entity_Side__c", "Side", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Business_Relationship_Type__c", "Business", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Business_NT1_NT2__c", "NT1", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Engagement_Description__c", "Engagement Description", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Status__c", "Status", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Date__c", "Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Result_Determination__c", "Result Outcome", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Match_Relevancy__c", "Match Relevancy", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Risk_Alert__c", "Risk Alert", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Country_Name__c", "Country of entity", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Referral_from_another_MF_MF_Name__c", "Referral from another MF? MF Name", ColumnType.STRING));

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
        queryBuilder.append(" FROM GCCS_DCCS_Federated_Search_Result__c where GCCS_Federated_Search_Entity_Junction_ID__c = ");
        soqlQuery = queryBuilder.toString();
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public DataIterator getDataModel(Context context, String entityId) {
        String currentSoqlQuery = soqlQuery + "'" + entityId + "' ";
        return new BaseDataIterator(context, currentSoqlQuery, "DCCS");
    }
}

