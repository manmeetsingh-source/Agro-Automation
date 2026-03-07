package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EMDRulePage {

	WebDriver driver;
	WebDriverWait wait;

	public EMDRulePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(xpath = "//*[@id=\"table\"]/tbody/tr[1]/td[1]/input")
	public WebElement emdrulecheckboxbtn;
	
	@FindBy(xpath = "//*[@id=\"table\"]/tbody/tr[6]/td[1]/input")
	public WebElement emdruleEnglishcheckboxbtn;

	@FindBy(xpath = "//app-emd-auction-tab/div[2]/div/app-common-btn/button")
	public WebElement EMDruleSaveandContinuebtn;

	// Actions

	public void selectEMDrule() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emdrulecheckboxbtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
		
		WebElement savebtn = wait.until(ExpectedConditions.elementToBeClickable(EMDruleSaveandContinuebtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", savebtn);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", savebtn);

	}
	
	public void selectEMDruleEnglish() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emdruleEnglishcheckboxbtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
		
		WebElement savebtn = wait.until(ExpectedConditions.elementToBeClickable(EMDruleSaveandContinuebtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", savebtn);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", savebtn);

	}

}
