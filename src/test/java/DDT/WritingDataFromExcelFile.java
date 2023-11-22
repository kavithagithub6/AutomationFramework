package DDT;


import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataFromExcelFile {

	public static void main(String[] args) throws Throwable {
		//Step 1: Open the document in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//Step 2 : Create a Workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3 : writing data to excel
		wb.getSheet("Contacts").createRow(12).createCell(1).setCellValue("Advance selenium");
		
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		wb.write(fos);
		wb.close();

	}

}
