package practice;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
	
	@Test
	public void read()
	{
		String un = System.getProperty("username");
		String pwd = System.getProperty("password");
		
		System.out.println(un);
		System.out.println(pwd);
	}
}
