package PracticeBaseClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import GenericUtilities.BaseClass;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
//import objectRepository.LoginPage;


@Listeners(GenericUtilities.ListenersImplementation.class)
public class CreateContactWithBaseClass extends BaseClass {
	
	@Test
	public  void CreateNewContactWithBaseClass() throws IOException, InterruptedException {
		
		//Test Data
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts",1,2);
		
		//Navigate to Contacts link
		HomePage hp = new HomePage(driver);
		hp.clickOnCOntactsLink();
		
		//Click on create contact look up 
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		Assert.fail();
		
		// Create new contact
		CreateNewContactPage cncp= new CreateNewContactPage(driver);
		cncp.createNewContact(LASTNAME);
		
		// Validate
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactheader = cip.getContactHeader();
		
		//Assertions
		Assert.assertTrue(contactheader.contains(LASTNAME));
		System.out.println(contactheader);
		
		
	}

}
