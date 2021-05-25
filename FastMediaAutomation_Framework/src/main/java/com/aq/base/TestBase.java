package com.aq.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public TestBase() {
		try {
			FileInputStream file= new FileInputStream("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\"
												+"src\\main\\java\\com\\qa\\config\\config.properties");
			prop= new Properties();
			prop.load(file);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void initialization() throws InterruptedException {
		String browsername="chrome";
		
		if(browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Browser-Driver\\chromedriver.exe");
			driver= new ChromeDriver();
		}else {
			System.out.println("Driver not found");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		//driver.navigate().refresh();
		Thread.sleep(2000);
	}
	
	public static void getScreenShot(String testMethodName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\ScreenShot\\"
										+ testMethodName +".png"));
		
		
	}

}
