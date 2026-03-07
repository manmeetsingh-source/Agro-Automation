package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String testName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
			Files.createDirectories(Paths.get(folderPath));

			String screenshotPath = folderPath + testName + ".png";

			Files.copy(src.toPath(), Paths.get(screenshotPath), StandardCopyOption.REPLACE_EXISTING

			);

			return screenshotPath;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}