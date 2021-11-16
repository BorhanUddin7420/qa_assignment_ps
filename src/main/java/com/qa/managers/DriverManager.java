package com.qa.managers;

import com.qa.enums.DriverType;
import com.qa.enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverManager {


    public static WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private void createDriver() {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver am not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.addArguments("firefox.switches", "-damable-extensions");
                fOptions.addArguments("--start-maximized");
                fOptions.addArguments("--damable-web-security");
                fOptions.addArguments("--damable-notifications");
                fOptions.addArguments("--no-proxy-server");
                driver = new FirefoxDriver(fOptions);
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments("chrome.switches", "-damable-extensions");
                cOptions.addArguments("--start-maximized");
                cOptions.addArguments("--damable-web-security");
                cOptions.addArguments("--damable-notifications");
                cOptions.addArguments("--no-proxy-server");

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                cOptions.setExperimentalOption("prefs", prefs);
                cOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                driver = new ChromeDriver(cOptions);
                break;
            case INTERNETEXPLORER:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
            driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}
