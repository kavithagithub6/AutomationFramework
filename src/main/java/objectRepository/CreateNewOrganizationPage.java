package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility{
	
	//Declaration
	@FindBy(name = "accountname")
	private WebElement OrgName;
	
	@FindBy(name ="industry")
	private WebElement industryDropDown;
	
	@FindBy(name ="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialisation
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}


	//Utilisation

	public WebElement getOrgName() {
		return OrgName;
	}
	
	public WebElement getindustryDropDown() {
		return industryDropDown;
	}
	
	public WebElement gettypeDropDown() {
		return typeDropDown;
	}
	public WebElement getsaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method will create new Organization with mandatory fields
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME)
	{
		OrgName.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method will create Organization with industry dropdown
	 * @param ORGNAME
	 * @param INDUSTRYNAME
	 */
	public void createNewOrganization(String ORGNAME,String INDUSTRYNAME)
	{
		OrgName.sendKeys(ORGNAME);
		handleDropDown(industryDropDown,INDUSTRYNAME);
		saveBtn.click();
	}
	/**
	 * This method will create Organization with industry and Type dropdowns
	 * @param ORGNAME
	 * @param INDUSTRYNAME
	 * @param TYPE
	 */
	public void createNewOrganization(String ORGNAME,String INDUSTRYNAME, String TYPE)
	{
		OrgName.sendKeys(ORGNAME);
		handleDropDown(industryDropDown,INDUSTRYNAME);
		handleDropDown(typeDropDown,TYPE);
		saveBtn.click();
	}
}
