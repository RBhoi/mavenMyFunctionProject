package com.deloitte.excel;

import com.salesforce.functions.jvm.sdk.data.Record;
import com.salesforce.functions.jvm.sdk.data.error.DataApiException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class ExcelContent {
    protected SXSSFWorkbook workbook;

    public ExcelContent() {
        this.workbook = new SXSSFWorkbook();
    }

    protected int writeData(SXSSFSheet sheet, List<ColumnDefinition> columnDefinitions, DataIterator dataIterator, int startRowNum) throws DataApiException {
        int rowNum = startRowNum;

        //write body/row
        while(dataIterator.hasNext()) {
            List<Record> records = dataIterator.next();

            System.out.println("Function successfully queried records => " + records.size());

            DataModel dataModel = dataIterator.getModel();
            for(Record record : records) {
                Row bodyRow = sheet.createRow(rowNum++);
                int bodyCellNum = 0;

                for (ColumnDefinition columnDefinition : columnDefinitions) {
                    Cell cell = bodyRow.createCell(bodyCellNum++);
                    writeRowCell(cell, record, columnDefinition, dataModel);
                }
            }
        }
        return rowNum;
    }

    protected void writeRowCell(Cell cell, Record record, ColumnDefinition columnDefinition, DataModel dataModel) {
        ColumnType columnType = columnDefinition.getColumnType();

        Object obj = dataModel.getValue(record, columnType, columnDefinition.getId());

        writeCell(cell, columnType, obj);
    }

    protected void writeCell(Cell cell, ColumnType columnType, Object obj) {
        if(obj == null) {
            cell.setCellValue("");
        } else {
            if (ColumnType.INT.equals(columnType)) {
                cell.setCellValue((Integer) obj);
            } else if (ColumnType.DOUBLE.equals(columnType)) {
                cell.setCellValue((Double) obj);
            } else if (ColumnType.STRING.equals(columnType)) {
                cell.setCellValue((String) obj);
            }
        }
    }

    public void close(OutputStream outputStream) throws IOException {
        workbook.write(outputStream);
        workbook.dispose();
        workbook.close();
    }
}

