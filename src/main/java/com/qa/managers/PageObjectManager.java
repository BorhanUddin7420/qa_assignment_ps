package com.qa.managers;


import com.pages.CurrencyCalcPage;
import com.qa.util.ElementUtil;
import org.openqa.selenium.WebDriver;

public class PageObjectManager extends DriverManager {
    public DriverManager driverManager;
    public ElementUtil elementUtil;
    public CurrencyCalcPage currencyCalcPage;
    protected WebDriver driver = getDriver();

    public void launchBrowser() {
        driverManager = new DriverManager();
        elementUtil = new ElementUtil(driver);
        currencyCalcPage = new CurrencyCalcPage(driver);

    }

}
