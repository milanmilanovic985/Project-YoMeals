package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage{

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	public WebElement getFirstNameInput () {
		return driver.findElement(By.name("user_first_name"));
	}
	public WebElement getLastNemeInput () {
		return driver.findElement(By.name("user_last_name"));
	}
	public WebElement getAddressInput () {
		return driver.findElement(By.name("user_address"));
	}
	public WebElement getPhoneInput () {
		return driver.findElement(By.name("user_phone"));
	}
	public WebElement getZipCodeInput () {
		return driver.findElement(By.name("user_zip"));
	}
	public void countrySelect (String country) {
		Select drpCountry = new Select (driver.findElement(By.id("user_country_id")));
		drpCountry.selectByVisibleText(country);
	}
	public void stateSelect(String state) {
		Select drpState = new Select(driver.findElement(By.id("user_state_id")));
		drpState.selectByVisibleText(state);
	}

	public void citySelect(String city) {
		Select drpCity = new Select(driver.findElement(By.id("user_city")));
		drpCity.selectByVisibleText(city);
	}
	public WebElement getSaveButton () {
		return driver.findElement(By.xpath("//*[contains (@class, 'col-lg-12 col-md-12 col-sm-12 col-lg-12 align--right')]//input"));
	}
	public WebElement getUploadImageButton () {
		return driver.findElement(By.xpath("//*[contains (@class, 'upload uploadFile-Js')]"));
	}
	public WebElement getRemoveImageButton () {
		return driver.findElement(By.className("remove"));
	}
	public void uploadProfileImage (String pathToTheImage) {
		js.executeScript("arguments[0].click();", getUploadImageButton());
		WebElement profilePhotoUpload = driver.findElement(By.xpath("//input[@type='file']"));
		profilePhotoUpload.sendKeys(pathToTheImage);
	}
	public void removeProfileImage () {
		js.executeScript("arguments[0].click();", getRemoveImageButton());
	}
	public void personalInformation (String country, String state, String city, String firstName, 
							String lastName, String address, String phoneNumber,String zipCode) {
		getFirstNameInput().clear();
		getLastNemeInput().clear();
		getAddressInput().clear();
		getPhoneInput().clear();
		getZipCodeInput().clear();
		countrySelect(country);
		stateSelect(state);
		citySelect(city);
		getFirstNameInput().sendKeys(firstName);
		getLastNemeInput().sendKeys(lastName);
		getAddressInput().sendKeys(address);
		getPhoneInput().sendKeys(phoneNumber);
		getZipCodeInput().sendKeys(zipCode);
		js.executeScript("arguments[0].click();", getSaveButton());
		
	}

}
