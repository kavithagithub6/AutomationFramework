package DDT;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario4WithDDT {

	public static void main(String[] args) throws Throwable {
		//Step 1 : Read all the necessary data

		/* Read data from property file*/
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String BROWSER = p.getProperty("browser");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		/* Read data from excel data*/
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String ORGNAME = wb.getSheet("Organizations").getRow(7).getCell(2).getStringCellValue();
		String INDUSTRY = wb.getSheet("Organizations").getRow(7).getCell(3).getStringCellValue();
		String TYPE = wb.getSheet("Organizations").getRow(7).getCell(4).getStringCellValue();
		
		//Step 2 : Launch the browser
		WebDriver driver = null;
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Step 3 : Load the Application
		driver.get(URL);
		
		//Step 4 : Login to the application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		Thread.sleep(2000);
		driver.findElement(By.id("submitButton")).submit();
		
		//Step 5 : Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 6 : Click on create Organizations look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 7 : Fill mandatory fields and industry and Type dropdowns
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//address of industry drop down
		WebElement dropdownind = driver.findElement(By.name("industry"));
		//handling drop down
		Select s = new Select(dropdownind);
		s.selectByValue(INDUSTRY);
		
		//address of type drop down
		WebElement dropdowntype = driver.findElement(By.name("accounttype"));
		//handling drop down
		Select st = new Select(dropdowntype);
		st.selectByValue(TYPE);
		
		Thread.sleep(2000);
		
		//Step 7 : Save create Organizations page
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		
		//Step 8 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains(ORGNAME))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 9 : Logout
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions a = new Actions(driver);
		a.moveToElement(ele).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		System.out.println("Signed out succesfully");


	}

}
