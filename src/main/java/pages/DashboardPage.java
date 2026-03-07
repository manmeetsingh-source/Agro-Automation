package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utils.ConfigReader;

import java.time.Duration;

public class DashboardPage {

	private WebDriverWait wait;

	@FindBy(css = "p.user-name-area")
	private WebElement userNameLabel;

	@FindBy(xpath = "//label[@for='checkbox-hamburger-menu']")
	private WebElement hamburgerMenu;

	String expectedUser = ConfigReader.getProperty("LoginUsername");

	public DashboardPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	public void waitForDashboardToLoad() {
		wait.until(ExpectedConditions.urlContains("/home"));

		wait.until(ExpectedConditions.textToBePresentInElement(userNameLabel, expectedUser));

		wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
	}

	public void clickHamburgerMenu() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cdk-overlay-backdrop")));
		wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu)).click();
	}

	public String getLoggedInUserName() {
		waitForDashboardToLoad();
		return userNameLabel.getText();
	}

	public boolean isUserNameDisplayed(String expectedName) {
		waitForDashboardToLoad();
		return userNameLabel.getText().equalsIgnoreCase(expectedName);
	}
}