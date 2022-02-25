package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	public WebElement getLoginButton () {
		return driver.findElement(By.xpath("//li[@class='filled']/a"));
	}
	public WebElement getEmailInput () {
		return driver.findElement(By.name("username"));
	}
	public WebElement getPasswordInput () {
		return driver.findElement(By.name("password"));
	}
	public WebElement getSubmitButton () {
		return driver.findElement(By.name("btn_submit"));
	}
	public void login(String email, String password) {
		getEmailInput().clear();
		getEmailInput().sendKeys(email);
		getPasswordInput().clear();
		getPasswordInput().sendKeys(password);
		getSubmitButton().click();
		
	}

}
