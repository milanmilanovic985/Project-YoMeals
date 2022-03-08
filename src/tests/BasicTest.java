package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait wait;
	protected SoftAssert softAssert;

	protected String baseUrl;
	protected String email;
	protected String password;
	protected String locationName;

	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;

	@BeforeMethod
	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, 20);
		js = (JavascriptExecutor) driver;

		baseUrl = "http://demo.yo-meals.com/";
		email = "customer@dummyid.com";
		password = "12345678a";

		locationPopupPage = new LocationPopupPage(driver, wait, js);
		loginPage = new LoginPage(driver, wait, js);
		profilePage = new ProfilePage(driver, wait, js);
		notificationSystemPage = new NotificationSystemPage(driver, wait, js);
		authPage = new AuthPage(driver, wait, js);
		mealPage = new MealPage(driver, wait, js);
		cartSummaryPage = new CartSummaryPage(driver, wait, js);
		searchResultPage = new SearchResultPage(driver, wait, js);

	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) throws IOException, InterruptedException {
		
		if (ITestResult.FAILURE == result.getStatus()) {

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			String screenshotDate = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm-ss'.png'").format(new Date());
			FileHandler.copy(source, new File("./screenshots/" + result.getName() + " - " + screenshotDate + ".png"));
		}
		Thread.sleep(3000);
		driver.quit();
		
	}
	

}
