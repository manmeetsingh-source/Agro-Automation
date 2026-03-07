package pages;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogListingPage {
	
	private WebDriverWait wait;

	public CatalogListingPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	//Locators

	@FindBy(xpath = "//*[name()='svg' and contains(@class,'feather-plus-circle')]")
	private WebElement catalogPlusIcon;
		
	// actions
	
	public void CatalogCreate() {
		wait.until(ExpectedConditions.elementToBeClickable(catalogPlusIcon)).click();
		wait.until(ExpectedConditions.urlContains("/catalog-add"));
		
	}

}
