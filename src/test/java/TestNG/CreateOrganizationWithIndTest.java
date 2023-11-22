package TestNG;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationinfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithIndTest {
	@Test
	public void CreateNewOrganizationWithIndTest() throws IOException, InterruptedException {
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
		String ORGANISATIONNAME =eUtil.readDataFromExcelFile("Organizations",4,2)+jUtil.getRandomNumber();
		String INDUSTRYNAME = eUtil.readDataFromExcelFile("Organizations",4,3);
		
		//Step2 : launch the browser - polymorphism - run time - driver
		
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
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4 :Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLink();
		
		//Step 5 : Click on create Organization look up 
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImg();
		
		//Step 6 : Create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGANISATIONNAME,INDUSTRYNAME);
		
		//Step 7 : Validate
		OrganizationinfoPage oip = new OrganizationinfoPage(driver);
		String orgheader = oip.getOrgHeader();
		
		if(orgheader.contains(ORGANISATIONNAME))
		{
			System.out.println(orgheader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 8 : Logout
		hp.logoutOfApp(driver);
		
		//Step 9 : close the application
		driver.quit();


	}

}
