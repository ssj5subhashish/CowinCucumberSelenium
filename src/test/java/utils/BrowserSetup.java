package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;

public class BrowserSetup {

    public static WebDriver driver;
    static String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "drivers" + File.separator;

    public static WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver firefoxDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver edgeDriver() {
        System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
        driver = new EdgeDriver();
        return driver;
    }

}
