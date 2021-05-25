package com.qa.FastMediaTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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

public class LoginPageTest extends TestBase {
  
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	@BeforeSuite
	public void setupExtent() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Extent-Reports\\FsatMediaLoginPage.html");
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
	

	public void LoginLaunch() throws InterruptedException {
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@id='loginpopup']")).click();
		Thread.sleep(2000);
		
	}
	
	@Test(priority =1)
	public void validateLoginPageLable() throws InterruptedException {
		test=extent.createTest("TC_01: validate LoginPage Lable");
		LoginLaunch();
		WebElement ele= driver.findElement(By.xpath("//h3[text()='Login to our site']"));
		boolean flag= ele.isDisplayed();
		Assert.assertTrue(flag);
		test.info("The Login Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		
	}
	
	@Test(priority =2)
	public void CheckLoginBothBlank() throws InterruptedException, IOException {
		test=extent.createTest("TC_02: Check Login BothBlank TextField");
		LoginLaunch();
		driver.findElement(By.xpath("//button[@id='button-login-pop']")).click();
		String actulwarning=driver.findElement(By.xpath("//div[@class='alert alert-danger warning-tr']")).getText();
		String expectedwarning="E-Mail address does not appear to be valid!"; 
		Assert.assertEquals(actulwarning, expectedwarning, "Warning Error not matched ");
        Thread.sleep(2000);
		TestBase.getScreenShot("BothEmptyFiled");
		test.info("The Login page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	
	
	@Test(priority = 3)
	public void CheckSignUpPage() throws InterruptedException, IOException {
		test=extent.createTest("TC_03:Check SignUp Page");
		LoginLaunch();
		driver.findElement(By.xpath("//a[normalize-space()='Sign Up']")).click();
		Thread.sleep(2000);
		TestBase.getScreenShot("CheckSignUpPage");
		test.info("The SignUp page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	@Test(priority = 4)
	public void CheckConnectWithFBPage() throws InterruptedException, IOException {
		test=extent.createTest("TC_04:Check Connec tWith FBPage");
		LoginLaunch();
		driver.findElement(By.xpath("//div[@id='ozxmod_login_modal']//a[@class='btn btn-link-2 facebook'][normalize-space()='Facebook']")).click();
		Thread.sleep(2000);
		TestBase.getScreenShot("CheckConnectWithFBPage");
		test.info("The FBPage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	@Test(priority = 5)
	public void CheckConnectWithGooglePage() throws InterruptedException, IOException {
		test=extent.createTest("TC_05: Check Connect With GooglePage");
		LoginLaunch();
		driver.findElement(By.xpath("//div[@id='ozxmod_login_modal']//i[@class='fa fa-google-plus']")).click();
		Thread.sleep(2000);
		TestBase.getScreenShot("CheckConnectWithGooglePage");
		test.info("The GooglePage",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 6)
	public void CheckForgotPage() throws InterruptedException, IOException {
		test=extent.createTest("TC_06: Check Forgot Page");
		LoginLaunch();
		driver.findElement(By.xpath("//a[normalize-space()='Forgot Password']")).click();
		Assert.assertEquals("fastmediashipsfromusa.com", driver.getTitle(), "Title not matched");
		TestBase.getScreenShot("CheckForgotPage");
		test.info("The Forgot page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
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




/*@Test(priority = 5)
public void CheckLoginCloseBtn() throws InterruptedException {
	LoginLaunch();
	driver.findElement(By.xpath("//div[@id='ozxmod_login_modal']//span[@aria-hidden='true'][normalize-space()='×']")).click();
}


@Test(priority = 7)
public void CheckLoginBothInvalid() throws InterruptedException {
	LoginLaunch();
	driver.findElement(By.xpath("//input[@name='ajax_email']")).sendKeys("vasuj661@gmail.com");
	driver.findElement(By.xpath("//input[@name='ajax_password']")).sendKeys("1234");
	driver.findElement(By.xpath("//button[@id='button-login-pop']")).click();
	String actulwarning=driver.findElement(By.xpath("//div[@class='alert alert-danger warning-tr']")).getText();
	String expectedwarning="Warning: No match for E-Mail address and/or Password!"; 
	Assert.assertEquals(actulwarning, expectedwarning, "Warning Error not matched ");
	Thread.sleep(2000);
}
@Test(priority = 8)
public void CheckInvalidMaillPssBlank() throws InterruptedException {
	LoginLaunch();
	String expectedwarning="E-Mail address does not appear to be valid!"; 
	driver.findElement(By.xpath("//input[@name='ajax_email']")).sendKeys("vasujain");
	driver.findElement(By.xpath("//button[@id='button-login-pop']")).click();
	String actulwarning=driver.findElement(By.xpath("//div[@class='alert alert-danger warning-tr']")).getText();
	Assert.assertEquals(actulwarning, expectedwarning, "Warning Error not matched ");		
}*/

