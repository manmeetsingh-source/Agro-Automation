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

	private WebDriverWait wait;
	private WebDriver driver;

	public BidderLoginPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// locators
	@FindBy(xpath = "//form//input[@id=\"username\"]")
	private WebElement bidderUsernameField;

	@FindBy(xpath = "//form//input[@id=\"password\"]")
	private WebElement bidderPasswordField;

	@FindBy(xpath = "//form//input[@id=\"check2\"]")
	private WebElement bidderTermsandConditionsCheckbox;

	@FindBy(xpath = "//form//button[@type=\"submit\"]")
	private WebElement clickOnBidderLoginbtn;

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

		WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		By popupHeader = By.xpath("//div[contains(@class,'modal-content')]//h2[contains(text(),'Session Exists')]");

		By yesButton = By.xpath("//div[contains(@class,'modal-content')]//button[normalize-space()='Yes']");

		try {

			popupWait.until(ExpectedConditions.visibilityOfElementLocated(popupHeader));

			System.out.println("Already bidder session exists");

			WebElement yesBtnElement = popupWait.until(ExpectedConditions.elementToBeClickable(yesButton));

			yesBtnElement.click();

			// Wait for popup to disappear
			popupWait.until(ExpectedConditions.invisibilityOfElementLocated(popupHeader));

			System.out.println("Existing session handled successfully");

		} catch (Exception e) {
			System.out.println("No Existing Session Found");
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
