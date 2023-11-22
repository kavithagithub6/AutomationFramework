package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	
	//Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactTitle;
	
	//Initialisation
	public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Utilisation
	public WebElement getContactTitle() {
		return contactTitle;
	}
	
	//Business Library
	/**
	 * This method will capture the header text and return it to caller
	 * @return
	 */
	public String getContactHeader()
	{
		return contactTitle.getText();
	}
	
}
