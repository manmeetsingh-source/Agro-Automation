package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.BidderDashboardPage;
import pages.BidderLoginPage;
import utils.ConfigReader;

public class RoundOneBiddingTests extends BaseTest {

	@Test(description = "Verify Bidder can Bid in Auction Round 1 NAFED")
	public void VerifyBiddingRound1NAFED() {

		// Load BidderLoginUrl URL from config file
		driver.get(ConfigReader.getProperty("BidderLoginUrl"));

		// Fetch credentials from config
		String bidderLoginName = ConfigReader.getProperty("bidder1LoginName");
		String bidderLoginPassword = ConfigReader.getProperty("bidder1LoginPassword");
		String LotNameforbidPage = "Alpha-Auto/Gram/Lot";
		String Price = "100";
		String Qty = "100";

		// Create page object
		BidderDashboardPage bidderDashboardPage = new BidderDashboardPage(driver);
		BidderLoginPage bidderLoginPage = new BidderLoginPage(driver);

		bidderLoginPage.LoginAsBidder(bidderLoginName, bidderLoginPassword);
		bidderDashboardPage.BidderDashboardPageLoad();
		bidderDashboardPage.selectforwardAuctionOption();
		bidderDashboardPage.enterLotName(LotNameforbidPage);
		bidderDashboardPage.clickOnWatchlistBtn();
		bidderDashboardPage.clickBidTab();
		bidderDashboardPage.getFreePoolBalance();
		bidderDashboardPage.getBlockedPoolBalance();
		bidderDashboardPage.enterPriceAndQtyForAllLots(Price, Qty);

	}

}
