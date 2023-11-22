package TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataProviderpractice {

	@Test(dataProvider ="getData")
	public void readData(String Name, String model, int qty, int price)
	{
		System.out.println(Name+"--"+model+"--"+qty+"--"+price);
	}

@DataProvider
public Object[][] getData()
{
	Object[][] data = new Object[3][4];
	
	data[0][0] = "SAmsung";
	data[0][1] = "A80";
	data[0][2] = 12;
	data[0][3] = 12000;
	
	data[1][0] = "iPhone";
	data[1][1] = "S14";
	data[1][2] = "16";
	data[1][3] = 15000;
	
	data[2][0] = "Vivo";
	data[2][1] = "V21";
	data[2][2] = 15;
	data[2][3] = 10000;
	
	return data;
}

}