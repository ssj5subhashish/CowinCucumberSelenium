package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SpreadSheetWriter {

    public static Workbook workbook = new HSSFWorkbook();

    public static void setCellValuesStringList(String path, List<String[]> cellValues) throws IOException {
        Sheet sheet = workbook.createSheet();
        FileOutputStream out = new FileOutputStream(new File(path));
        Iterator<Row> rowIterator = sheet.iterator();
        int listIndex = 0;
        while (!rowIterator.hasNext()) {
            if (listIndex == cellValues.size()) break;
            String[] strArr = cellValues.get(listIndex);
            Row nextRow = sheet.createRow(sheet.getLastRowNum() + 1);
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int arrayIndex = 0;
            while (!cellIterator.hasNext()) {
                if (arrayIndex == strArr.length) break;
                Cell cell = nextRow.createCell(nextRow.getLastCellNum() + 1);
                cell.setCellValue(strArr[arrayIndex]);
                arrayIndex++;
            }
            listIndex++;
        }
        workbook.write(out);
    }
}
