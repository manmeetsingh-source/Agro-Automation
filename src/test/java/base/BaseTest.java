package base;

import factory.DriverFactory;
import utils.ConfigReader;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    
    public  final boolean DEV_MODE =
            Boolean.parseBoolean(ConfigReader.getProperty("devMode"));

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        ConfigReader.initConfig();
        String chosenBrowser = browser.isEmpty() ? ConfigReader.getProperty("browser") : browser;
        driver = DriverFactory.initDriver(chosenBrowser);
        System.out.println(" Browser launched: " + chosenBrowser);
    }
    
    public static void clickElementUsingJavascript(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
    


    @AfterClass(alwaysRun = true)
    public void tearDown() {
    	if (!DEV_MODE && driver != null) {
            DriverFactory.quitDriver();
            System.out.println(" Browser closed successfully.");
        } else {
            System.out.println(" DEV MODE active  Browser kept open.");
        }
    }
    
}
