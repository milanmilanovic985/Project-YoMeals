package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage{

	public CartSummaryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	public WebElement getClearAllButton () {
		return driver.findElement(By.xpath("//*[@class='cart-head']/a[2]"));
	}
	public void clearAll () {
		js.executeScript("arguments[0].click();", getClearAllButton());
	}

}
