package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class CatalogSchedulePage {

	WebDriver driver;
	WebDriverWait wait;

	public CatalogSchedulePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "//app-common-btn[@class='btn-secondary btn-sm false']//span[normalize-space()='CONTINUE']")
	public WebElement catalogScheduleContinuebtn;
	
	
	// Actions
	
	public void ClickCatalogScheduleContinue() {
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(catalogScheduleContinuebtn));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		js.executeScript("arguments[0].click();", element);
		
		
	}
}
