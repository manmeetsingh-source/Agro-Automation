package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadTechformatPage {

	WebDriver driver;
	WebDriverWait wait;

	public UploadTechformatPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "//app-common-btn//span[contains(text(),'UPLOAD')]")
	public WebElement uploadBtn;

	@FindBy(id = "formFile")
	private WebElement fileInput;

	@FindBy(xpath = "//span[normalize-space()='CONTINUE']/ancestor::button")
	private WebElement continueBtn;

	// actions

	public void techformatFileUpload(String fileLocation) {

		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\"+fileLocation;

		wait.until(ExpectedConditions.visibilityOf(fileInput));

		fileInput.sendKeys(filePath);

		wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));

		uploadBtn.click();

		System.out.println("File Uploaded Successfully");

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
		System.out.println("Techformat is verified");

	}
}
