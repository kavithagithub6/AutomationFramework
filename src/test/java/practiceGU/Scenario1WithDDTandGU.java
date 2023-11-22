package practiceGU;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import GenericUtilities.ExcelFileUtility;
//import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1WithDDTandGU {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Create Object of all utilities
		//ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		//JavaUtility jUtil = new JavaUtility();
		WebDriver driver = null;
		
		//Step 1 : Read all the required data
		//Common data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		//Test Data
		//String LASTNAME = eUtil.readDataFromExcelFile("Contacts",7,2);
				
		//Step 2: Launch the browser
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

		//Step 3 : Login to the application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		Thread.sleep(2000);
		driver.findElement(By.id("submitButton")).submit();
				
		//Step 4 : Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
				
		//Step 5 : Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Step 6 : Fill mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("TestK");
		Thread.sleep(2000);
				
		//Step 7 : Save create contact page
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Step 8 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains("TestK"))
		{
			System.out.println(contactHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
				
		//Step 9 : Logout
		WebElement user = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		wUtil.mouseOver(driver, user);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		System.out.println("Signed out succesfully");
		//driver.close();
				

	}

}
