package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest{
	
	@Test
	public void searchResultsTest() throws InterruptedException, IOException {

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meal Search Results");

		driver.navigate().to(baseUrl + "meals");
		Thread.sleep(500);
		locationPopupPage.setLocation("City Center - Albany");
		
		Thread.sleep(500);

		for (int i = 1; i < 7; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			driver.get(url);

			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			locationPopupPage.openLocationPopupDialog();;
			Thread.sleep(500);
			locationPopupPage.setLocation(location);;
			Thread.sleep(500);

			int numberOfResults = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			Thread.sleep(500);

			Assert.assertEquals(this.searchResultPage.numberOfResults(), numberOfResults, "[ERROR] Unexpected number of results message");

			for (int j = 3; j < numberOfResults + 3; j++) {
				String meal = sheet.getRow(i).getCell(j).getStringCellValue();
				String mealName = this.searchResultPage.getResultsNames().get(j - 3);
				Thread.sleep(500);
				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(mealName.contains(meal), "[ERROR] Results don't match");
				
			}
			softAssert.assertAll();
		}
	}

}
