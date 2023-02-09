package com.deloitte.excel.report;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ParadigmReportDefinition implements ReportDefinition {

    private final List<ColumnDefinition> columnDefinitions;
    private final String soqlQuery;

    public ParadigmReportDefinition() {
        this.columnDefinitions = new ArrayList<>();
        columnDefinitions.add(new ColumnDefinition("Id", "Id", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Name", "Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Case_ID__c", "Case ID", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Party_Searched__c", "Party Searched", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Entity_Match_Found__c", "Match Found", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Entity_Role__c", "Role", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Entity_Side__c", "Side", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Business_Involved__c", "Business", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Business_NT1_NT2__c", "NT1", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_EngagementDescription__c", "Engagement Description", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Partner_Contact_Primary_Contact__c", "Partner", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Status__c", "Status", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Date__c", "Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Result_Determination__c", "Result Outcome", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Match_Type__c", "Match Relevancy", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Risk_Alert__c", "Risk Alert", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Streamline_Check__c", "Streamline Check", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_GUP_ID__c", "GUP ID", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Expected_Close_Date__c", "Expected Close Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Opportunity_Start_Date__c", "Opportunity Start Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Project_End_Date__c", "Project End Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Chance_of_Win__c", "Chance of Win", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Recurring__c", "Recurring", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Opportunity_Name__c", "Opportunity Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Country_Name__c", "Country of entity", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Case_Manager__c", "Submitter / manager name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Is_Inbound__c", "Is it an inbound conflict check?", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Sending_Member_Firm__c", "Sending Member Firm", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Split_fee_type__c", "Split Fee type", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Fee_estimate__c", "Fee estimate", ColumnType.STRING));

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
        queryBuilder.append(" FROM GCCS_ParadigmConflict_Federated_Search__c where GCCS_Federated_Search_Entity_Junction_ID__c = ");
        soqlQuery = queryBuilder.toString();
    }

    @Override
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public DataIterator getDataModel(Context context, String entityId) {
        String currentSoqlQuery = soqlQuery + "'" + entityId + "' ";
        return new BaseDataIterator(context, currentSoqlQuery, "Paradigm");
    }
}

