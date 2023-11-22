package practiceGU;

import java.io.IOException;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;

public class GenericUtilitiesPractice {
	public static void main(String[] args) throws IOException {
		int sum = add(221,23);
		System.out.println(sum);
		
		/* Using PropertyFileUtility Generic Utilities class */
		PropertyFileUtility putil = new PropertyFileUtility();
		String url = putil.readDataFromPropertyFile("url");
		System.out.println(url);
		
		String user = putil.readDataFromPropertyFile("username");
		System.out.println(user);
		
		/* Using ExcelFileUtility Generic Utilities class */
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.readDataFromExcelFile("Contacts", 1, 2);
		System.out.println(LASTNAME);
		
		/* Using Java Utility Generic Utilities class */
		JavaUtility jutil = new JavaUtility();
		int ran = jutil.getRandomNumber();
		System.out.println(ran);
		
		/*Java utility - current system date */
		String date = jutil.getSystemDate();
		System.out.println(date);
	}
	
	public static int  add(int a , int b)
	{
		int c = a + b;
		return c;
	}
}
