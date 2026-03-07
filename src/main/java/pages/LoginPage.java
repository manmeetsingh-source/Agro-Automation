package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	// @FindBy
	@FindBy(xpath = "//input[@placeholder='AD Credential. e.g: firstname.lastname']")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[@id=\"tab1\"]/form/div[3]/app-common-btn/button/span")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@placeholder='OTP']")
	private WebElement otpField;

	@FindBy(xpath = "//*[@id=\"tab1\"]/form/div[4]/app-common-btn")
	private WebElement finalloginButton;

	// Action methods

	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}

	public void clickFinalLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(finalloginButton));
		finalloginButton.click();
	}

	public void enterOtp(String otp) {
		wait.until(ExpectedConditions.visibilityOf(otpField));
		otpField.clear();
		otpField.sendKeys(otp);
	}

	public void handleExistingSessionPopup() {

		WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			popupWait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//h5[contains(text(),'A SESSION ALREADY EXISTS')]")));

			System.out.println("Existing session detected — handling popup");

			WebElement firstOption = driver
					.findElement(By.xpath("(//label[contains(text(),'Logout my previous session')])[1]"));
			firstOption.click();

			WebElement acceptButton = driver
					.findElement(By.xpath("(//button[span[normalize-space(text())='ACCEPT']])[1]"));
			acceptButton.click();

			System.out.println("Popup handled successfully.");

		} catch (Exception e) {
			System.out.println("No existing session popup found.");
		}
	}

	public void login(String username, String password, String otp) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
		handleExistingSessionPopup();
		enterOtp(otp);
		clickFinalLogin();
	}

	// verify page title or error messages
	public String getPageTitle() {
		return driver.getTitle();
	}
}
