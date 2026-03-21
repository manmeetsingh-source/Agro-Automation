package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.BidderDashboardPage;
import pages.BidderLoginPage;
import utils.ConfigReader;
import utils.ExcelUtil;
import utils.ExtentManager;

public class RoundOneBiddingHAFEDTests extends BaseTest{
	
	@Test(description = "Verify Bidder can Bid in Auction Round 1 HAFED")
	public void VerifyBiddingRound1HAFED() throws IOException {

		ExtentManager.initReport();
		ExtentManager.createTest("Round 1 Bidding HAFED Test");

		// Load BidderLoginUrl URL from config file
		driver.get(ConfigReader.getProperty("BidderLoginUrl"));

		// Fetch credentials from config
		String LotNameforbidPage = "HFD/BAJRA/Lot";

		// Create page object
		BidderDashboardPage bidderDashboardPage = new BidderDashboardPage(driver);
		BidderLoginPage bidderLoginPage = new BidderLoginPage(driver);

		// Read BidDetails Data from BidDetails Excel file
		Object[][] biddata = ExcelUtil.getTestData("BidDetailsDataHAFED.xlsx", "BidData");

		for (int i = 0; i < 3; i++) {

			String bidderLoginName = biddata[i][1].toString();
			String bidderLoginPassword = biddata[i][2].toString();
			String price = biddata[i][5].toString();
			String qty = biddata[i][6].toString();
			// double biddername = Double.parseDouble(bidderLoginName);

			System.out.println("Running test for bidder: " + bidderLoginName);

			bidderLoginPage.LoginAsBidder(bidderLoginName, bidderLoginPassword);
			bidderDashboardPage.BidderDashboardPageLoad();
			bidderDashboardPage.selectforwardAuctionOption();
			bidderDashboardPage.clickOnRefreshbtn();

			// Set Freepool balance before bid in the BidDetails Sheet
			double freepoolBalanceBeforeBid = Double.parseDouble(bidderDashboardPage.getFreePoolBalance());
			ExcelUtil.writeData("BidDetailsDataHAFED.xlsx", "BidData", i + 1, 3, freepoolBalanceBeforeBid); // second
																												// row
																												// third
																												// column
			System.out.println(
					"Free pool balance is successfully set in BidDetailsData sheet:" + freepoolBalanceBeforeBid);

			// Set Blockpool balance before bid in the BidDetails Sheet
			double blockpoolBalanceBeforeBid = Double.parseDouble(bidderDashboardPage.getBlockedPoolBalance());
			ExcelUtil.writeData("BidDetailsDataHAFED.xlsx", "BidData", i + 1, 4, blockpoolBalanceBeforeBid); // second
																												// row
																												// fourth
																												// column
			System.out.println(
					"Blocked pool balance is successfully set in BidDetailsData sheet:" + blockpoolBalanceBeforeBid);

			// Get Bid Price from BidDetails sheet and Set Price in EMD Calculation Sheet
			double BidPrice = Double.parseDouble(price); // second row, Sixth column
			ExcelUtil.writeData("EMD_Workbook.xlsx", "EMDCalculation", 3, 0, BidPrice);
			System.out.println("BidPrice  is successfully set in EMDCalculation sheet:" + BidPrice);

			double BidQuantity = Double.parseDouble(qty); // second row, seventh column
			ExcelUtil.writeData("EMD_Workbook.xlsx", "EMDCalculation", 3, 1, BidQuantity);
			System.out.println("BidQuantity  is successfully set in EMDCalculation sheet:" + BidQuantity);

			// Get Calculated EMD Value from EMD sheet and Set value to BidDetails Sheet

			// Read EMDCalculation Data from EMDWorkbook Excel file
			Object[][] EmdData = ExcelUtil.getTestData("EMD_Workbook.xlsx", "EMDCalculation");

			double CalculatedEmdValue = Double.parseDouble(EmdData[0][5].toString()); // second row, ninth column
			ExcelUtil.writeData("BidDetailsDataHAFED.xlsx", "BidData", i + 1, 7, CalculatedEmdValue);
			System.out.println(
					"CalculatedEmdValue balance is successfully set in BidDetailsData sheet:" + CalculatedEmdValue);
			bidderDashboardPage.deAttachAllLots();
			bidderDashboardPage.enterLotName(LotNameforbidPage);
			bidderDashboardPage.clickOnWatchlistBtn();
			bidderDashboardPage.clickBidTab();
			bidderDashboardPage.enterPriceAndQtyForAllLots(price, qty);
			bidderDashboardPage.clickSaveBidbtn();

			bidderDashboardPage.clickOnRefreshbtn();

			// Set Freepool balance After Bid in the BidDetails Sheet
			double freepoolBalanceAfterBid = Double.parseDouble(
					bidderDashboardPage.getUpdatedFreePoolBalance(freepoolBalanceBeforeBid, CalculatedEmdValue));
			ExcelUtil.writeData("BidDetailsDataHAFED.xlsx", "BidData", i + 1, 8, freepoolBalanceAfterBid); // second row
																											// third
																											// column
			System.out.println("Free pool balance after bid is successfully set in BidDetailsData sheet:"
					+ freepoolBalanceAfterBid);

			// Set Blockpool balance After Bid in the BidDetails Sheet
			double blockpoolBalanceAfterBid = Double.parseDouble(
					bidderDashboardPage.getUpdatedBlockedPoolBalance(blockpoolBalanceBeforeBid, CalculatedEmdValue));
			ExcelUtil.writeData("BidDetailsDataHAFED.xlsx", "BidData", i + 1, 9, blockpoolBalanceAfterBid); // second
																											// row
																											// fourth
																											// column
			System.out.println("Blocked pool balance after bid is successfully set in BidDetailsData sheet:"
					+ blockpoolBalanceAfterBid);

			bidderDashboardPage.calculateDifferenceAmount(freepoolBalanceBeforeBid, blockpoolBalanceBeforeBid,
					freepoolBalanceAfterBid, blockpoolBalanceAfterBid, CalculatedEmdValue, bidderLoginName);

			bidderDashboardPage.logoutBidder();

		}

	}


}
