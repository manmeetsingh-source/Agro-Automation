package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import factory.DriverFactory;
import org.testng.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "✅ " + result.getMethod().getMethodName() + " passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "❌ " + result.getThrowable());
		String screenshotPath = ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(),
				result.getMethod().getMethodName());
		if (screenshotPath != null) {
			try {
				test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "⚠️ " + result.getMethod().getMethodName() + " skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

		// ✅ Open report in browser
		try {
			String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
			java.awt.Desktop.getDesktop().browse(new java.io.File(reportPath).toURI());
			System.out.println("📊 Extent Report opened in browser: " + reportPath);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("⚠️ Could not open Extent Report automatically.");
		}
	}
}
