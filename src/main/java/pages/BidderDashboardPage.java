package pages;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class BidderDashboardPage {

	private WebDriverWait wait;
	private WebDriver driver;

	public BidderDashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(35));
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

	@FindBy(xpath = "//div[@class=\"dropdown-menu show\"]//a[contains(text(),\"Logout\")]")
	WebElement logoutBidderbtn;

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

	public void clickOnRefreshbtn() {

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"side-label-area\"]//div[@ngbtooltip=\"Refresh Balance\"]//i")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		js.executeScript("arguments[0].click();", element);

		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(freepoolBalanceSpn)));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(blockedpoolBalanceSpn)));

		System.out.println("Pool balance is refreshed");
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

	public void deAttachAllLots() {
		wait.until(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)).click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)));
		System.out.println("Watchlist button is successfully clicked");

		wait.until(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)).click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(addAllWatchlistIcon)));
		System.out.println("Watchlist button is successfully clicked and deAttached all lots successfully");

	}

	public void clickBidTab() {
		wait.until(ExpectedConditions.elementToBeClickable(bidTabBtn)).click();
		System.out.println("bid Tab button is successfully clicked");
	}

	public String getFreePoolBalance() {

		wait.until(driver -> {
			String text = freepoolBalanceSpn.getText();
			return !text.contains(": 0");
		});

		String amount = freepoolBalanceSpn.getText().split(":")[1].trim();

		System.out.println("Free Pool Balance: " + amount);

		return amount;
	}

	public String getUpdatedFreePoolBalance(double oldBalance, double expectedEMD) {

	    wait.until(driver -> {
	        double newBalance = Double.parseDouble(getFreePoolBalance());

	        double expectedBalance = oldBalance - expectedEMD;

	        return Math.abs(newBalance - expectedBalance) < 0.5;
	    });

	    return getFreePoolBalance();
	}

	public String getBlockedPoolBalance() {

		String text = wait.until(ExpectedConditions.visibilityOf(blockedpoolBalanceSpn)).getText();

		String amount = text.split(":")[1].trim();

		System.out.println("Blocked Pool Balance: " + amount);

		return amount;
	}

	public String getUpdatedBlockedPoolBalance(double oldBalance, double expectedEMD) {

	    wait.until(driver -> {
	        double newBalance = Double.parseDouble(getBlockedPoolBalance());

	        double expectedBalance = oldBalance + expectedEMD;

	        return Math.abs(newBalance - expectedBalance) < 0.5;
	    });

	    return getBlockedPoolBalance();
	}

	public void enterPriceAndQtyForAllLots(String price, String qty) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-table[@role='table']")));

		List<WebElement> rows = driver
				.findElements(By.xpath("//mat-row[contains(@class,'mat-mdc-row') and not(@hidden)]"));

		System.out.println("Rows Found in the table: " + rows.size());

		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < rows.size(); i++) {

			// Re-locate row each time (prevents stale element issue)
			WebElement row = driver.findElements(By.xpath("//mat-row[contains(@class,'mat-mdc-row') and not(@hidden)]"))
					.get(i);

			// Scroll to row
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", row);

			WebElement qtyInput = row.findElement(By.xpath(".//input[@placeholder='Bid Quantity']"));
			WebElement priceInput = row.findElement(By.xpath(".//input[@placeholder='Bid Price']"));
			WebElement multiSelectInput = row.findElement(By.xpath(".//input[@placeholder='Checkbox']"));

			wait.until(ExpectedConditions.elementToBeClickable(qtyInput)).clear();
			qtyInput.sendKeys(qty);

			wait.until(ExpectedConditions.elementToBeClickable(priceInput)).clear();
			priceInput.sendKeys(price);

			wait.until(ExpectedConditions.elementToBeClickable(multiSelectInput)).click();

			System.out.println("Entered values in row: " + (i + 1));
		}
	}

	public void clickSaveBidbtn() {

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"tab-main-area\"]//button[contains(text(),\"S\")]")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", element);
		}

		System.out.println("Save Bid button is successfully clicked");
	}

	public double calculateDifferenceAmount(double freepoolBeforeBid, double blockedpoolBeforeBid,
			double freepoolAfterBid, double blockedpoolAfterBid, double calculatedEMD, String bidderLoginName) {

		double diffrenceFreepoolAmount = freepoolBeforeBid - freepoolAfterBid;
		System.out.println("Amount deducted from freepool is: " + diffrenceFreepoolAmount);

		double diffrenceBlockedpoolAmount = blockedpoolAfterBid - blockedpoolBeforeBid;
		System.out.println("Amount added to blockedpool is: " + diffrenceBlockedpoolAmount);

		double tolerance = 0.9;

		if (Math.abs(diffrenceFreepoolAmount - calculatedEMD) < tolerance
				&& Math.abs(diffrenceBlockedpoolAmount - calculatedEMD) < tolerance) {

			System.out.println("Calculation is working properly");

		} else {

			System.out.println("Calculation is NOT working properly");
		}

		String result;

		if (Math.abs(diffrenceFreepoolAmount - calculatedEMD) < tolerance
				&& Math.abs(diffrenceBlockedpoolAmount - calculatedEMD) < tolerance) {
			result = "<span style='color:green'><b>PASS</b></span>";
		} else {
			result = "<span style='color:red'><b>FAIL</b></span>";
		}

		String reportBlock = "<b>Bidder " + bidderLoginName + "</b><br>" + "--------------------------------<br>" +

				"<b>Freepool Details</b><br>" + "Freepool Before  : " + freepoolBeforeBid + "<br>"
				+ "Freepool After   : " + freepoolAfterBid + "<br>" + "Actual Deduction : " + diffrenceFreepoolAmount
				+ "<br><br>" +

				"<b>Blockedpool Details</b><br>" + "Blockedpool Before : " + blockedpoolBeforeBid + "<br>"
				+ "Blockedpool After  : " + blockedpoolAfterBid + "<br>" + "Actual Added       : "
				+ diffrenceBlockedpoolAmount + "<br><br>" +

				"Expected EMD : " + calculatedEMD + "<br>" + "<b>Result : " + result + "</b>";

		ExtentManager.getTest().info(reportBlock);

		return diffrenceFreepoolAmount;
	}

	public void logoutBidder() {

		wait.until(ExpectedConditions.elementToBeClickable(bidderNamespn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(logoutBidderbtn)).click();
		wait.until(ExpectedConditions.urlToBe("https://sit-agro.mjunction.in/"));
		driver.get("https://sit-agro.mjunction.in/login");
		wait.until(ExpectedConditions.urlContains("/login"));

	}

}
