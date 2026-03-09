package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CatalogPage {
	WebDriver driver;
	WebDriverWait wait;

	public CatalogPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "(//ng-select)[1]//div[contains(@class,'ng-select-container')]")
	public WebElement selectOrganizationbutton;

	@FindBy(xpath = "(//ng-select)[2]//div[contains(@class,'ng-select-container')]")
	public WebElement selectSubOrganizationbutton;

	@FindBy(xpath = "(//ng-select)[3]//div[contains(@class,'ng-select-container')]")
	public WebElement selectTemplatebutton;

	@FindBy(xpath = "//app-common-searchable-select[@formcontrolname=\"organization\"]//input")
	public WebElement organizationInput;

	@FindBy(xpath = "//app-common-searchable-select[@formcontrolname=\"suborganization\"]//input")
	public WebElement subOrganizationInput;

	@FindBy(xpath = "//app-common-searchable-select[@formcontrolname=\"catalogtemplate\"]//input")
	public WebElement templateInput;

	@FindBy(xpath = "//app-common-btn//button[.//span[normalize-space()='SAVE AND CONTINUE']]")
	public WebElement saveAndContinueButton;

	@FindBy(xpath = "//app-common-input[@formcontrolname='catCode']//input[@id='inputId']")
	public WebElement catalogCodeInput;

	@FindBy(xpath = "(//input[@id='inputId'])[2]")
	WebElement catcode;

	@FindBy(xpath = "//input[@placeholder='Catalog name']")
	public WebElement catalogNameInput;

	@FindBy(xpath = "//app-date-time-picker[@formcontrolname='catalogStartDateTime']//input[@type='date']")
	public WebElement startDateInput;

	@FindBy(xpath = "//select[@class='ng-star-inserted']")
	public WebElement startTimeInput;

	@FindBy(xpath = "//app-date-time-picker[@formcontrolname='catalogEndDateTime']//input[@type='date']")
	public WebElement endDateInput;

	@FindBy(xpath = "//app-date-time-picker[@formcontrolname='catalogEndDateTime']//select[@class='ng-star-inserted']")
	public WebElement endTimeInput;

	@FindBy(xpath = "//div[@id='ab9592d91ceb-0']//div[@class='form-check ng-star-inserted']////label[normalize-space()='Forward Auction']")
	public WebElement forwardAuctionValueLabel;

	@FindBy(xpath = "//span[@title='Clear']")
	public WebElement AuctionValClearbtn;

	@FindBy(xpath = "//div[contains(@class,'ng-select-container') and .//div[normalize-space()='Select Auction Type']]//span[contains(@class,'ng-arrow-wrapper')]")
	public WebElement AuctionTypeArrow;

	@FindBy(xpath = "//ng-multiselect-dropdown[@id='multiSelectV1']//span[contains(@class,'dropdown-btn')]")
	public WebElement auctionRuleDropdown;

	@FindBy(xpath = "//app-common-multi-select[@formcontrolname='smsGroupList']//span[contains(@class,'dropdown-btn')]")
	public WebElement smsGroupDropdown;

	@FindBy(xpath = "//app-common-upload[@id='pdfUpload']//input[@type='file']")
	public WebElement pdfUploadbtn;

	@FindBy(xpath = "//app-common-searchable-select[@formcontrolname='mandateNumber']//input[@type='text']")
	public WebElement mandateNumDropdown;

	@FindBy(xpath = "//div[contains(@class,'ng-option-marked') or contains(@class,'ng-option')][1]")
	public WebElement firtMandateOption;

	@FindBy(xpath = "//div[@class='col-md-12 d-flex align-items-end justify-content-end']//span[normalize-space()='SAVE AND CONTINUE']")
	public WebElement saveAndContinueBasicDetailsbtn;

	// actions

	public void selectOrganization(String organization) {
		// Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(selectOrganizationbutton)).click();

		// Wait for input to appear
		WebElement input = wait.until(ExpectedConditions.visibilityOf(organizationInput));

		// Type organization
		input.clear();
		input.sendKeys(organization);

		// Select exact match option
		By optionLocator = By.xpath(
				"//div[contains(@class,'ng-dropdown-panel')]//div[contains(@class,'ng-option')]//label[normalize-space()='"
						+ organization + "']");
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		option.click();

		// Wait for dropdown to close
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-dropdown-panel")));
	}

	public void selectSubOrganization(String subOrganization) {

		// Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(selectSubOrganizationbutton)).click();

		// Wait for input to appear
		WebElement input = wait.until(ExpectedConditions.visibilityOf(subOrganizationInput));
		wait.until(ExpectedConditions.elementToBeClickable(subOrganizationInput));

		// Type organization
		input.clear();
		input.sendKeys(subOrganization);

		// Select correct label option
		By optionLocator = By.xpath(
				"//div[contains(@class,'ng-dropdown-panel')]//div[contains(@class,'ng-option')]//label[normalize-space()='"
						+ subOrganization + "']");

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		option.click();

		// Wait for dropdown close
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-dropdown-panel")));
	}

	public void selectTemplate(String Template) {
		wait.until(ExpectedConditions.elementToBeClickable(selectTemplatebutton)).click();

		wait.until(ExpectedConditions.visibilityOf(templateInput)).sendKeys(Template + Keys.ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(saveAndContinueButton)).click();

		// Wait until Catalog Code field becomes visible and enabled
		wait.until(ExpectedConditions.and(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='inputId'])[2]")),
				ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='inputId'])[2]"))));
		System.out.println("Save & Continue completed, next section fully loaded.");
	}

	public void enterCatalogCode(String code) {

		String finalCode = code + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(catalogCodeInput));

		input.click();
		input.sendKeys(finalCode);

		//wait.until(ExpectedConditions.attributeToBe(input, finalCode, finalCode));

		System.out.println("Final Code: " + finalCode);

		System.out.println("Catalog Code Entered Successfully");
	}

	public void enterCatalogName(String Name, String code) {
		String finalCode = code + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		String finalName = finalCode + "/" + Name;

		System.out.println("Final Name: " + finalName);

		WebElement input = wait.until(ExpectedConditions.visibilityOf(catalogNameInput));

		input.click();
		input.sendKeys(Keys.CONTROL + "a");
		input.sendKeys(Keys.DELETE);
		input.sendKeys(finalName);

		input.sendKeys(Keys.TAB);

		System.out.println("Final Code: " + finalName);

		System.out.println("Catalog Code Entered Successfully");

	}

	public void selectStartDateAndTime() {

		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();

		LocalTime startSlot = LocalTime.of(11, 0);
		LocalTime endSlot = LocalTime.of(17, 0);

		LocalDate selectedDate;
		LocalTime selectedTime;

		// Before 11:00 -> today 11:00
		if (now.isBefore(startSlot)) {
			selectedDate = today;
			selectedTime = startSlot;
		}
		// Between 11:00 and 18:00
		else if (now.isBefore(endSlot)) {

			int minutes = now.getMinute();
			int nextMinutes = (minutes < 30) ? 30 : 0;
			int nextHour = (minutes < 30) ? now.getHour() : now.getHour() + 1;

			selectedTime = LocalTime.of(nextHour, nextMinutes);

			if (selectedTime.isAfter(endSlot)) {
				selectedDate = today.plusDays(1);
				selectedTime = startSlot;
			} else {
				selectedDate = today;
			}
		}
		// After 18:00 -> tomorrow 11:00
		else {
			selectedDate = today.plusDays(1);
			selectedTime = startSlot;
		}

		// Format values
		String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		String formattedTime = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"));

		// Wait and set Date
		wait.until(ExpectedConditions.elementToBeClickable(startDateInput));
		startDateInput.clear();
		startDateInput.sendKeys(formattedDate);

		// Wait and set Time
		wait.until(ExpectedConditions.elementToBeClickable(startTimeInput));
		Select select = new Select(startTimeInput);
		select.selectByVisibleText(formattedTime);

		System.out.println("Start Date Selected: " + formattedDate);
		System.out.println("Start Time Selected: " + formattedTime);
	}

	public void selectEndDateAndTime() {

		// Assume start date was today or tomorrow
		LocalDate endDate = LocalDate.now().plusDays(2);

		LocalTime now = LocalTime.now();

		// Keep same slot logic (11:00–18:00)
		LocalTime startSlot = LocalTime.of(11, 0);

		LocalTime selectedTime;

		if (now.isBefore(startSlot)) {
			selectedTime = startSlot;
		} else {
			int minutes = now.getMinute();
			int nextMinutes = (minutes < 30) ? 30 : 0;
			int nextHour = (minutes < 30) ? now.getHour() : now.getHour() + 1;

			selectedTime = LocalTime.of(nextHour, nextMinutes);

			if (selectedTime.isAfter(LocalTime.of(17, 0))) {
				selectedTime = startSlot;
			}
		}

		String formattedDate = endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		String formattedTime = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"));

		// ---- Set Date ----
		wait.until(ExpectedConditions.visibilityOf(endDateInput));

		wait.until(ExpectedConditions.elementToBeClickable(endDateInput));
		endDateInput.clear();
		endDateInput.sendKeys(formattedDate);

		// ---- Select Time using Select class ----
		wait.until(ExpectedConditions.elementToBeClickable(endTimeInput));

		Select select = new Select(endTimeInput);
		select.selectByVisibleText(formattedTime);

		System.out.println("End Date Selected: " + formattedDate);
		System.out.println("End Time Selected: " + formattedTime);
	}

	public void selectAuctionType(String auctionType) {

		WebElement auctionClearButton = wait.until(ExpectedConditions.elementToBeClickable(AuctionValClearbtn));

		auctionClearButton.click();

		WebElement auctionTypeArrow = wait.until(ExpectedConditions.elementToBeClickable(AuctionTypeArrow));

		auctionTypeArrow.click();

		By optionLocator = By
				.xpath("//div[contains(@class,'form-check')]//label[normalize-space()='" + auctionType + "']");

		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
		option.click();

		System.out.println("Auction Type Selected: " + auctionType);
	}

	public void selectRule(String ruleName) {

		By ruleLocator = By
				.xpath("//li[contains(@class,'multiselect-item-checkbox')]//div[normalize-space()='" + ruleName + "']");

		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(ruleLocator));

		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void selectAuctionRule(String auctionRule1, String auctionRule2) {

		// Open dropdown
		wait.until(ExpectedConditions.elementToBeClickable(auctionRuleDropdown)).click();

		selectRule(auctionRule1);

		if (auctionRule2 != null && !auctionRule2.isEmpty()) {
			selectRule(auctionRule2);
		}

		// Optional: close dropdown (click outside)
		auctionRuleDropdown.click();

		System.out
				.println("Auction Rules Selected: " + auctionRule1 + (auctionRule2 != null ? ", " + auctionRule2 : ""));

	}

	public void selectSmsGroup(String SMSVal) {

		wait.until(ExpectedConditions.elementToBeClickable(smsGroupDropdown)).click();

		selectGroup(SMSVal);

		System.out.println("Selected sms group is:" + SMSVal);

	}

	public void selectGroup(String groupName) {

		By groupLocator = By.xpath("//app-common-multi-select[@formcontrolname='smsGroupList']"
				+ "//li[contains(@class,'multiselect-item-checkbox')]//div[normalize-space()='" + groupName + "']");

		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(groupLocator));

		if (!checkbox.isSelected()) {
			checkbox.click();
		}

	}

	public void fileUpload() {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/SO_1673391_70426.pdf";

		WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(pdfUploadbtn));

		uploadElement.sendKeys(filePath);

		System.out.println("PDF is Uploaded Successfully");

	}

	public void enterMandateNum() {

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(mandateNumDropdown));
		input.click();
		input.sendKeys("VJ");

		WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firtMandateOption));

		firstOption.click();

		System.out.println("First mandate number selected");

	}

	public void saveBasicDetails() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(saveAndContinueBasicDetailsbtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);

		System.out.println("Basic details is saved");
	}

}
