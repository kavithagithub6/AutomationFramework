package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationinfoPage {
	
	//Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement OrgTitle;
	
	//Initialisation
	public OrganizationinfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilisation
	public WebElement getOrgTitle() {
		return OrgTitle;
	}

	
	//Business Library
	/**
	 * This method will capture the header text and return it to caller
	 * @return
	 */
	public String getOrgHeader()
	{
		return OrgTitle.getText();
	}



}
