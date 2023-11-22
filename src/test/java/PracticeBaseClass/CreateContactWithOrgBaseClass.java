package PracticeBaseClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationinfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrgBaseClass extends BaseClass {
	
	@Test
	public void CreateNewContactWithOrgTest() throws IOException, InterruptedException {
		//Test Data
		String LASTNAME = eUtil.readDataFromExcelFile("Contacts",7,2);
		String ORGNAME = eUtil.readDataFromExcelFile("Contacts",7,3)+jUtil.getRandomNumber();
		
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
				
				//Assertions
				Assert.assertTrue(orgheader.contains(ORGNAME));
				System.out.println(orgheader);
				
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
				
				//Assertions
				Assert.assertTrue(contactheader.contains(LASTNAME));
				System.out.println(contactheader);
				

				
	}
}
