package com.deloitte.excel.art;

import com.deloitte.publish.PublisherException;
import com.salesforce.functions.jvm.sdk.Context;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArtReport {

    public static File generateReport(Context context, String selectedEntityId) {
        System.out.println("Start Art Report... ");

        ArtReportExcelContent artReportExcelContent = new ArtReportExcelContent();

        //write Excel file
        String excelFileName = "conflict-art-report.xlsx";
        File file = new File(excelFileName);
        try {
            //MDM
            ArtMDMReportDefinition mDMReportDefinition = new ArtMDMReportDefinition();

            //Write headers only one time, take from one knowing that they are all the same
            int startRowNumber = artReportExcelContent.writeHeader(mDMReportDefinition.getColumnDefinitions());

            startRowNumber = artReportExcelContent.write(mDMReportDefinition.getColumnDefinitions(),
                    mDMReportDefinition.getDataModel(context, selectedEntityId), startRowNumber);

            //Paradigm
            ArtParadigmReportDefinition paradigmReportDefinition = new ArtParadigmReportDefinition();
            startRowNumber = artReportExcelContent.write(paradigmReportDefinition.getColumnDefinitions(),
                    paradigmReportDefinition.getDataModel(context, selectedEntityId), startRowNumber);

            //DCCS
            ArtDCCSReportDefinition dCCSReportDefinition = new ArtDCCSReportDefinition();
            startRowNumber = artReportExcelContent.write(dCCSReportDefinition.getColumnDefinitions(),
                    dCCSReportDefinition.getDataModel(context, selectedEntityId), startRowNumber);

            //BR
            ArtBRReportDefinition bRReportDefinition = new ArtBRReportDefinition();
            startRowNumber = artReportExcelContent.write(bRReportDefinition.getColumnDefinitions(),
                    bRReportDefinition.getDataModel(context, selectedEntityId), startRowNumber);

            System.out.println("Writing data to excel file <" + excelFileName + ">");
            FileOutputStream outputStream = new FileOutputStream(file);

            artReportExcelContent.close(outputStream);

            outputStream.flush();
            outputStream.close();

            System.out.println("\nArt - Data is successfully written to excel file <" + file.getAbsolutePath() + ">.");
        } catch (IOException | DataApiException e) {
            e.printStackTrace();
            throw new PublisherException("Unable to generate Art report", e);
        }

        System.out.println("End Art Report... ");

        return file;
    }
}
