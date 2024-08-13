package com.example.demo.utils;

import com.example.demo.model.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component // sử dụng thư viện Apache POI

public class EXCELFileReader implements IFileReader{
    @Override
    public List<Book> readFile(String path) {
        List<Book> books = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path)) {
            Workbook workbook = null;

            if (path.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (path.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }

            Sheet sheet = workbook.getSheetAt(0);
            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                Book.BookBuilder bookBuilder = Book.builder();

                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            bookBuilder.id((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            bookBuilder.title(getCellValueAsString(cell));
                            break;
                        case 2:
                            bookBuilder.author(getCellValueAsString(cell));
                            break;
                        case 3:
                            bookBuilder.year((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                }

                books.add(bookBuilder.build());
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }

    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
