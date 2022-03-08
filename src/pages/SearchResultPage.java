package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage{

	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	
	}
	
	public List<WebElement> getSearchResults() {
		List<WebElement> searchResultList = driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return searchResultList;
	}
	
	
	

}
