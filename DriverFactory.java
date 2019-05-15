package ProvaBase2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	
private static WebDriver driver;
	
	private DriverFactory() {};
		
	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Properties.browser) {
				case CHROME:
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\DIshi\\Documents\\chromedriver.exe");
					driver = new ChromeDriver();
					break;
				case FIREFOX:
					System.setProperty("webdriver.gecko.driver", "C:\\Users\\DIshi\\Documents\\geckodriver.exe");
					driver = new FirefoxDriver();
					break;
			}
		}
		
			return driver;
	}
	
	public static void killDriver() {
		if(driver != null ) {
			driver.quit();
			driver = null;
		}
	}
}
