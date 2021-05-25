package com.qa.FastMediaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
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

public class HomePageTest extends TestBase {
	
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	@BeforeSuite
	public void setupExtent() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\Vasu\\eclipse-workspace\\FastMediaAutomation_Framework\\Extent-Reports\\FsatMediaHomePage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("FastMedia HomePage Automation");
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
	
	
	 @Test(priority = 1)
	public void FastMediaHomePageTitleTest() {
		test=extent.createTest("TC_01: FastMedia HomePage Title Test");
		System.out.println("1st Test case is running");
		String ExceptedTitle="fastmediashipsfromusa.com | Easy buy from usa to india";
		String ActualTitle=driver.getTitle();
		System.out.println("The currt pagae tile is==>" + driver.getTitle());
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Tile not matched");
		System.out.println("FastMedia Home Page Title Test pass");
		test.info("The acutal Title is==>" + driver.getTitle());
		
	}
	
	@Test(priority = 2)
	public void HomePageImgVlaidateTest() {//1
		System.out.println("2nd Test case is running");
		test=extent.createTest("TC_02: HomePage Image Vlaidate Test");
		boolean flag=driver.findElement(By.xpath("//img[@title='fastmediashipsfromusa.com']")).isDisplayed();
		Assert.assertTrue(flag);
		test.info("The Image is displayed",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}  
	
	@Test(priority = 3)
	public void ContectusOpenAndTitleTest() throws InterruptedException {//2
		test=extent.createTest("TC_03: Contectus Validation Test");
		System.out.println("3rd Test case is running");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='CONTACT US']")).click();
		String ActualTitle=driver.getTitle();
		System.out.println("ContactusPage title is ===>"+ driver.getTitle());
		
		String ExceptedTitle="Contact Us";
		Assert.assertEquals(ActualTitle, ExceptedTitle, "Title not matched");
		test.info("The Contact Us page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}   
	
	
	@Test(priority = 4)
	public void LoginPageOpenTest() throws InterruptedException {
		test=extent.createTest("TC_04:Login Page Open Test");
		System.out.println("4th Test case is running");
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='loginpopup']")).click();
		Thread.sleep(2000);
		test.info("The Login Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 5)
	public void RegistrationPageOpenTest() throws InterruptedException 
	{
		test=extent.createTest("TC_05:Registration Page Open Test");
		System.out.println("5th Test case is running");
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		Thread.sleep(2000);
		test.info("The Registration Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	 
	@Test(priority = 6)
	public void HeaderValidateTest() {
		test=extent.createTest("TC_06:Header Validate Test");
		System.out.println("6th Test case is running");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='header_strip']")).isDisplayed());
		test.info("The Home Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 7)
	public void WishlistTitleCheckTest() {
		test=extent.createTest("TC_07:Wish list Title Check Test");
		System.out.println("7th Test case is running");
		driver.findElement(By.xpath("//span[normalize-space()='Wish List (0)']")).click();
		String ExceptedTitle="fastmediashipsfromusa.com";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The Wishlist Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 8)
	public void BlogTitleCheckTest() {
		test=extent.createTest("TC_08:Blog Title Check Test");
		System.out.println("8th Test case is running");
		driver.findElement(By.xpath("//*[@id=\"bt_header\"]/div[4]/div[2]/div/div[1]/div/div/div/div/nav/ul/li[3]/a/span")).click();
		// //*[@id="bt_header"]/div[4]/div[2]/div/div[1]/div/div/div/div/nav/ul/li[3]/a/span
		String ExceptedTitle="Blog";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The Blog Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	@Test(priority = 9)
	public void GiftTitleCheckTest() throws InterruptedException {
		test=extent.createTest("TC_09:Gift Title Check Test");
		System.out.println("9th Test case is running");
		driver.findElement(By.xpath("//div[@class='menu']//span[@class='menu-title'][normalize-space()='Gift']")).click();
		Thread.sleep(1000);
		String ExceptedTitle="Gift Online Store : Buy Gift products in India at Best Prices - fastmediashipsfromusa.com";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The Gift Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
		@Test(priority = 10)
	public void ShopByBrandTitleCheck() throws InterruptedException {
		test=extent.createTest("TC_10:Gift Title Check Test");
		System.out.println("10th Test case is running");
		driver.findElement(By.xpath("//div[@class='menu']//div[@class='menubar']//div[@class='container']//div[@class='bt_mainmenu']//div[@class='row']//nav[@class='mega-menu']//ul[@class='nav nav-pills']//li//a[@href='brand']")).click();
		Thread.sleep(3000);
		String ExceptedTitle="Find Your Favorite Brand";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The ShopByBrand Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	 
	@Test(priority = 11)
	public void CheckSearchboxTest() throws InterruptedException {
		test=extent.createTest("TC_11:Check Search box Test");
		System.out.println("11th Test case is running");
		driver.findElement(By.xpath("//input[@id='txt_search']")).sendKeys("Oil");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		test.info("The Searchbox Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}

	@Test(priority = 12)
	public void BagsAndCasesImageTest(){//2----exclude
		test=extent.createTest("TC_12:BagsAndCases Image Test");
		System.out.println("12th Test case is running");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='bags & casest']")).isDisplayed());
		test.info("The BagsAndCases Image",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 13)
	public void BagsAndCasesImageOpenTest() {
		test=extent.createTest("TC_13:BagsAndCases Image Open Test");
		System.out.println("13th Test case is running");
		driver.findElement(By.xpath("//img[@alt='bags & casest']")).click();
		String ExceptedTitle="Bags & Cases:Buy Bags & Cases online at best prices in India @ fastmediashipsfromusa.com<br>";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The BagsAndCases Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 14)
	public void HomeAndKitchenOpenTest() {
		test=extent.createTest("TC_14:Home And Kitchen Open Test");
		System.out.println("14th Test case is running");
		driver.findElement(By.xpath("//img[@alt='fastmediashipsfromusa_home_kitchen.jpg']")).click();
		String ExceptedTitle="Home & Kitchen Online Store : Buy Home & Kitchen products in India at Best Prices - fastmediashipsfromusa.com";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The Home And Kitchen Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 15)
	public void BathToysOpenTest() {
		test=extent.createTest("TC_15:BathToys Open Test");
		System.out.println("15th Test case is running");
		driver.findElement(By.xpath("//img[@alt='fastmediashipsfromusa_bath-toys.jpg']")).click();
		String ExceptedTitle="Baby Products Online Store : Buy Baby Products products in India at Best Prices - fastmediashipsfromusa.com";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The BathToys Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 16)
	public void CheckProductTest() throws InterruptedException {
		test=extent.createTest("TC_16:Product Page Test");
		System.out.println("16th Test case is running");
		Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,850)");
		
		driver.findElement(By.xpath("//img[@title='PLAYMOBIL® 9401 Equestrian shop']")).click();
		
		String ExceptedTitle="Buy PLAYMOBIL® 9401 Equestrian shop Online at Low Prices in India - fastmediashipsfromusa.com";
		String ActualTitle=driver.getTitle();
		Assert.assertEquals(ExceptedTitle, ActualTitle, "Title not matched");
		test.info("The acutal Title is==>" + driver.getTitle());
		test.info("The Product Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	
	@Test(priority = 17)
	public void CheckSearchBoxCatagoryTest() throws InterruptedException {
		test=extent.createTest("TC_17:Check SearchBox Catagory Test");
		System.out.println("17th Test case is running");
		driver.findElement(By.xpath("//a[@class='sbSelector']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@rel='5424']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		test.info("The SearchBox Test",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		
	}
	
	@Test(priority = 18)
	public void ClearanceSaleOpenCheckTest() throws InterruptedException {
		test=extent.createTest("TC_18:Clearance Sale Title Check Test");
		System.out.println("18th Test case is running");
		driver.findElement(By.xpath("//div[@class='menu']//span[@class='menu-title'][normalize-space()='Clearance sale']")).click();
	    Thread.sleep(2000);
		test.info("The Clearance Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 19)
	public void CheckAddtoCartTest() throws InterruptedException {
		test=extent.createTest("TC_19:Check AddtoCart Test");
		System.out.println("19th Test case is running");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,850)");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@title='PLAYMOBIL® 9401 Equestrian shop']"))).build().perform();
		driver.findElement(By.xpath("//div[@id='bt_container']//div[@id='content_top']//div[3]//div[1]//div[1]//button[1][1]//i[1]")).click();
		test.info("The Addtocart Test",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority = 20)
	public void CheckBannerOpenTest() throws InterruptedException {
		test=extent.createTest("TC_20:Check Banner Test");//3
		System.out.println("20th Test case is running");
		driver.findElement(By.xpath("//div[@class='tp-caption sft slidelink hasclicklistener']//span")).click();
		Thread.sleep(1000);
		test.info("The Banner Page",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	
		@Test(priority = 21)
	public void CheckAddtoWishListTest() throws InterruptedException {
		test=extent.createTest("TC_21:Check AddtoWishList Test");
		System.out.println("21st Test case is running");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,850)");
		Thread.sleep(1000);
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@title='PLAYMOBIL® 9401 Equestrian shop']"))).build().perform();
		driver.findElement(By.xpath("//div[@id='content_top']//div[3]//div[1]//div[1]//button[1]//i[1]")).click();
		test.info("The AddtoWishList Test",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
	}
	
	@Test(priority =22)
	public void CheckAddtoCompareTest() throws InterruptedException {
		test=extent.createTest("TC_22:Check AddtoCompare Test");
		System.out.println("22nd Test case is running");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		Thread.sleep(1000);
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@title='PLAYMOBIL® 9401 Equestrian shop']"))).build().perform();
		driver.findElement(By.xpath("//div[@id='content_top']//div[3]//div[1]//div[1]//button[1]//i[1]")).click();
		test.info("The AddtoCompare Test",MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
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
