package common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataHelper {
	private String filePath;
	private String sheetName;

	public DataHelper(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
	}

	public List<HashMap<String, String>> data() {
		List<HashMap<String, String>> mydata = new ArrayList<>();
		try {
			FileInputStream fs = new FileInputStream(this.filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(this.sheetName);
			Row HeaderRow = sheet.getRow(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<String, String>();
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) {
					case STRING:
						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;
					}
				}
				mydata.add(currentHash);
			}
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mydata;
	}

	private void update(String field, int rowIndex, Object value, String Type) {
		int fieldIndex = 0;
		try {
			FileInputStream fs = new FileInputStream(this.filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(this.sheetName);
			Row HeaderRow = sheet.getRow(0);
			for (int j = 0; j < HeaderRow.getPhysicalNumberOfCells(); j++) {
				Cell currentCell = HeaderRow.getCell(j);
				if (currentCell.getStringCellValue().equals(field)) {
					fieldIndex = j;
				}
			}
			Cell updateCell = sheet.getRow(rowIndex).getCell(fieldIndex);
			if (updateCell == null) {
				updateCell = sheet.getRow(rowIndex).createCell(fieldIndex);
			}
			if (Type.toLowerCase().equals("string"))
				updateCell.setCellValue(value.toString());
			else if (Type.toLowerCase().equals("number"))
				updateCell.setCellValue((double) value);

			FileOutputStream fileOut = new FileOutputStream(this.filePath);
			workbook.write(fileOut);

			fileOut.close();
			workbook.close();

			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(String field, int rowIndex, String value) {
		this.update(field, rowIndex, value, "string");
	}

	public void update(String field, int rowIndex, double value) {
		this.update(field, rowIndex, value, "number");
	}

	public static void main(String[] args) {
		DataHelper dataHelper = new DataHelper("C:\\TestData\\Book1.xlsx", "Sheet1");
		dataHelper.update("status", 1, "0");

		System.out.println(dataHelper.data().get(1).get("email"));
	}

	public String getOneMatchCell(String getColName, String searchColName, String searchValue) {
		String value = "";
		try {
			int i = 1;
			List<HashMap<String, String>> data = data();
			for (HashMap<String, String> row : data) {
				if (row.get(searchColName).equals(searchValue)) {
					value = i + ";" + row.get(getColName);
					break;
				}
				i++;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;
	}
}
