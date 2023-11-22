package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//Declaration
	@FindBy(name = "user_name")
	private WebElement userName;
	
	@FindBy(name = "user_password")
	private WebElement userPassword;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	//Initialisation
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilisation
	public WebElement getUserName() {
		return userName;
	}

	public WebElement getUserPassword() {
		return userPassword;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Business libraries -> Generic method using the web elements in current Page
	/**
	 * This method will login to Application
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME, String PASSWORD)
	{
		userName.sendKeys(USERNAME);
		userPassword.sendKeys(PASSWORD);
		loginBtn.click();
	}
	
	
	
	
}
