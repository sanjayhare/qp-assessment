package com.grocery.service.impl;

import com.grocery.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {

    public List<Product> readProductsFromExcel(InputStream inputStream) {
        List<Product> products = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assumes the first sheet contains product data
            boolean isFirstRow = true;
            for (Row row : sheet) {
                // Skip header row
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                Product product = new Product();
                // Assuming columns: Name, Description, Price, Quantity
                product.setProductName(getCellValueAsString(row.getCell(0)));
                product.setProductDescription(getCellValueAsString(row.getCell(1)));
                product.setProductPrice(getCellValueAsBigDecimal(row.getCell(2)));
                product.setProductQuantity(Long.valueOf(getCellValueAsInt(row.getCell(3))));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }
        return products;
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }
    private Double getCellValueAsBigDecimal(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return Double.valueOf(cell.getNumericCellValue());
        }
        return 0.0;
    }
    private int getCellValueAsInt(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }
}
