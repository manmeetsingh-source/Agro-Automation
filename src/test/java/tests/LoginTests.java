package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTests extends BaseTest {

	@Test(description = "Verify user can log in with valid credentials")
	public void verifyLogin() {
		// Load URL from config
		driver.get(ConfigReader.getProperty("baseUrl"));

		// Create page object
		LoginPage loginPage = new LoginPage(driver);

		// Fetch credentials from config
		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");
		String otp = ConfigReader.getProperty("otp");

		// Perform login
		loginPage.login(username, password, otp);

		DashboardPage dashboardPage = new DashboardPage(driver);

		// Wait a moment for page to load
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}

		String actualName = dashboardPage.getLoggedInUserName();
		Assert.assertEquals(actualName, "Manmeet Singh", " Username after login does not match!");
		System.out.println(" Login successful and username verified: " + actualName);
	}
}
