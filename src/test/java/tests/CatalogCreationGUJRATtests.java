package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CatalogImageVideoPage;
import pages.CatalogListingPage;
import pages.CatalogPage;
import pages.CatalogSchedulePage;
import pages.CategorySelectionPage;
import pages.EMDRulePage;
import pages.LoginPage;
import pages.NavigationPage;
import pages.ProceedtoPublishPage;
import pages.UploadTechformatPage;
import utils.ConfigReader;
import utils.ExcelUtil;

public class CatalogCreationGUJRATtests extends BaseTest {

	@Test(description = "Catalog Creation for GUJRAT Auction")
	public void verifyCatalogCreationGUJRAT() throws IOException, InterruptedException {
		// Load URL from config
		driver.get(ConfigReader.getProperty("baseUrl"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"),
				ConfigReader.getProperty("otp"));

		NavigationPage nav = new NavigationPage(driver);
		nav.navigateToCatalogListing();

		// Simple verification
		Assert.assertTrue(driver.getCurrentUrl().contains("catalog-listing"), "❌ Not on Catalog Listing page!");

		CatalogListingPage catalogListingPage = new CatalogListingPage(driver);
		catalogListingPage.CatalogCreate();

		// Read Organization from excel
		Object[][] data = ExcelUtil.getTestData("Catalog_Data");
		String organization = data[2][1].toString(); // Third row, first column
		String Suborganization = data[2][2].toString(); // Third row, second column
		String Template = data[2][3].toString(); // Third row, third column
		String Code = data[2][4].toString();
		String Name = data[2][5].toString();
		String AuctionType = data[2][6].toString();
		String AuctionRule1 = data[2][7].toString();
		String AuctionRule2 = data[2][8].toString();
		String SmsGroup = data[2][9].toString();
		String LotVal = data[2][10].toString();
		String BidBasisVal = data[2][11].toString();
		String UomVal = data[2][12].toString();
		String MultiFactorVal = data[2][13].toString();
		String fileLocation = "GUJRAT_TechFormat.xlsx";

		CatalogPage catalogPage = new CatalogPage(driver);
		CatalogSchedulePage catalogSchedulePage = new CatalogSchedulePage(driver);
		CategorySelectionPage categorySelectionPage = new CategorySelectionPage(driver);
		UploadTechformatPage uploadTechFormatPage = new UploadTechformatPage(driver);
		CatalogImageVideoPage catalogImageVideoPage = new CatalogImageVideoPage(driver);
		EMDRulePage emdRulePage = new EMDRulePage(driver);
		ProceedtoPublishPage proceedToPublish = new ProceedtoPublishPage(driver);

		catalogPage.selectOrganization(organization);
		System.out.println("Organization from Excel: '" + organization + "'");
		catalogPage.selectSubOrganization(Suborganization);
		System.out.println("SubOrganization from Excel: '" + Suborganization + "'");
		catalogPage.selectTemplate(Template);
		catalogPage.enterCatalogCode(Code);
		catalogPage.enterCatalogName(Name, Code);
		catalogPage.selectStartDateAndTime();
		catalogPage.selectEndDateAndTime();
		catalogPage.selectAuctionType(AuctionType);
		catalogPage.selectAuctionRule(AuctionRule1, AuctionRule2);
		catalogPage.selectSmsGroup(SmsGroup);
		catalogPage.fileUpload();
		catalogPage.enterMandateNum();
		catalogPage.saveBasicDetails();
		catalogSchedulePage.ClickCatalogScheduleContinue();
		categorySelectionPage.enterGujratCategorySelectionValues(LotVal, UomVal, BidBasisVal, MultiFactorVal);
		uploadTechFormatPage.techformatFileUpload(fileLocation);
		catalogImageVideoPage.clickImagePageContinuebtn();
		emdRulePage.selectEMDruleEnglish();
		proceedToPublish.publishCatalog();

	}

}
