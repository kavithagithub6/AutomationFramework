package PracticeBaseClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationinfoPage;
import objectRepository.OrganizationsPage;

public class CreateOrganizationWithBaseClass extends BaseClass{
	
	@Test
	public void CreateNewOrganizationWithBaseClass() throws IOException, InterruptedException{
	
		//Test Data
		String ORGANISATIONNAME =eUtil.readDataFromExcelFile("Organizations",1,2)+jUtil.getRandomNumber();
		
		//Step 4 :Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgLink();
		
		//Step 5 : Click on create Organization look up 
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImg();
		
		//Step 6 : Create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGANISATIONNAME);
		
		//Step 7 : Validate
		OrganizationinfoPage oip = new OrganizationinfoPage(driver);
		String orgheader = oip.getOrgHeader();
		
		//Assertions
		Assert.assertTrue(orgheader.contains(ORGANISATIONNAME));
		System.out.println(orgheader);
				
		
				
	}
}
