package dev.dactech.booksmanagement.infrastructure.excel;

import dev.dactech.booksmanagement.domain.book.dto.excel.ExportInventoryExcel;
import dev.dactech.booksmanagement.domain.book.dto.excel.ExportOverdueExcel;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

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
    public static String writeInventoryBookToExcel(Header header, List<ExportInventoryExcel> data) throws IOException {
        Workbook workbook = getWorkbook("", Excel.ExcelType.XLSX);
        Sheet sheet = workbook.createSheet();

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

        String outputPath = "C:/Users/Admin/Downloads/"+ UUID.randomUUID() + ".xlsx";
        OutputStream os;
        try {
            os = new FileOutputStream(outputPath);
            workbook.write(os);
            os.close();
            workbook.close();
            return outputPath;
        } catch (IOException e) {
            throw new ApiException(MessageCode.ERROR_WRITE_EXCEL_FILE);
        }
    }
    public static String writeOverdueToExcel(Header header, List<ExportOverdueExcel> data) throws IOException {
        Workbook workbook = getWorkbook("", Excel.ExcelType.XLSX);
        Sheet sheet = workbook.createSheet();

        setHeader(sheet, header);
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);

        Row row;
        int index = 1;
        for (ExportOverdueExcel item : data){
            row = sheet.createRow(index);

            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getId());

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getStudentCode());

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getBookId());

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getBookName());

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getStartTime());

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getExpiredDate());

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(item.getNumOfDayOverdue());

            index++;
        }
        for (int i = 0; i < header.getTitle().size(); i++){
            sheet.autoSizeColumn(i);
        }

        String outputPath = "C:/Users/Admin/Downloads/"+ UUID.randomUUID() + ".xlsx";
        OutputStream os;
        try {
            os = new FileOutputStream(outputPath);
            workbook.write(os);
            os.close();
            workbook.close();
            return outputPath;
        } catch (IOException e) {
            throw new ApiException(MessageCode.ERROR_WRITE_EXCEL_FILE);
        }
    }
}
