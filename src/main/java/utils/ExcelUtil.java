package utils;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelUtil {
	
	static {
        // Fix Zip Bomb error
        ZipSecureFile.setMinInflateRatio(0.001);
    }

	public ExcelUtil() {
		// Prevent object creation (Utility class)
		
	}

	/**
	 * Reads test data from Excel and returns a 2D Object array
	 * 
	 * @param sheetName - Sheet name in Excel
	 * @return 2D Object[][] data
	 */
	public static Object[][] getTestData(String fileName, String sheetName) throws IOException {
		String excelPath = Paths.get(System.getProperty("user.dir"), "testdata", fileName).toString();

		try (FileInputStream fis = new FileInputStream(excelPath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException(" Sheet '" + sheetName + "' not found in Excel file!" + fileName);
			}

			int totalRows = sheet.getPhysicalNumberOfRows();
			int totalCols = sheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[totalRows - 1][totalCols];

			DataFormatter formatter = new DataFormatter();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			for (int i = 1; i < totalRows; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < totalCols; j++) {
					Cell cell = row.getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell,evaluator);
				}
			}
			return data;
		}
	}

	public static void writeData(String fileName, String sheetName, int rowNum, int colNum, Object value)
			throws IOException {

		String excelPath = Paths.get(System.getProperty("user.dir"), "testdata", fileName).toString();

		FileInputStream fis = new FileInputStream(excelPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		Cell cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue(value.toString());
		}

		fis.close();

		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.setForceFormulaRecalculation(true);
		workbook.write(fos);

		fos.close();
		workbook.close();
	}
	
	public static void generateDynamicLots(String file, String sheet,String commodity) throws IOException {

	    String baseName = "AutoTest" +
	            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

	    for (int i = 6; i <= 15; i++) {

	        String lotName = baseName + "/"+commodity+"/Lot-" + (i-5);

	        // Group No column
	        ExcelUtil.writeData(file, sheet, i, 2, lotName);

	        // Description column
	        ExcelUtil.writeData(file, sheet, i, 3, lotName);
	        
	        System.out.println("data successfully entered in the sheet row number:"+i);
	    }
	}
}
