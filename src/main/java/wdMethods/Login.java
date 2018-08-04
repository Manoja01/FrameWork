package wdMethods;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import wdMethods.SeMethods;

public class Login extends SeMethods{

	@Test
	public void login() {
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement eleUserName = locateElement("id", "username");
		type(eleUserName, "DemoSalesManager");
		WebElement elePassword = locateElement("id","password");
		type(elePassword, "crmsfa");
		WebElement eleLogin = locateElement("class","decorativeSubmit");
		click(eleLogin);
		WebElement eleCRMSFA = locateElement("linktext","CRM/SFA");
		click(eleCRMSFA);
		WebElement elecreatelead = locateElement("linktext","Create Lead");
		click(elecreatelead);
		WebElement eleCompanyName = locateElement("createLeadForm_companyName");
		type(eleCompanyName, "Infosys");
		WebElement eleFName = locateElement("createLeadForm_firstName");
		type(eleFName, "Manoja");
		WebElement eleLName = locateElement("createLeadForm_lastName");
		type(eleLName, "Rajan");
		WebElement eleIndustry = locateElement("createLeadForm_industryEnumId");
		selectDropDownUsingText(eleIndustry, "Insurance");		
		WebElement eleSubmit = locateElement("class","smallSubmit");
		click(eleSubmit);		
		WebElement textCompVal = locateElement("viewLead_firstName_sp");
		verifyExactText(textCompVal, "Manoja");
		closeBrowser();
		
	}
	
}






