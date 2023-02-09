package com.deloitte.excel.report;
import com.deloitte.publish.PublisherException;
import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportAllReport {

    public File generateReport(Context context, String selectedEntityId) {
        System.out.println("Start Export All Report... ");

        ExportAllExcelContent exportAllExcelContent = new ExportAllExcelContent();

        //write Excel file
        String excelFileName = "conflict-export-all-report.xlsx";
        File file = new File(excelFileName);
        try {
            //MDM
            MDMReportDefinition mDMReportDefinition = new MDMReportDefinition();
            exportAllExcelContent.write(mDMReportDefinition.getColumnDefinitions(), mDMReportDefinition.getDataModel(context, selectedEntityId));

            //Paradigm
            ParadigmReportDefinition paradigmReportDefinition = new ParadigmReportDefinition();
            exportAllExcelContent.write(paradigmReportDefinition.getColumnDefinitions(), paradigmReportDefinition.getDataModel(context, selectedEntityId));

            //DCCS
            DCCSReportDefinition dCCSReportDefinition = new DCCSReportDefinition();
            exportAllExcelContent.write(dCCSReportDefinition.getColumnDefinitions(), dCCSReportDefinition.getDataModel(context, selectedEntityId));

            //BR
            BRReportDefinition bRReportDefinition = new BRReportDefinition();
            exportAllExcelContent.write(bRReportDefinition.getColumnDefinitions(), bRReportDefinition.getDataModel(context, selectedEntityId));

            System.out.println("Writing data to excel file <" + excelFileName + ">");
            FileOutputStream outputStream = new FileOutputStream(file);

            exportAllExcelContent.close(outputStream);

            outputStream.flush();
            outputStream.close();

            System.out.println("\nData is successfully written to excel file <" + file.getAbsolutePath() + ">.");
        } catch (IOException | DataApiException e) {
            e.printStackTrace();
            throw new PublisherException("Unable to generate Export All report", e);
        }

        System.out.println("End Export All Report... ");

        return file;
    }
}
