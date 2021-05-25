package com.qa.FastMediaTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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

public class ProductPageTest extends TestBase {
	
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	@BeforeSuite
	public void setupExtent() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Extent-Reports\\FsatMediaProductPage.html");
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
	
	
	public void ProductPageLaunch() throws InterruptedException {
		WebElement ele=driver.findElement(By.xpath("//img[@title='Funko Yoda Fabrikations Plush']"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		driver.findElement(By.xpath("//img[@title='Funko Yoda Fabrikations Plush']")).click();
	}

	@Test(priority = 1)
	public void CheckProductPageTest() throws InterruptedException {
		test=extent.createTest("TC_01: Check Price Home And Product Page Test");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		test.info("The Check Price Home And Product Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	
	@Test(priority = 2)
	public void CheckBuyItNowBtnTest() throws InterruptedException {
		test=extent.createTest("TC_02: Check BuyItNow Btn Test");
		ProductPageLaunch();
		driver.findElement(By.xpath("//button[normalize-space()='Buy It Now']")).click();
		Thread.sleep(2000);
		test.info("The BuyItNow",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 3)
	public void CheckAddToCartBtnTest() throws InterruptedException {
		test=extent.createTest("TC_03: Check AddToCart Btn Test");
		ProductPageLaunch();
		driver.findElement(By.xpath("//button[normalize-space()='Add to Cart']")).click();
		Thread.sleep(2000);
		test.info("The AddToCart",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 4)
	public void CheckAddToWishListBtnTest() throws InterruptedException {
		test=extent.createTest("TC_04: CheckAddToWishListBtnTest");
		ProductPageLaunch();
		driver.findElement(By.xpath("//button[normalize-space()='Add to Wish List']")).click();
		Thread.sleep(2000);
		test.info("The AddToWishList",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 5)
	public void CheckAddToCampareListBtnTest() throws InterruptedException {
		test=extent.createTest("TC_05: Check AddToCampareList Btn Test");
		ProductPageLaunch();
		driver.findElement(By.xpath("//button[normalize-space()='Add to Compare']")).click();
		Thread.sleep(2000);
		test.info("The AddToCampareList",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 6)
	public void CheckProductDescription() throws InterruptedException, IOException {
		test=extent.createTest("TC_06: Check Product Description");
		ProductPageLaunch();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		Thread.sleep(2000);
		TestBase.getScreenShot("CheckProductDescription");
		test.info("The Product Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 7)
	public void CheckReview() throws InterruptedException, IOException {
		test=extent.createTest("TC_07: Check Review");
		ProductPageLaunch();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		TestBase.getScreenShot("CheckReview");
		driver.findElement(By.xpath("//a[normalize-space()='Reviews (0)']")).click();
		Thread.sleep(2000);
		test.info("The Review Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 8)
	public void CheckFaceComment() throws InterruptedException, IOException {
		test=extent.createTest("TC_08: Check Face Comment");
		ProductPageLaunch();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		TestBase.getScreenShot("CheckFaceComment");
		driver.findElement(By.xpath("//a[normalize-space()='Face comments']")).click();
		Thread.sleep(2000);
		test.info("The Face Comment",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
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
