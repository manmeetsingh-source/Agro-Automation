package tests;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NavigationPage;
import utils.ConfigReader;

public class H1ReportsNAFEDtests extends BaseTest{
	
	@Test(description = "Test NAFED H1 Report")
	public void verifyNafedH1Report() throws IOException, InterruptedException {
		// Load URL from config
		driver.get(ConfigReader.getProperty("baseUrl"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"),
				ConfigReader.getProperty("otp"));
		
		NavigationPage nav = new NavigationPage(driver);
		nav.navigateToCatalogListing();

		
	}

}
