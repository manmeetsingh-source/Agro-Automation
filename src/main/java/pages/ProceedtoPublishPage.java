package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProceedtoPublishPage {

	WebDriver driver;
	WebDriverWait wait;

	public ProceedtoPublishPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//app-common-btn/button/span[normalize-space()='PROCEED TO PUBLISH']")
	public WebElement ProceedtoPublishbtn;

	// Actions

	public void publishCatalog() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(ProceedtoPublishbtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);

		wait.until(ExpectedConditions.urlContains("/catalog-listing"));

	}

}
