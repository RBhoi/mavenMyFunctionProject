package com.deloitte.excel.report;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

class ExportAllExcelContent extends ExcelContent {

    public void write(List<ColumnDefinition> columnDefinitions, DataIterator dataIterator) throws DataApiException {
        Instant startTime = Instant.now();
        System.out.println("Start Time " + dataIterator.getName() + ".records ==>" + startTime);

        SXSSFSheet sheet = workbook.createSheet(dataIterator.getName());

        int rowNum = 0;

        //write headers
        Row headerRow = sheet.createRow(rowNum++);
        int headerCellNum = 0;
        for(ColumnDefinition columnDefinition: columnDefinitions) {
            String name = columnDefinition.getName();
            Cell cell = headerRow.createCell(headerCellNum++);
            writeCell(cell, columnDefinition.getColumnType(), name);
        }

        //write body/row
        writeData(sheet, columnDefinitions, dataIterator, rowNum);

        Instant endTime = Instant.now();
        System.out.println("End Time " + dataIterator.getName() + ".records ==>" + endTime);

        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Query Execution time in Minitues===>"+timeElapsed.toMinutes());
    }

}
