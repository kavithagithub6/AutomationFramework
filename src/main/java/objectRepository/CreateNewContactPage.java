package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {
	
	//Declaration
	@FindBy(name = "lastname")
	private WebElement contactName;
	
	@FindBy(name="account_name")
	private WebElement orgName;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//img[@alt='Select']")
	private WebElement OrgLookUpImg;
	
	@FindBy(name="search_text")
	private WebElement OrgSearchTxt;
	
	@FindBy(name="search")
	private WebElement OrgSearchBtn;
	
	//Initialisation
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilisation
	public WebElement getContactName() {
		return contactName;
	}

	public WebElement getOrgName() {
		return orgName;
	}
	
	public WebElement getsaveBtn() {
		return saveBtn;
	}
	
	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}
	
	public WebElement getOrgSearchTxt() {
		return OrgSearchTxt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}
	
	//Business Library
	/**
	 * This method will create new Contact with mandatory fields
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME)
	{
		contactName.sendKeys(LASTNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will crrate contact choosing organization
	 * @param driver
	 * @param LASTNAME
	 * @param ORGNAME
	 */
	public void createNewContact(WebDriver driver,String LASTNAME,String ORGNAME)
	{
		contactName.sendKeys(LASTNAME);
		OrgLookUpImg.click();
		switchToWindow(driver,"Accounts");
		OrgSearchTxt.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		switchToWindow(driver,"Contacts");
		saveBtn.click();
	}
	
	
	
}
