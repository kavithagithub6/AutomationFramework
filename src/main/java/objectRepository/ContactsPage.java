package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//Declaration
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContact;
	
	//Initialisation
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilisation
	public WebElement getCreateContact() {
		return createContact;
	}
	
	//Business Library
	/**
	 * This method will click on Create New contact look up image
	 */
	public void clickOnCreateContactLookUpImg()
	{
		createContact.click();
	}
}
