package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage{

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	
	public WebElement getAuthButton () {
		return driver.findElement(By.xpath("//*[contains (@class, 'after-arrow user-trigger-js')]"));
	}
	public WebElement getMyAccountButton () {
		return driver.findElement(By.xpath("//*[@class='my-account-dropdown']//li/a"));
	}
	public WebElement getLogoutButton () {
		return driver.findElement(By.xpath("//*[@class='my-account-dropdown']//li[2]/a"));
	}
	public void logout () {
		js.executeScript("arguments[0].click();", getLogoutButton());
	}

}
