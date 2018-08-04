package week6.day1;


import org.testng.annotations.Test;
//import wdMethods.Annotations;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData   {
	
	public Object[][] ExcelLearnin(String fileName) throws IOException{	
		//going to the file path
		XSSFWorkbook wBook = new XSSFWorkbook("./data/"+fileName+".xlsx");
		//going to the sheet
		XSSFSheet sheet = wBook.getSheetAt(0);
		//getting the row count
		int rowCount = sheet.getLastRowNum();
		//getting the column count
		int colCount = sheet.getRow(0).getLastCellNum();
		//reading the data from the cell
		System.out.println("rowCount:"+rowCount);
		System.out.println("colCount: "+colCount);
		Object[][] testData = new Object[rowCount][colCount];
		for (int i=1;i<=rowCount;i++)
		{
			XSSFRow row = sheet.getRow(i);
			for (int j=0;j<colCount;j++)
			{
				try {
					XSSFCell cell = row.getCell(j);
					testData[i-1][j] = cell.getStringCellValue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					testData[i-1][j]= "";
				}
				//System.out.println(testData);
			}
		}
		wBook.close();
		return testData;
	}

}
