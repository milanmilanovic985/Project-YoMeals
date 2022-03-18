package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest{
	
	@Test (priority = 1)
	public void addMealToCart () throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopupDialog();
		mealPage.addMealToCart(3);
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains
		("The Following Errors Occurred:"), "[ERROR] Message didn't appear.");
		notificationSystemPage.waitUntilNotificationDisappear();
		locationPopupPage.setLocation("City Center - Albany");
		mealPage.addMealToCart(5);
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Meal Added To Cart"), "[ERROR] Message didn't appear.");
		
	}
	@Test (priority = 2)
	public void addMealToFavorite () throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopupDialog();
		mealPage.addToFavourite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Please login first!"), "[ERROR] Message didn't appear.");
		driver.get(baseUrl + "guest-user/login-form");
		loginPage.login(email, password);
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavourite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Product has been added to your favorites."), "[ERROR] Message didn't appear.");
		
	}
	@Test (priority = 3)
	public void clearCart () throws IOException, InterruptedException {
		driver.get(baseUrl + "meals");
		locationPopupPage.setLocation("City Center - Albany");
		File file = new File("data/Data.xlsx");	
		FileInputStream fis = new FileInputStream(file);	
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			
			driver.get(mealUrl);
			mealPage.addMealToCart(quantity);
			Thread.sleep(500);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(notificationSystemPage.notificationMessage().contains("Meal Added To Cart"), "[ERROR] Message didn't appear.");
		}
		cartSummaryPage.clearAll();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("All meals removed from Cart successfully"), "[ERROR] Message didn't appear.");
		
	}

}
