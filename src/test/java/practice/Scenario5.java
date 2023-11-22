package practice;

//import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5 {

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
		
		//Step 4 : Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 5 : Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 6 : Fill mandatory fields and organization
		driver.findElement(By.name("lastname")).sendKeys("TestK5");
		//address of organization look up icon
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		//handling parent window
		String parent = driver.getWindowHandle();
				
		//Handling child window
		
		Set<String> child = driver.getWindowHandles();
		for(String b:child)
		{
			driver.switchTo().window(b);
		}
		driver.findElement(By.linkText("TestOrg3")).click();
		Thread.sleep(2000);
		
		driver.switchTo().window(parent);
		
		//Step 7 : Save create contact page
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		
		//Step 8 : Validate
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(contactHeader.contains("TestK5"))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 9 : Logout
		WebElement user = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		Actions a = new Actions(driver);
		a.moveToElement(user).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		System.out.println("Signed out succesfully");
		
	}

}
