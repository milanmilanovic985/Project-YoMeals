package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage{

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	public WebElement getSelectLocationElement () {
		return driver.findElement(By.className("location-selector"));
	}
	public WebElement getCloseElement () {
		return driver.findElement(By.xpath("//*[contains (@class, 'close-btn-white')]"));
	}
	public WebElement getKeyword () {
		return driver.findElement(By.id("locality_keyword"));
	}
	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	public WebElement getLocationInput() {
		return driver.findElement(By.id("location_id"));
	}
	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	public void openLocationPopupDialog () {
		getSelectLocationElement().click();
	}
	public void setLocation (String locationName) {
		getKeyword().click();
		String value = getLocationItem(locationName).getAttribute("data-value");
		js.executeAsyncScript("arguments[0].value=arguments[1]", getLocationInput(), value);
		js.executeScript("arguments[0].click()", getSubmit());
	}
	public void closePopupDialog () {
		getCloseElement().click();
	}

}
