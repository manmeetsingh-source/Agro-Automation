package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public NavigationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Hamburger menu
	@FindBy(xpath = "//label[@for='checkbox-hamburger-menu']")
	public WebElement hamburgerMenu;

	// Pre-Auctions menu item
	@FindBy(xpath = "//span[text()='Pre Auction']/ancestor::div[contains(@class,'dropdown-title')]")
	private WebElement preAuctionMenu;

	// Catalog Listing submenu item
	@FindBy(xpath = "//a[contains(@href, '/post-auth/pre-auction/catalog-listing')]")
	public WebElement catalogListingLink;

	@FindBy(xpath = "//h5[normalize-space()='Catalog Listing']")
	private WebElement catalogListingHeader;

	// Navigation method
	public void navigateToCatalogListing() {

		wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu)).click();
		System.out.println("Clicked Hamburger");

		wait.until(ExpectedConditions.elementToBeClickable(preAuctionMenu)).click();
		System.out.println("Clicked Pre Auction");

		wait.until(ExpectedConditions.elementToBeClickable(catalogListingLink)).click();
		System.out.println("Clicked Catalog Listing");

		wait.until(ExpectedConditions.visibilityOf(catalogListingHeader));

		System.out.println("✅ Navigated Successfully: " + driver.getCurrentUrl());

		/* wait.until(ExpectedConditions.urlContains("/catalog")); */
	}
}
