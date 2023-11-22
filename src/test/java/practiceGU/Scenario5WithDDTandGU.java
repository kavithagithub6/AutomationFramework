package practiceGU;

import java.io.IOException;
//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.LoginPage;

public class Scenario5WithDDTandGU {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Create Object of all Utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriver driver = null;
		
		//Step 1 : Read all the required data
		//Common data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Test Data
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts",7,2);
		String ORGNAME = eUtil.readDataFromExcelFile("Contacts",7,3)+jUtil.getRandomNumber();
		
		//Step2 : launch the browser
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER +" launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER +" launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER +" launched");
		}
		else
		{
			System.out.println("Invalid browser name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.WaitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3 - Login to the application
		/*driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		Thread.sleep(2000);
		driver.findElement(By.id("submitButton")).submit();
		*/
		
		//using POM
		LoginPage lp = new LoginPage(driver);
		/*lp.getUserName().sendKeys(USERNAME);
		lp.getUserPassword().sendKeys(PASSWORD);
		lp.getLoginBtn().click();*/
		
		//using POM Business libraries
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4 : Navigate to Organizations
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5 : Click on create organization look up icon
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 6 : Create organization with mandatory field
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 7 : Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 8 : validate
		String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(ORGNAME))
		{
			System.out.println(orgHeader);
			System.out.println("Organization created");
		}
		else
		{
			System.out.println("Fail");
		}
		
		//Step 9 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 10 : Click on create contacts look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 11 : Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step 12 : click on Org look up image
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
									  //(img[@alt='Select'])[1]
		
		//Step 13: switch the control to child window
		wUtil.switchToWindow(driver,"Accounts");
		System.out.println("Switched to child window");
		
		//Step 14 : Search for org
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click(); 
		//driver.findElement(By.linkText(ORGNAME));
			
		//Step 15 : switch the control back to parent
		wUtil.switchToWindow(driver,"Contacts");
		System.out.println("Switched back to parent");
		
		//Step 16 : Save contacts
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 17 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 18 : Logout
		WebElement user = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		wUtil.mouseOver(driver, user);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		System.out.println("Signed out succesfully");
		
		
	}

}
