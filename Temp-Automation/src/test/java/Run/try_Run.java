package Run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class try_Run {

	public static void main(String[] args) {
		
	String UserDir = System.getProperty("user.dir");
	WebDriver driver;
	System.setProperty("webdriver.gecko.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"geckodriver.exe");
		/*System.out.println(UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"geckodriver.exe");
		
		
		
		//System.setProperty("webdriver.chrome.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"chromedriver.exe");
		System.setProperty("webdriver.ie.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"IEDriverServer.exe");

		*/
       /* DesiredCapabilities IEcapabilities = DesiredCapabilities.internetExplorer();
        IEcapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        IEcapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        //IEcapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        IEcapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
        IEcapabilities.setCapability("ignoreProtectedModeSettings", true);
        //IEcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //IEcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
        IEcapabilities.setJavascriptEnabled(true);
        IEcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        IEcapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        IEcapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        IEcapabilities.setCapability("nativeEvents", false);    
        //IEcapabilities.setCapability("unexpectedAlertBehaviour", "accept");
        IEcapabilities.setCapability("ignoreProtectedModeSettings", true);
        IEcapabilities.setCapability("disable-popup-blocking", true);
        IEcapabilities.setCapability("enablePersistentHover", true);
        //IEcapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);
        IEcapabilities.setJavascriptEnabled(true);
        //IEcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //IEcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		driver = new FirefoxDriver();
		driver.get("https://www.google.co.uk/");
		//driver.close();
		driver.quit();*/
		
		/*BrowserMobProxy server = new BrowserMobProxyServer();
		server.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		server.start();
		int port = server.getPort();
		server.addRequestFilter(new RequestFilter() {
			public HttpResponse filterRequest(HttpRequest request, HttpMessageContents content, HttpMessageInfo info) {
			    String q;
				try {
					q = URLDecoder.decode(info.getOriginalUrl(), "UTF-8");
					 System.out.println("Request: "+ q);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			    return null;
			}
		});

		server.addResponseFilter(new ResponseFilter() {
			public void filterResponse(HttpResponse response, HttpMessageContents content, HttpMessageInfo info) {
			    String type = response.headers().get("Content-Type");
			    System.out.println("Response: "+info.getOriginalRequest());
			    System.out.println(type);
			}
		});

		Proxy proxy = ClientUtil.createSeleniumProxy(server);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		capabilities.setCapability("marionette", true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		FirefoxProfile fp = new FirefoxProfile();
		//capabilities.setCapability(FirefoxDriver.PROFILE, fp);
		fp.setPreference("network.proxy.http", "localhost");
		fp.setPreference("network.proxy.http_port", server.getPort());
		fp.setPreference("network.proxy.ssl", "localhost");
		fp.setPreference("network.proxy.ssl_port", server.getPort());
		fp.setPreference("network.proxy.type", 1);
		fp.setPreference("network.proxy.no_proxies_on", "");
		String gecko = UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", gecko);
		driver = new FirefoxDriver(capabilities);                    
		driver.get("https://www.google.co.uk/");
		driver.quit();*/
	
	/*BrowserMobProxy server = new BrowserMobProxyServer();
	ProxyServer server = new ProxyServer(8107);
    server.start();
    server.setCaptureHeaders(true);
    server.setCaptureContent(true);
    server.newHar("gooogle home page");
    DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(CapabilityType.PROXY, proxy);
WebDriver driver = new FirefoxDriver(capabilities);
    driver.get("http://www.google.com");
    driver.findElement(By.id("lst-ib")).sendKeys("hello");
    driver.findElement(By.name("btnG")).click();
    Har har1 = server.getHar();
    System.out.println("HAR content is:   "+ har1);
    
    har1.writeTo(new File("har.json"));
    server.stop();
    driver.quit();*/
	
	/*---------------------------------------*/
	/*BrowserMobProxy proxy = new BrowserMobProxyServer();
    proxy.start(0);
    int port = proxy.getPort();
    System.out.println(port);
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
    FirefoxProfile fp = new FirefoxProfile();
    fp.setPreference("network.proxy.http", "localhost");
    fp.setPreference("network.proxy.http_port", port);
    
    
    driver = new FirefoxDriver(capabilities);*/
    /*---------------------------------------*/
	/*Proxy proxy = new Proxy();
    // The URL here is the URL that the browsermob proxy is using
    proxy.setHttpProxy("localhost:9100");

    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability(CapabilityType.PROXY, proxy);

	System.setProperty("webdriver.chrome.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"chromedriver.exe");

    driver = new ChromeDriver(capabilities);
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    proxy.newHar("yahoo.com");*/
    
    
	BrowserMobProxy proxy = new BrowserMobProxyServer();
    proxy.start(0);
    int port = proxy.getPort();
    System.out.println(port);
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
	System.setProperty("webdriver.chrome.driver", UserDir+ File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator +"Setup files"+ File.separator +"chromedriver.exe");
    driver = new ChromeDriver(capabilities);
	
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    proxy.newHar("xyxxyxyxyxyxy");
 
    driver.get("https://www.bbc.co.uk/");
    Har har = proxy.getHar();
    //System.out.println("HAR content is:   "+ har);
    
    String JsonfilepathtoHAR = UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator +"har1.json";
    String JsonfilepathtoString = UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator +"har2.json";

    File f = new File(JsonfilepathtoHAR);

   try {
		//har.writeTo(new File(UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator +"har.json"));
		//File f = new File(UserDir+ File.separator + "Test Evidence"+ File.separator +"Failed Scenairos"+ File.separator +"FailedPages"+ File.separator +"har.json");
	   har.writeTo(f);

        if (f.exists()){
            InputStream is = new FileInputStream(JsonfilepathtoHAR);
            String jsonTxt = IOUtils.toString(is);
            System.out.println(jsonTxt);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		JsonParser jp = new JsonParser();
    		JsonElement je = jp.parse(jsonTxt);
    		String prettyJson = gson.toJson(je);
            
    		FileWriter file = new FileWriter(JsonfilepathtoHAR);
    		try{
    			file.write(prettyJson);
    			System.out.println("Successfully copied JSON Object to file");
    			System.out.println("\nJSON Object:" + prettyJson);
    		} finally {
    			file.flush();
    			file.close();
    		}   
        }
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    
    proxy.stop();
    driver.quit();
	}
	
}
