package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class ExcelUtil {

    private ExcelUtil() {
        // Prevent object creation (Utility class)
    }

    /**
     * Reads test data from Excel and returns a 2D Object array
     * @param sheetName - Sheet name in Excel
     * @return 2D Object[][] data
     */
    public static Object[][] getTestData(String sheetName) throws IOException {
        String excelPath = Paths.get(System.getProperty("user.dir"), "testdata", "CatalogData.xlsx.xlsx").toString();

        try (FileInputStream fis = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet '" + sheetName + "' not found in Excel file!");
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[totalRows - 1][totalCols];

            DataFormatter formatter = new DataFormatter(); // handles numeric/string types safely

            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }
            return data;
        }
    }
}
