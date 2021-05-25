package com.qa.FastMediaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aq.base.TestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ContectUsPageTest extends TestBase {

	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	@BeforeSuite
	public void setupExtent() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Extent-Reports\\FsatMediaContectUsPage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("FastMedia LoginPage Automation");
		spark.config().setReportName("Vashu Jain");
		extent.attachReporter(spark);
		
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("System", "Windows10");
		extent.setSystemInfo("Tool", "Eclipse");
		extent.setSystemInfo("Software", "Selenium with Java");
		
	}
	
	@AfterSuite
	public void tearExtent() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
	}
	
	
	public void LunchContectUs() {
		driver.findElement(By.xpath("//span[normalize-space()='CONTACT US']")).click();
		
	}
	
	@Test(priority=1)
	public void OurLocationSectionVisibilityTest() {
		test=extent.createTest("TC_01: OurLocation Section Visibility Test");
		LunchContectUs();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-sm-3']")).isDisplayed());
		test.info("The GooglePage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority=2)
	public void ClickSubmitBtnWithoutValues() throws InterruptedException {
		test=extent.createTest("TC_01: OurLocation Section Visibility Test");
		LunchContectUs();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		test.info("The GooglePage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority=3)
	public void ClickSubmitBtnWithValidValues() throws InterruptedException {
		test=extent.createTest("TC_01: OurLocation Section Visibility Test");
		LunchContectUs();
		driver.findElement(By.xpath("//input[@id='input-name']")).sendKeys(prop.getProperty("name"));
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.xpath("//textarea[@id='input-enquiry']")).sendKeys(prop.getProperty("message"));
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		test.info("The GooglePage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority=4)
	public void ClickSubmitBtnWithInValidValues() throws InterruptedException {
		test=extent.createTest("TC_01: OurLocation Section Visibility Test");
		LunchContectUs();
		driver.findElement(By.xpath("//input[@id='input-name']")).sendKeys("2");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("wrong");
		driver.findElement(By.xpath("//textarea[@id='input-enquiry']")).sendKeys("message");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		test.info("The GooglePage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL,"The test casee failed due to to==>"+result.getName());
			test.log(Status.FAIL,"The test casee failed Error is ==>"+result.getThrowable());
		}
		driver.close();
	}
	
	public String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	
}
