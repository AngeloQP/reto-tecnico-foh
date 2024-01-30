package com.reto.backend.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class Utilitario {

    public static String getStringCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        return (cell != null && cell.getCellType() == CellType.STRING) ? cell.getStringCellValue() : Constantes.EMPTY_STRING;
    }

    public static String getNumericStringValue(Row row, int index) {
        Cell cell = row.getCell(index);
        return (cell != null) ? String.valueOf((int) cell.getNumericCellValue()) : Constantes.EMPTY_STRING;
    }
}
