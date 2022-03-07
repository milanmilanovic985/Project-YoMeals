package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
		
	}
	public WebElement getAddToCartButton () {
		return driver.findElement(By.linkText("Add To Cart"));
	}
	public WebElement getQuantityInput () {
		return driver.findElement(By.name("product_qty"));
	}
	public WebElement getFavouriteButton () {
		return driver.findElement(By.id("item_119"));
	}
	public void addMealToCart(int mealQuantity) {
		String quantity = Integer.toString(mealQuantity);
		getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		getQuantityInput().sendKeys(quantity);
		js.executeScript("arguments[0].click();", getAddToCartButton());
		
	}
	public void addToFavourite () {
		js.executeScript("arguments[0].click();", getFavouriteButton());
	}

}
