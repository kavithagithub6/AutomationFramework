package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{
	
	//Declaration
	@FindBy(linkText="Contacts")
	private WebElement ContactLink;
	
	@FindBy(linkText="Organizations")
	private WebElement OrgLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement userIcon;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	//Initialisation
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilisation
	public WebElement getContactLink() {
		return ContactLink;
	}

	public WebElement getOrgLink() {
		return OrgLink;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	//Business library
	/**
	 * This method will click on Organizations link
	 */
	public void clickOnOrgLink()
	{
		OrgLink.click();
	}
	
	/**
	 * This method will click on contacts link
	 */
	public void clickOnCOntactsLink()
	{
		ContactLink.click();
	}
	
	/**
	 * This method will logout of Application
	 * @param driver
	 * @throws InterruptedException
	 */
	public void logoutOfApp(WebDriver driver) throws InterruptedException
	{
		mouseOver(driver,userIcon);
		Thread.sleep(2000);
		signOutLink.click();
		
	}
}
