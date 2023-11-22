package practice;

//import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario4 {

	public static void main(String[] args) throws Throwable {
		//Step 1: Launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Step 2 : Load the Application
		driver.get("http://localhost:8888/");
		
		//Step 3 : Login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		Thread.sleep(2000);
		driver.findElement(By.id("submitButton")).submit();
		
		//Step 4 : Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5 : Click on create Organizations look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 6 : Fill mandatory fields and industry and Type dropdowns
		driver.findElement(By.name("accountname")).sendKeys("TestOrg3");
		
		//address of industry drop down
		WebElement dropdownind = driver.findElement(By.name("industry"));
		//handling drop down
		Select s = new Select(dropdownind);
		s.selectByValue("Energy");
		
		//address of type drop down
		WebElement dropdowntype = driver.findElement(By.name("accounttype"));
		//handling drop down
		Select st = new Select(dropdowntype);
		st.selectByValue("Customer");
		
		Thread.sleep(2000);
		
		//Step 7 : Save create Organizations page
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		
		//Step 8 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains("TestOrg3"))
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
