package com.reto.backend.steps;

import com.reto.backend.entities.Cliente;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class ClienteItemReader implements ItemReader<Row> {

    private final Resource resource;
    private Iterator<Row> rowIterator;

    private boolean isInitialized = false;
    public ClienteItemReader() {
        this.resource = new ClassPathResource("archivos/data.xlsx");
    }

    @Override
    public Row read() throws Exception {
        initializeIterator();

        return rowIterator.hasNext() ? rowIterator.next() : null;
    }

    private void initializeIterator() throws IOException {
        if (!isInitialized) {
            try (InputStream inputStream = resource.getInputStream()) {
                String fileName = resource.getFilename();
                Workbook workbook;
                if (fileName != null && fileName.toLowerCase().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(inputStream);
                } else {
                    throw new IllegalArgumentException("Formato de archivo no compatible: " + fileName);
                }

                Sheet sheet = workbook.getSheet("Cliente");

                rowIterator = sheet.iterator();
                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }
                isInitialized = true;
            }
        }
    }
}
