package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BidderLoginPage {

	public WebDriverWait wait;
	public WebDriver driver;

	public BidderLoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// locators
	@FindBy(xpath = "//form//input[@id=\"username\"]")
	public WebElement bidderUsernameField;

	@FindBy(xpath = "//form//input[@id=\"password\"]")
	public WebElement bidderPasswordField;

	@FindBy(xpath = "//form//input[@id=\"check2\"]")
	public WebElement bidderTermsandConditionsCheckbox;

	@FindBy(xpath = "//form//button[@type=\"submit\"]")
	public WebElement clickOnBidderLoginbtn;

	// Actions

	public void enterBidderName(String bidderName) {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(bidderUsernameField));
		input.clear();
		input.sendKeys(bidderName);

	}

	public void enterBidderPassword(String bidderPassword) {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(bidderPasswordField));
		input.clear();
		input.sendKeys(bidderPassword);

	}

	public void selectTermsandConditions() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(bidderTermsandConditionsCheckbox));
		input.click();
	}

	public void clickOnBidderLoginbtn() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(clickOnBidderLoginbtn));
		input.click();
	}

	public void HandleAlreadyLoggedInPopUp() {

		WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			popupWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//p[contains(text(),'Session already exists, do you want to close the previous session?')]")));

			System.out.println("Existing session detected — handling popup");

			WebElement acceptButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]"));
			acceptButton.click();

			System.out.println("Popup handled successfully.");

		} catch (Exception e) {
			System.out.println("No existing session popup found.");
		}
	}

	public void LoginAsBidder(String bidderName, String bidderPassword) {
		enterBidderName(bidderName);
		enterBidderPassword(bidderPassword);
		selectTermsandConditions();
		clickOnBidderLoginbtn();
		HandleAlreadyLoggedInPopUp();
	}

}
