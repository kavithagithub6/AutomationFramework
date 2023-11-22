package TestNG;

//import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGpractice {
	
	@Test(priority= 0, invocationCount = 3)
	public void create()
	{
		System.out.println("Create");
		Assert.fail();
	}
	
	@Test(priority= -1, dependsOnMethods="create")
	public void modify()
	{
		System.out.println("modify");
	}
	
	@Test(priority= -3, enabled = false)
	public void delete()
	{
		System.out.println("delete");
	}

}
