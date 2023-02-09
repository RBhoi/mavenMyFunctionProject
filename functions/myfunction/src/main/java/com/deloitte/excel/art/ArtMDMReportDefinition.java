package com.deloitte.excel.art;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ArtMDMReportDefinition implements ReportDefinition {

    private final List<ColumnDefinition> columnDefinitions;
    private final String soqlQuery;

    public ArtMDMReportDefinition() {
        this.columnDefinitions = new ArrayList<>();
        columnDefinitions.add(new ColumnDefinition("", "Source System", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Record_Type_Name__c", "Record Type", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Entity Side", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Local Request/ Engagement ID #", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("Name", "Entity Name", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Role__c", "Entity Role", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Country_Name__c", "Country of Entity Location", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Is Entity an EU PIE? (Only applicable if entity is domiciled in the EU)", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Geography originating the check", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Detailed description of service or relationship", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Service Line/Offering  (Audit / ERS / FAS / Consulting / Tax / etc.)", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Partner_Contact__c", "Engagement Partner", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Engagement Manager/Delegate", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Engagement Status", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Estimated/Confirmed Completion Date", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Engagement Fee Basis", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Do Confidentiality Requirements Prevent this from being shared with the Audit Team", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("", "Work referral from another Geography", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_BR_Category__c", "Category of Business or Financial Relationship", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Comments__c", "Comments", ColumnType.STRING));
        columnDefinitions.add(new ColumnDefinition("GCCS_Date__c", "Submitted Date/Created Date", ColumnType.STRING));

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("Select ");
        Iterator<ColumnDefinition> iterator = columnDefinitions.iterator();
        while(iterator.hasNext()) {
            ColumnDefinition columnDefinition = iterator.next();
            if(!columnDefinition.getId().isEmpty()) {
                queryBuilder.append(columnDefinition.getId());
            }
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

