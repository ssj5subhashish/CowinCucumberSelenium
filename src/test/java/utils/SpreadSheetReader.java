package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpreadSheetReader {

    public static List<String> singleColumnSpreadsheetReader(String path, String sheetName) throws IOException {
        Sheet sheet = getSheetFromFile(path, sheetName);
        List<String> sheetContent = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(CellType.STRING);
                sheetContent.add(cell.getStringCellValue());
            }
        }
        return sheetContent;
    }

    public static Sheet getSheetFromFile(String path, String sheetName) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        Workbook workbook = new HSSFWorkbook(inputStream);
        return workbook.getSheet(sheetName);
    }
}
