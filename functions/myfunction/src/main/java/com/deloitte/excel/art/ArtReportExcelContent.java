package com.deloitte.excel.art;

import com.deloitte.excel.*;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ArtReportExcelContent extends ExcelContent {
    private SXSSFSheet principalSheet;

    public int writeHeader(List<ColumnDefinition> columnDefinitions) {
        System.out.println("Art - Start write header");

        Instant startTime = Instant.now();
        int rowNumber = initializeReport(startTime, columnDefinitions);

        System.out.println("End - Start write header");
        return rowNumber;
    }

    public int write(List<ColumnDefinition> columnDefinitions, DataIterator dataIterator, int startRowNumber) throws DataApiException {
        Instant startTime = Instant.now();
        System.out.println("Start Time " + dataIterator.getName() + ".records ==>" + startTime);

        //write body/row
        int nextStartRowNumber = writeData(principalSheet, columnDefinitions, dataIterator, startRowNumber);

        Instant endTime = Instant.now();
        System.out.println("End Time " + dataIterator.getName() + ".records ==>" + endTime);

        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Query execution time in minutes ===>"+timeElapsed.toMinutes());

        return nextStartRowNumber;
    }

    private int initializeReport(Instant startTime, List<ColumnDefinition> columnDefinitions) {
        principalSheet = workbook.createSheet("ART Report");

        for(int i=0; i<= 20; i++) {
            principalSheet.setColumnWidth(i, 6000);
        }

        int rowNumber = 0;
        principalSheet.createRow(rowNumber++); //jump
        Row deloitteRow = principalSheet.createRow(rowNumber++);
        Cell cell1 = deloitteRow.createCell(0);
        Font font1 = workbook.createFont();
        font1.setColor(IndexedColors.WHITE.getIndex());
        font1.setFontHeightInPoints((short) 23);
        font1.setFontName("Verdana");
        font1.setBold(true);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font1);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SPARSE_DOTS);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("Deloitte.");

        principalSheet.addMergedRegion(new CellRangeAddress(
                0, //first row index in zero-based
                1, //last row index in zero-based
                0, //first column index in zero-based
                20  //last column index in zero-based
        ));

        Row row2 = principalSheet.createRow(rowNumber++);
        Font font2 = workbook.createFont();
        //font2.setColor(IndexedColors.LIGHT_GREEN.getIndex());
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFont(font2);
        cellStyle2.setFillBackgroundColor(IndexedColors.LIME.getIndex());
        cellStyle2.setFillPattern(FillPatternType.FINE_DOTS);

        for(int i=0; i<=20; i++) {
            Cell cell2 = row2.createCell(i);
            cell2.setCellValue("");
            cell2.setCellStyle(cellStyle2);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy")
                .withZone(ZoneId.systemDefault());
        String formattedInstant = formatter.format(startTime);

        Row row3 = principalSheet.createRow(rowNumber++);
        Cell cell3 = row3.createCell(0);
        Font font3 = workbook.createFont();
        font3.setColor(IndexedColors.BLACK.getIndex());
        font3.setFontHeightInPoints((short) 9);
        font3.setFontName("Verdana");
        font3.setBold(true);
        CellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setFont(font3);
        cell3.setCellStyle(cellStyle3);
        cell3.setCellValue("Conflict Check ID:CC-22.0003946       Report Date:" + formattedInstant);

        //write headers
        Row headerRow = principalSheet.createRow(rowNumber++);
        headerRow.setHeightInPoints(100);
        principalSheet.setColumnWidth(1, 6000);
        principalSheet.setColumnWidth(3, 6000);

        // Set up font
        Font font4 = workbook.createFont();
        font4.setColor(IndexedColors.BLACK.getIndex());
        font4.setBold(true);
        font4.setFontHeightInPoints((short) 9);
        font4.setFontName("Verdana");

        // set up background color
        CellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setFont(font3);
        //cellStyle4.setFillBackgroundColor(XSSFColor.);

        cellStyle4.setFillBackgroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        cellStyle4.setFillPattern(FillPatternType.SPARSE_DOTS);

        //added by Rameswari
        principalSheet.trackAllColumnsForAutoSizing();
        principalSheet.autoSizeColumn(30);

        int headerCellNum = 0;
        for(ColumnDefinition columnDefinition: columnDefinitions) {
            String name = columnDefinition.getName();
            Cell cell = headerRow.createCell(headerCellNum++);
            writeCell(cell, columnDefinition.getColumnType(), name);
        }

        return rowNumber;
    }
}

