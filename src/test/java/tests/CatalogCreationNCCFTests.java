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

public class CatalogCreationNCCFTests extends BaseTest {

	@Test(description = "Catalog Creation for NCCF Auction")
	public void verifyCatalogCreationNCCF() throws IOException, InterruptedException {
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
		Object[][] data = ExcelUtil.getTestData("CatalogData.xlsx","Catalog_Data");
		String organization = data[1][1].toString(); // second row, first column
		String Suborganization = data[1][2].toString(); // second row, second column
		String Template = data[1][3].toString(); // second row, third column
		String Code = data[1][4].toString();
		String Name = data[1][5].toString();
		String AuctionType = data[1][6].toString();
		String AuctionRule1 = data[1][7].toString();
		String AuctionRule2 = data[1][8].toString();
		String SmsGroup = data[1][9].toString();
		String LotVal = data[1][10].toString();
		String BidBasisVal = data[1][11].toString();
		String UomVal = data[1][12].toString();
		String MultiFactorVal = data[1][13].toString();
		String fileLocation = "NCCF_Techformat.xlsx";
		String commodity = "Mustard";

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
		categorySelectionPage.enterNCCFCategorySelectionValues(LotVal, UomVal, BidBasisVal, MultiFactorVal);
		ExcelUtil.generateDynamicLots(fileLocation, "Techformat", commodity);
		uploadTechFormatPage.techformatFileUpload(fileLocation);
		catalogImageVideoPage.clickImagePageContinuebtn();
		emdRulePage.selectEMDrule();
		proceedToPublish.publishCatalog();
		
		

	}
}
