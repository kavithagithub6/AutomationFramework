package GenericUtilities;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of generic methods related to Property file
 * @author kavitha.r
 *
 */
public class PropertyFileUtility {

	/**
	 * This method reads data from property file and returns the value to 
	 * caller
	 * @param key
	 * @return
	 * @throws IOException 
	 * @throws  
	 * @throws  
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}

}
