package wdMethods;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import week6.day1.ExcelData;


public class Annotations extends SeMethods{
	public String excelFileName,testName,testDesc,category,author,moduleName;
	@BeforeSuite
	public void startSuite() {
		beginResult();
	}
	
	@BeforeClass
	public void startTest() {
		startTest(testName, testDesc);
	}

	@Parameters({"url","username","password"})
	@BeforeMethod(groups= {"smoke","sanity"})
	public void login(String url, String uName, String pwd) {
		startTestIteration(moduleName,author,category);
		startApp("chrome", url);
		type(locateElement("id", "username"), uName);
		type(locateElement("id", "password"), pwd);
		click(locateElement("class", "decorativeSubmit"));
		click(locateElement("linktext", "CRM/SFA"));
	}
	
	@AfterMethod(groups= {"smoke","sanity"})
	public void closeApp() {
		closeBrowser();
	}
	
	@AfterSuite
	public void stopSuite() {
		endResult();
	}
	
	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {		
		ExcelData excel = new ExcelData();
		return excel.ExcelLearnin(excelFileName);		
	}
	
	
	
}





