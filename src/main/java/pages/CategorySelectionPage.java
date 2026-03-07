package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategorySelectionPage {

	WebDriver driver;
	WebDriverWait wait;

	public CategorySelectionPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "//tbody/tr[1]/td[5]/app-common-icon")
	public WebElement categoryGramPlusbtn;

	@FindBy(xpath = "//tbody/tr[1]/td[5]/app-common-icon")
	public WebElement categoryMustardPlusbtn;

	@FindBy(xpath = "//tbody/tr[2]/td[5]/app-common-icon")
	public WebElement categoryMoongPlusbtn;

	@FindBy(xpath = "//app-category-selection-top-table//a[normalize-space()='2']")
	public WebElement paginationNumberTwobtn;

	@FindBy(xpath = "//tbody/tr[6]/td[5]/app-common-icon")
	public WebElement categoryBajraHafedPlusbtn;

	@FindBy(xpath = "//tbody/tr[@class='cdk-drag ng-star-inserted']/td[4]/app-common-input[1]/div[1]/input[1]")
	public WebElement categoryLotInputbtn;

	@FindBy(xpath = "//tbody/tr[@class='cdk-drag ng-star-inserted']/td[7]/app-common-select[1]/select[1]")
	public WebElement categoryBidBasisbtn;

	@FindBy(xpath = "//tbody/tr[@class='cdk-drag ng-star-inserted']/td[8]/app-common-select[1]/select[1]")
	public WebElement categoryUOMbtn;

	@FindBy(xpath = "//tbody/tr[@class='cdk-drag ng-star-inserted']/td[11]/app-common-input[1]/div[1]/input[1]")
	public WebElement categoryMultiplyingFactor;

	@FindBy(xpath = "//app-common-btn[@class='btn-lg btn-secondary false']//span[normalize-space()='DOWNLOAD']")
	public WebElement techformatDownloadbtn;

	@FindBy(xpath = "//button[@class='btn btn-primary w-90 ng-star-inserted']//span[contains(text(),'SAVE AND CONTINUE')]")
	public WebElement techformatSave;

	// Actions

	public void ClickCategoryGramPlusIcon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		wait.until(ExpectedConditions.elementToBeClickable(categoryGramPlusbtn)).click();

	}

	public void ClickCategoryMustardPlusIcon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		wait.until(ExpectedConditions.elementToBeClickable(categoryMustardPlusbtn)).click();

	}

	public void ClickCategoryMoongPlusIcon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		wait.until(ExpectedConditions.elementToBeClickable(categoryMoongPlusbtn)).click();

	}

	public void ClickCategoryPaginationNumberTwobtn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		wait.until(ExpectedConditions.elementToBeClickable(paginationNumberTwobtn)).click();

	}

	public void ClickCategoryBajraHafedPlusIcon() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
		wait.until(ExpectedConditions.elementToBeClickable(categoryBajraHafedPlusbtn)).click();

	}

	public void enterLotVal(String lotVal) {
		WebElement lotInput = wait.until(ExpectedConditions.elementToBeClickable(categoryLotInputbtn));
		lotInput.clear();
		lotInput.sendKeys(lotVal);
		System.out.println("Lot value is entered in input:" + lotVal);
	}

	public void enterMultiplyingFactor(String MultiFactor) {
		WebElement MultiInput = wait.until(ExpectedConditions.elementToBeClickable(categoryMultiplyingFactor));
		MultiInput.clear();
		MultiInput.sendKeys(MultiFactor);
		System.out.println("Multiplying Factor is entered in input:" + MultiFactor);
	}

	public void enterUOMVal(String UOMVal) {
		wait.until(ExpectedConditions.elementToBeClickable(categoryUOMbtn)).click();
		By optionLocator = By
				.xpath("//*[@id='cdk-drop-list-0']/table/tbody/tr/td[8]/app-common-select/select/option[@value='"
						+ UOMVal + "']");
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		option.click();

	}

	public void enterAuctionRuleVal(String auctionRule) {
		wait.until(ExpectedConditions.elementToBeClickable(categoryUOMbtn)).click();
		By optionLocator = By
				.xpath("//*[@id='cdk-drop-list-0']/table/tbody/tr/td[8]/app-common-select/select/option[@value='"
						+ auctionRule + "']");
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		option.click();

	}

	public void enterBidBasis(String BidBasisVal) {
		wait.until(ExpectedConditions.elementToBeClickable(categoryBidBasisbtn)).click();
		By optionLocator = By
				.xpath("//*[@id='cdk-drop-list-0']/table/tbody/tr/td[7]/app-common-select/select/option[@value='"
						+ BidBasisVal + "']");
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

		option.click();

	}

	public void ClickDownloadTechformatBtn() {
		WebElement Downloadinput = wait.until(ExpectedConditions.elementToBeClickable(techformatDownloadbtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", Downloadinput);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Downloadinput);

	}

	public void ClickSaveandContinueCategorybtn() {
		WebElement Saveinput = wait.until(ExpectedConditions.elementToBeClickable(techformatSave));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", Saveinput);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Saveinput);

	}

	public void enterCategorySelectionValues(String lotVal, String UOMVal, String BidBasisVal, String MultiFactor) {
		ClickCategoryGramPlusIcon();
		enterLotVal(lotVal);
		enterUOMVal(UOMVal);
		enterBidBasis(BidBasisVal);
		enterMultiplyingFactor(MultiFactor);
		ClickDownloadTechformatBtn();
		ClickSaveandContinueCategorybtn();
	}

	public void enterNCCFCategorySelectionValues(String lotVal, String UOMVal, String BidBasisVal, String MultiFactor) {
		ClickCategoryMustardPlusIcon();
		enterLotVal(lotVal);
		enterUOMVal(UOMVal);
		enterBidBasis(BidBasisVal);
		enterMultiplyingFactor(MultiFactor);
		ClickDownloadTechformatBtn();
		ClickSaveandContinueCategorybtn();
	}

	public void enterGujratCategorySelectionValues(String lotVal, String UOMVal, String BidBasisVal,
			String MultiFactor) {
		ClickCategoryMoongPlusIcon();
		enterLotVal(lotVal);
		enterUOMVal(UOMVal);
		enterBidBasis(BidBasisVal);
		enterMultiplyingFactor(MultiFactor);
		ClickDownloadTechformatBtn();
		ClickSaveandContinueCategorybtn();
	}

	public void enterHAFEDCategorySelectionValues(String lotVal, String UOMVal, String BidBasisVal,
			String MultiFactor) {
		ClickCategoryPaginationNumberTwobtn();
		ClickCategoryBajraHafedPlusIcon();
		enterLotVal(lotVal);
		enterUOMVal(UOMVal);
		enterBidBasis(BidBasisVal);
		enterMultiplyingFactor(MultiFactor);
		ClickDownloadTechformatBtn();
		ClickSaveandContinueCategorybtn();
	}

}
