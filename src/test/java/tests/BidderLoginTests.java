package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BidderDashboardPage;
import pages.BidderLoginPage;
import utils.ConfigReader;

public class BidderLoginTests extends BaseTest {

	@Test(description = "Verify Bidder can login with valid Credentials")
	public void verifyBidderLogin() {

		// Load BidderLoginUrl URL from config file
		driver.get(ConfigReader.getProperty("BidderLoginUrl"));

		// Create page object
		BidderLoginPage bidderLoginPage = new BidderLoginPage(driver);

		// Fetch credentials from config
		String bidderLoginName = ConfigReader.getProperty("bidder1LoginName");
		String bidderLoginPassword = ConfigReader.getProperty("bidder1LoginPassword");
		String bidder1Name = ConfigReader.getProperty("bidder1Name");

		// Perform Bidder Login

		bidderLoginPage.LoginAsBidder(bidderLoginName, bidderLoginPassword);

		BidderDashboardPage bidderDashBoardPage = new BidderDashboardPage(driver);

		bidderDashBoardPage.BidderDashboardPageLoad();

		String actualName = bidderDashBoardPage.getBidderLoginName();
		Assert.assertEquals(actualName, bidder1Name, " Username after login does not match!");
		System.out.println(" Login successful and username verified: " + actualName);
	}

}
