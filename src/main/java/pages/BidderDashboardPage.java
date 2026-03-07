package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BidderDashboardPage {

	private WebDriverWait wait;
	private WebDriver driver;

	public BidderDashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class=\"post-login\"]//button[@id=\"dropdownBasic1\"]//span")
	WebElement bidderNamespn;

	@FindBy(xpath = "//li[a[@id='navbarDropdownAuctions' and normalize-space()='Markets']]")
	WebElement auctionsDropdownbtn;

	@FindBy(xpath = "//li[a[@id='navbarDropdownAuctions']]//a[normalize-space()='Forward Auction']")
	WebElement forwardAuctionoption;

	@FindBy(xpath = "//input[@id=\"inputsearch\"][@placeholder=\"Type & Search\"]")
	WebElement searchboxallbidpage;

	@FindBy(xpath = "//div[@class=\"ps-2\"]//span//img")
	WebElement addAllWatchlistIcon;

	@FindBy(xpath = "//ul[@id=\"myTab\"]//button//span[contains(text(),\"Bid\")]")
	WebElement bidTabBtn;

	@FindBy(xpath = "//div[@class=\"row side-label-area\"]//span[contains(text(),\"Free Pool\")]")
	WebElement freepoolBalanceSpn;

	@FindBy(xpath = "//div[@class=\"row side-label-area\"]//span[contains(text(),\"Blocked Pool\")]")
	WebElement blockedpoolBalanceSpn;

	@FindBy(xpath = "//div[@class=\"side-label-area\"]//div[@ngbtooltip=\"Refresh Balance\"]//i")
	WebElement poolBalanceRefreshbtn;

	@FindBy(xpath = "//div[@class=\"tab-main-area\"]//button[contains(text(),\"S\")]")
	WebElement savebidButton;

	@FindBy(xpath = "//mat-table[@role='table']//mat-row[@role='row']")
	WebElement bidTableRow;

	// Actions

	public String getBidderLoginName() {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(bidderNamespn));
		String LoggedinBidderName = input.getAttribute("ng-reflect-ngb-tooltip");
		return LoggedinBidderName;

	}

	public void BidderDashboardPageLoad() {
		wait.until(ExpectedConditions.urlContains("/eauction/dashboard"));
	}

	public void selectforwardAuctionOption() {
		wait.until(ExpectedConditions.elementToBeClickable(auctionsDropdownbtn)).click();

		WebElement btn = wait.until(ExpectedConditions.visibilityOf(forwardAuctionoption));
		btn.click();

		wait.until(ExpectedConditions.urlContains("/market/forward-auction"));
		System.out.println("Forward Auctions option is selected");
	}

	public void enterLotName(String LotName) {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchboxallbidpage));
		input.sendKeys(LotName);
	}

	public void clickOnWatchlistBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)).click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)));
		System.out.println("Watchlist button is successfully clicked");

	}

	public void clickBidTab() {
		wait.until(ExpectedConditions.elementToBeClickable(bidTabBtn)).click();
		System.out.println("bid Tab button is successfully clicked");
	}

	public String getFreePoolBalance() {

		String text = wait.until(ExpectedConditions.visibilityOf(freepoolBalanceSpn)).getText();

		String amount = text.split(":")[1].trim();

		System.out.println("Free Pool Balance: " + amount);

		return amount;
	}

	public String getBlockedPoolBalance() {

		String text = wait.until(ExpectedConditions.visibilityOf(blockedpoolBalanceSpn)).getText();

		String amount = text.split(":")[1].trim();

		System.out.println("Blocked Pool Balance: " + amount);

		return amount;
	}

	public void enterPriceAndQtyForAllLots(String price, String qty) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-table[@role='table']")));

		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//mat-row[contains(@class,'mat-mdc-row') and not(@hidden)]")));

		System.out.println("Rows Found in the table: " + rows.size());

		for (int i = 0; i < rows.size(); i++) {

			WebElement row = rows.get(i);

			WebElement qtyInput = row.findElement(By.xpath(".//input[@placeholder='Bid Quantity']"));
			WebElement priceInput = row.findElement(By.xpath(".//input[@placeholder='Bid Price']"));
			WebElement multiSelectInput = row.findElement(By.xpath(".//input[@placeholder='Checkbox']"));

			qtyInput.clear();
			qtyInput.sendKeys(qty);

			priceInput.clear();
			priceInput.sendKeys(price);

			multiSelectInput.click();

			System.out.println("Entered values in row: " + (i + 1));
		}
	}

}
