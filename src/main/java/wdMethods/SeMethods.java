package wdMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class SeMethods extends Reporter implements WdMethods{
	public static RemoteWebDriver driver ;
	int i = 1;
	public void startApp(String browser, String url)
	{
		try {
			if (browser.equalsIgnoreCase("chrome")) 
			{
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();			
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckoriver.exe");
				driver = new FirefoxDriver();		
			}
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//			System.out.println();
			reportStep("The Browser "+browser+" is Launched Successfully", "Pass");
			
		} catch (WebDriverException e) 
		{
			System.err.println("WebDriver Exception has occurred");
			reportStep("WebDriver Exception has occurred", "Fail");
			throw new RuntimeException();
		}
		catch (Exception e) {
			reportStep("Run-time Exception has occurred", "Fail");
		}
		takeSnap();
	}


	public WebElement locateElement(String locator, String locValue) {
		try {
			switch (locator) {
			case "id": 	  return driver.findElementById(locValue);
			case "class": return driver.findElementByClassName(locValue);
			case "name": return driver.findElementByClassName(locValue);
			case "xpath": return driver.findElementByXPath(locValue);	
			case "linktext": return driver.findElementByLinkText(locValue);
			}
		}
		catch (NoSuchElementException e) {
			System.out.println("The element with locator "+locator+" and with value "+locValue+" not found.");
			throw new RuntimeException();
		} catch (WebDriverException e) {
			System.out.println("WebDriverException");
		}
		
		finally
		{
			takeSnap();
		}
		return null;
	}

	public WebElement locateElement(String locValue) {
		try 
		{
			return driver.findElementById(locValue);
		} 
		catch (WebDriverException e) 
		{
			System.err.println("WebDriver Exception has occurred");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Run-time Exception has occurred");
		}
		finally
		{
			takeSnap();
		}
		return null;
	}

	public void type(WebElement ele, String data)throws NullPointerException,NoSuchElementException {
		try {
//			ele.clear();
			ele.sendKeys(data);
//			System.out.println("The Data "+data+" is Entered Successfully");
			reportStep("The Data "+data+" is Entered Successfully", "Pass");
		}
		catch (WebDriverException e) 
		{
//			System.err.println("WebDriver Exception has occurred");
			reportStep("The data: "+data+" could not entered", "fail");
//			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("The data: "+data+" could not entered: "+e.getMessage());
			reportStep("The data: "+data+" could not entered", "fail");
		}
		finally
		{
			takeSnap();
		}
	}

	public void click(WebElement ele) {
		ele.click();
		System.out.println("The Element "+ele+" is clicked Successfully");
		takeSnap();		
	}

	public String getText(WebElement ele) {
		String gText=ele.getText();
		System.out.println("Text in GUI is" +gText);
		return gText ;
	}
	
	public String getAttribute(WebElement ele) {
		String gAttribute=ele.getAttribute("value");
		System.out.println("Text in GUI is" +gAttribute);
		return gAttribute ;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {		
		try {
			Select dd = new Select(ele);		
			dd.selectByVisibleText(value);
			System.out.println("The value selected in the value is :"+value);
		} 
		catch (WebDriverException e) {
			System.err.println("Webdriverexception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally {
			takeSnap();
		}
	}

	public void selectDropDownUsingIndex(WebElement ele, int index)
	{
		Select dd = new Select(ele);
		dd.selectByIndex(index);
	}

	public boolean verifyTitle(String expectedTitle) 
	{				
		try {
			String title = driver.getTitle();
			if (title == expectedTitle ) 
			{
				System.out.println("The tile "+title+"matches with the expected Title");	
			}
			else
			{
				System.out.println("The tile "+title+"does not matches with the expected Title");	
			}
		} catch (WebDriverException e) {
			System.err.println("Webdriverexception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		return false;
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		String a = ele.getText();
		try {
			if (a.equals(expectedText))
			{
				System.out.println("The entered text matches "+expectedText);	
			}
			else
			{
				System.out.println("The entered text doesn't match with the WebElement "+expectedText);
			}
		} 
		catch (WebDriverException e) {
			System.err.println("Webdriverexception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally {
			takeSnap();
		}
	}

	public void verifyPartialText(WebElement ele, String expectedText) 
	{
		try 
		{
			String partialText = ele.getText();
			if(expectedText.contains(partialText))
			{
				System.out.println("partial text  verification is passed ");
			}
			else
			{
				System.out.println("partial Text verification failed, expected is "+expectedText+" - actual is "+partialText);
			}
		}
		catch (WebDriverException e)
		{
			System.err.println("Webdriver exception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) 
		{
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally 
		{
			takeSnap();
		}

	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) 
	{
		try 
		{
			String exactAttribute = ele.getAttribute(value);
			if(exactAttribute==value)
			{
				System.out.println("partial text  verification is passed ");
			}
			else
			{
				System.out.println("partial Text verification failed, expected is "+exactAttribute+" - actual is "+value);
			}
		}
		catch (WebDriverException e)
		{
			System.err.println("Webdriver exception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) 
		{
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally 
		{
			takeSnap();
		}
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try 
		{
			String attributeValue = ele.getAttribute(attribute);
			if(attributeValue.contains(value))
			{
				System.out.println("partial text  verification is passed ");
			}
			else
			{
				System.out.println("partial Text verification failed, expected is "+attributeValue+" - actual is "+value);
			}
		}
		catch (WebDriverException e)
		{
			System.err.println("Webdriver exception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) 
		{
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally 
		{
			takeSnap();
		}



	}

	public void verifySelected(WebElement ele)
	{
		try {
			boolean selected = ele.isSelected();
			if (selected == true)
			{
				System.out.println("The searched value is displayed :"+selected);			
			}
			else
			{
				System.out.println("The searched value is not displayed :"+selected);			
			}
		} catch (WebDriverException e) {
			System.err.println("Webdriver exception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally {
			takeSnap();
		}
	}


	public void verifyDisplayed(WebElement ele) {
		try {
			boolean displayed = ele.isDisplayed();
			if (displayed == true)
			{
				System.out.println("The searched value is displayed :"+displayed);			
			}
			else
			{
				System.out.println("The searched value is not displayed :"+displayed);			
			}
		} catch (WebDriverException e) {
			System.err.println("Webdriverexception has occured");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Exception has occured");
			throw new RuntimeException();
		}
		finally {
			takeSnap();
		}
	}

	public void switchToWindow(int index) {
		Set<String> winHandle = driver.getWindowHandles();
		List<String> listWinHandle = new ArrayList<String>();
		listWinHandle.addAll(winHandle);
		int valueIndex = listWinHandle.size();
		System.out.println("WindowSize: "+valueIndex);		
		driver.switchTo().window(listWinHandle.get(index));	
	}

	public void switchToFrame(WebElement ele) 
	{
		driver.switchTo().frame(ele);		
	}

	public void acceptAlert() 
	{
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() 
	{
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText() 
	{
		try {
			String textAlert = driver.switchTo().alert().getText();
			System.out.println("The Alert text is :"+textAlert);
		} catch (WebDriverException e) {
			System.err.println("WebDriver Exception");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
		}
		return null;
	}

	public void takeSnap()
	{
		File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./snaps/img"+i+".png");
		try {
			FileUtils.copyFile(src, desc);
		} catch (IOException e) {

			e.printStackTrace();
		}
		i++;
	}

	public void closeBrowser() {
		driver.close();
	}

	public void closeAllBrowsers() {
		driver.quit();
	}

	public void maximize() 
	{
		driver.manage().window().maximize();
	}
	
	public void moveToElement(WebElement ele, WebElement ele1)
	{
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).pause(4000).click(ele1).perform();
	}
	
	public int convertInt(String value)
	{
		int result = Integer.parseInt(value);
		System.out.println(result);		
		return result;
		
	}
	

}
