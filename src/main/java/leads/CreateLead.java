package leads;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import wdMethods.Annotations;


public class CreateLead extends Annotations{

	@Test(invocationCount=2, timeOut=2000)
	public void createLead() {		
		WebElement elecreatelead = locateElement("linktext","Create Lead");
		click(elecreatelead);
		WebElement eleCompanyName = locateElement("createLeadForm_companyName");
		type(eleCompanyName, "Accenture");//changed by anitha
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
		
		
	}
	
}






