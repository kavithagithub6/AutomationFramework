package ContactTests;

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
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationinfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrg {

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
		cnop.createNewOrganization(ORGNAME);
		
		//Step 7 : validate for Organization
		OrganizationinfoPage oip = new OrganizationinfoPage(driver);
		String orgheader = oip.getOrgHeader();
		
		if(orgheader.contains(ORGNAME))
		{
			System.out.println(orgheader);
			System.out.println("Organization added");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 8 :Navigate to Contacts link
		hp.clickOnCOntactsLink();
		
		//Step 9 : Click on create contact look up 
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		//Step 10 : Create new contact with organization
		CreateNewContactPage cncp= new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);
		
		//Step 11 : Validate
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactheader = cip.getContactHeader();
		
		if(contactheader.contains(LASTNAME))
		{
			System.out.println(contactheader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 12 : Logout
		hp.logoutOfApp(driver); 
		
		//Step 13 : close the application
		driver.quit();


	}

}
