package com.qa.FastMediaTest;
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

public class RegistrationPageTest extends TestBase {

	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	
	public RegistrationPageTest() {
		super();
	}
	
	@BeforeSuite
	public void setupExtent() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Extent-Reports\\FsatMediaRegistrationPage.html");
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

	public void RegistrationPageLunch() throws InterruptedException {
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void ValidateRegisterPageLable() throws InterruptedException {
		test=extent.createTest("TC_01: Validate Register Page Lable");
		RegistrationPageLunch();
		WebElement actualLable= driver.findElement(By.xpath("//h3[text()='Sign up now']"));
		boolean flag=actualLable.isDisplayed();
		Assert.assertTrue(flag);
		test.info("The Register Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 2) //Without any Data
	public void ClickEmptySubmitBtn() throws InterruptedException {
		test=extent.createTest("TC_02: Click Empty Submit Btn");
		RegistrationPageLunch();
		driver.findElement(By.xpath("//button[normalize-space()='SIGN ME UP']")).click();
		test.info("The Empty Submit Btn Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 3)
	public void CheckRegisterWithGoogle() throws InterruptedException {
		test=extent.createTest("TC_03: Check Register With Google");
		RegistrationPageLunch();
		driver.findElement(By.xpath("//div[@id='ozxmod_signup_modal']//a[@class='btn btn-link-2 facebook'][normalize-space()='Facebook']")).click();
		test.info("The Register With Google",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 4)
	public void CheckRegisterWithFacebook() throws InterruptedException {
		test=extent.createTest("TC_04:Check Register With Facebook");
		RegistrationPageLunch();
		driver.findElement(By.xpath("//div[@id='ozxmod_signup_modal']//a[@class='btn btn-link-2 gplus'][normalize-space()='Google']")).click();
		test.info("The Register With Facebook",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 5)
	public void CheckRegisterLoginLink() throws InterruptedException {
		test=extent.createTest("TC_05: Check Register Login Link");
		RegistrationPageLunch();
		driver.findElement(By.xpath("//a[normalize-space()='Login Here']")).click();
		test.info("The Register Login Link",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 6)
	public void TryToRegisterWithValid() throws InterruptedException {
		test=extent.createTest("TC_06: Try To Register With Valid");
		RegistrationPageLunch();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(prop.getProperty("name"));
		driver.findElement(By.xpath("//input[@name='ajax_register_email']")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.xpath("//input[@placeholder='Telephone']")).sendKeys(prop.getProperty("telephone"));
		driver.findElement(By.xpath("//input[@name='ajax_register_password']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@placeholder='Re-Enter Password']")).sendKeys(prop.getProperty("repassword"));
		driver.findElement(By.xpath("//button[normalize-space()='SIGN ME UP']")).click();
		Thread.sleep(2000);
		test.info("The Register With Valid",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
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
public void CheckRegisterCloseBtn() throws InterruptedException {
	RegistrationPageLunch();
	driver.findElement(By.xpath("//div[@id='ozxmod_signup_modal']//span[@aria-hidden='true'][normalize-space()='×']")).click();
}
@Test(priority = 6)
public void CheckAllBlankNotPass() throws InterruptedException {
	RegistrationPageLunch();
	driver.findElement(By.xpath("//input[@name='ajax_register_password']")).sendKeys(prop.getProperty("password"));
}
@Test(priority = 7)
public void CheckAllBlankNotConfirmPass() throws InterruptedException {
	RegistrationPageLunch();
	driver.findElement(By.xpath("//input[@placeholder='Re-Enter Password']")).sendKeys(prop.getProperty("repassword"));
}
@Test(priority = 8)
public void CheckPassAndConfirmPass() throws InterruptedException {
	RegistrationPageLunch();
	driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Vashu");
	driver.findElement(By.xpath("//input[@name='ajax_register_email']")).sendKeys("Vashu.jain@testunity.com");
	driver.findElement(By.xpath("//input[@placeholder='Telephone']")).sendKeys("8962635374");
	driver.findElement(By.xpath("//input[@name='ajax_register_password']")).sendKeys("8962635374");
	driver.findElement(By.xpath("//input[@placeholder='Re-Enter Password']")).sendKeys("896263537");
	driver.findElement(By.xpath("//button[normalize-space()='SIGN ME UP']")).click();
}

@Test(priority = 9)
public void TryToRegisterWithValid() throws InterruptedException {
	RegistrationPageLunch();
	driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Vashu");
	driver.findElement(By.xpath("//input[@name='ajax_register_email']")).sendKeys("Vashu.jain@testunity.com");
	driver.findElement(By.xpath("//input[@placeholder='Telephone']")).sendKeys("8962635374");
	driver.findElement(By.xpath("//input[@name='ajax_register_password']")).sendKeys("8962635374");
	driver.findElement(By.xpath("//input[@placeholder='Re-Enter Password']")).sendKeys("8962635374");
	driver.findElement(By.xpath("//button[normalize-space()='SIGN ME UP']")).click();
	Thread.sleep(2000);
}
 */
