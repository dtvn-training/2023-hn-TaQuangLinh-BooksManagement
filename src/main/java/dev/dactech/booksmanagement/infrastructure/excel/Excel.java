package dev.dactech.booksmanagement.infrastructure.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Excel {
    enum ExcelType{
        XLSX,
        XLS;
    }
    private static Workbook getWorkbook(String excelFilePath, ExcelType type) throws IOException {
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        if (excelFilePath.endsWith("xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            return new HSSFWorkbook(inputStream);
        } else if (excelFilePath == null || excelFilePath.isEmpty()){
            if (type == ExcelType.XLSX)return new XSSFWorkbook();
            else if (type == ExcelType.XLS)return new HSSFWorkbook();
        }else
        {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return null;
    }
}
