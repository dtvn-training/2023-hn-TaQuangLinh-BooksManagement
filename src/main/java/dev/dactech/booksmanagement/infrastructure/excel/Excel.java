package dev.dactech.booksmanagement.infrastructure.excel;

import dev.dactech.booksmanagement.domain.book.dto.excel.ExportInventoryExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Excel {
    public enum ExcelType{
        XLSX,
        XLS;
    }
    public static Workbook getWorkbook(String excelFilePath, ExcelType type) throws IOException {
        if (excelFilePath == null || excelFilePath.isEmpty()){
            if (type == ExcelType.XLSX)return new XSSFWorkbook();
            else if (type == ExcelType.XLS)return new HSSFWorkbook();
        }
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        if (excelFilePath.endsWith("xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            return new HSSFWorkbook(inputStream);
        } else
        {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
    }
    public static void setHeader(Sheet sheet, Header header){
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        Row row = sheet.createRow(0);
        int numberColumn = header.getTitle().size();

        for (int i = 0; i < numberColumn; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(header.getTitle().get(i));
            cell.setCellStyle(cellStyle);
        }

    }
    public static void writeInventoryBookToExcel(Sheet sheet, Header header, List<ExportInventoryExcel> data){
        setHeader(sheet, header);
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);

        Row row;
        int index = 1;
        for (ExportInventoryExcel item : data){

            row = sheet.createRow(index);

            Cell cell = row.createCell(0);
            cell.setCellValue(item.getId());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(1);
            cell.setCellValue(item.getTitle());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(item.getCategory());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue(item.getAuthors());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(item.getQuantity());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            cell.setCellValue(item.getDateAdded());
            cell.setCellStyle(cellStyle);

            cell = row.createCell(6);
            cell.setCellValue(item.getImage());
            cell.setCellStyle(cellStyle);
            index++;
        }
        for (int i = 0; i < header.getTitle().size(); i++){
            sheet.autoSizeColumn(i);
        }
    }
}
