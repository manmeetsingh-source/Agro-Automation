package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogImageVideoPage {

	WebDriver driver;
	WebDriverWait wait;

	public CatalogImageVideoPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//app-image-video-tab//span[normalize-space()='CONTINUE']/ancestor::button")
	public WebElement ImagePageContinuebtn;

	// Actions

	public void clickImagePageContinuebtn() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(ImagePageContinuebtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
	}

}
