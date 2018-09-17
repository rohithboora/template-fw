package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import org.apache.log4j.PropertyConfigurator;
//import org.openqa.selenium.Platform;

public class ReadProperty {


	public static String loadPropertyValue(String key) {
		Properties props = new Properties();
		FileInputStream filePath = null;
		String UserDir = System.getProperty("user.dir");

		String filePathString = UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Config.properties";	
    	try{
			filePath = new FileInputStream(filePathString);
			props.load(filePath);
			filePath.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	
	/*public static String loadXOTestDataloProperty(String key) {
		Properties props = new Properties();
		FileInputStream filePath = null;
		Platform current = Platform.getCurrent();
		String UserDir = System.getProperty("user.dir");

		if(current.MAC != null || current.LINUX != null )
		{
	    	String filePathString = UserDir+"/src/main/resources/XODecision.properties";
	    	try{
				filePath = new FileInputStream(filePathString);
				props.load(filePath);
				filePath.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
	}else if(current.WINDOWS != null || current.VISTA != null || current.XP != null)
		{
			String filePathString = UserDir+"\\src\\main\\resources\\XODecision.properties";
			try{
				filePath = new FileInputStream(filePathString);
				props.load(filePath);
				filePath.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
	      }
	else
		{
			System.out.println("Recheck the Current Systme OS");
		}
		return props.getProperty(key);
	
	}

	public static String loadAIPTestData(String key, String file) {
		Properties props = new Properties();
		FileInputStream filePath = null;
		Platform current = Platform.getCurrent();
		String UserDir = System.getProperty("user.dir");

		if(current.MAC != null || current.LINUX != null )
		{
	    	String filePathString = UserDir+"/src/main/resources/TestData/AIP/"+file+".properties";
	    	try{
				filePath = new FileInputStream(filePathString);
				props.load(filePath);
				filePath.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
	}else if(current.WINDOWS != null || current.VISTA != null || current.XP != null)
		{
			String filePathString = UserDir+"\\src\\main\\resources\\TestData\\AIP\\"+file+".properties";
			try{
				filePath = new FileInputStream(filePathString);
				props.load(filePath);
				filePath.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
	      }
	else
		{
			System.out.println("Recheck the Current Systme OS");
		}

		return props.getProperty(key, file);
	
	}


public static String loadFadsAIPTestData(String key, String file) {
    Properties props = new Properties();
    FileInputStream filePath = null;
    Platform current = Platform.getCurrent();
    String UserDir = System.getProperty("user.dir");

    if(current.MAC != null || current.LINUX != null )
    {
        String filePathString = UserDir+"/src/main/resources/TestData/FADS-AIP/"+file+".properties";
        try{
            filePath = new FileInputStream(filePathString);
            props.load(filePath);
            filePath.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
}else if(current.WINDOWS != null || current.VISTA != null || current.XP != null)
    {
        String filePathString = UserDir+"\\src\\main\\resources\\TestData\\FADS-AIP\\"+file+".properties";
        try{
            filePath = new FileInputStream(filePathString);
            props.load(filePath);
            filePath.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
      }
else
    {
        System.out.println("Recheck the Current Systme OS");
    }

    return props.getProperty(key, file);

}

public static String loadB2BTestData(String key, String file) {
	Properties props = new Properties();
	FileInputStream filePath = null;
	Platform current = Platform.getCurrent();
	String UserDir = System.getProperty("user.dir");

	if(current.MAC != null || current.LINUX != null )
	{
    	String filePathString = UserDir+"/src/main/resources/TestData/B2B/"+file+".properties";
    	try{
			filePath = new FileInputStream(filePathString);
			props.load(filePath);
			filePath.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
}else if(current.WINDOWS != null || current.VISTA != null || current.XP != null)
	{
		String filePathString = UserDir+"\\src\\main\\resources\\TestData\\B2B\\"+file+".properties";
		try{
			filePath = new FileInputStream(filePathString);
			props.load(filePath);
			filePath.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
      }
else
	{
		System.out.println("Recheck the Current Systme OS");
	}

	return props.getProperty(key, file);

}*/
}

