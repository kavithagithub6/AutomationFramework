package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractice {
	
	@Test(retryAnalyzer = GenericUtilities.RetryAnalyserImplementation.class)
	public void Sample()
	{
		System.out.println("1");
		Assert.fail();
	}
	
	@Test
	public void Sample1()
	{
		System.out.println("2");
	}
}
