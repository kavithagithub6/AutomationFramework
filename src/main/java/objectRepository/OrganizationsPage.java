package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrg;
	
	//Initialisation
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilisation
	public WebElement getCreateOrg() {
		return createOrg;
	}
	
	//Business library
	/**
	 * This method will click on Create New Organization look up image
	 */
	public void clickOnCreateOrgLookUpImg()
	{
		createOrg.click();
	}
}
