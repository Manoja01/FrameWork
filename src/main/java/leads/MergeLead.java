package leads;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import wdMethods.SeMethods;

public class MergeLead extends SeMethods{

	@Test
	public void login() throws InterruptedException {
		
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement eleUserName = locateElement("id", "username");
		type(eleUserName, "DemoSalesManager");
		WebElement elePassword = locateElement("id","password");
		type(elePassword, "crmsfa");
		WebElement eleLogin = locateElement("class","decorativeSubmit");
		click(eleLogin);
		WebElement eleCRMSFA = locateElement("linktext","CRM/SFA");
		click(eleCRMSFA);
		WebElement elecreatelead = locateElement("linktext","Create Lead1");//changed by anitha
		click(elecreatelead);
		WebElement eleMergeLead = locateElement("xpath","//a[text()='Merge Leads']");
		click(eleMergeLead);
		WebElement eleIcon1 = locateElement("xpath", "//div[@class='subSectionBlock']//following::a/img");
		click(eleIcon1);//changed by Manoja.Changed the Element Name.
		switchToWindow(1);
		maximize();
		WebElement eleLeadId = locateElement("xpath", "//div/input[@name='id']");
		type(eleLeadId, "10089");	//changed by Manoja from 88 to 89.	
		WebElement eleFindLead = locateElement("xpath","//button[text()='Find Leads']");
		click(eleFindLead);
		Thread.sleep(5000);
		WebElement eleClickLead = locateElement("xpath","//div/a[text()='10088']");
		click(eleClickLead);
		Thread.sleep(5000);
		switchToWindow(0);
		Thread.sleep(5000);
		WebElement eleClickMerge = locateElement("xpath", "//td/a[text()='Merge']");
		click(eleClickMerge);
		closeBrowser();
		
		
		
		
				
		
	}
	
}






