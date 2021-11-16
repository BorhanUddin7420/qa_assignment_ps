package com.pages;

import com.qa.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;

public class CurrencyCalcPage {

    private final String baseURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
    WebDriver driver;
    NumberFormat format = NumberFormat.getNumberInstance();

    //1. Element Locators:
    private By sellInputField = By.xpath("//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[1]/input");
    private By buyInputField = By.xpath("//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[3]/input");
    private By seeRatesBtn = By.xpath("//a[@href='#rates' and contains(@class, 'btn')]");
    private By localizationBtn = By.xpath("//span[contains(@class, 'js-localization-popover')]");
    private By countriesDropDownBtn = By.xpath("//*[@id='countries-dropdown']");
    private By sellCurrencyByCountry = By.xpath("//*[@id='currency-exchange-app']/div/div/div[2]/div[1]/form/div[1]/div/div[1]/span/span[2]/span");


    //2. Constructor
    public CurrencyCalcPage(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoApplication() {
        driver.navigate().to(baseURL);
    }

    public void goToCurrencyCalc() {
        driver.findElement(seeRatesBtn).click();
    }

    public void inputSellFieldValue(String arg0) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(sellInputField))).click();

        driver.findElement(sellInputField).clear();
        driver.findElement(sellInputField).sendKeys(arg0);
    }

    public void inputBuyFieldValue(String arg0) {
        driver.findElement(buyInputField).click();
        driver.findElement(buyInputField).clear();
        driver.findElement(buyInputField).sendKeys(arg0);
    }

    public String getSellInputFieldValue() {
        return driver.findElement(sellInputField).getAttribute("value");
    }

    public String getBuyInputFieldValue() {
        return driver.findElement(buyInputField).getAttribute("value");
    }

    public void clickFlagIcon() {
        driver.findElement(localizationBtn).click();
    }

    public void clickToOpenCountryDropDown() {
        driver.findElement(countriesDropDownBtn).click();
    }

    public void selectCountryFromDropDown(String arg0) {
        WebElement countryName = driver.findElement(By.xpath("//*[contains(@id,'popover')]/div[2]/div/form/div[1]/div/div/ul/li[" + arg0 + "]/a"));
        countryName.click();
    }

    public String getDefaultCurrencyName() {
        return driver.findElement(sellCurrencyByCountry).getText();
    }

    public String getCurrencyName(int arg0) {
        WebElement currencyName = driver.findElement(By.xpath("//*[@id='currency-exchange-app']//table/tbody/tr[" + arg0 + "]/td[1]"));
        return currencyName.getText().trim();
    }

    public String getPsAmount(int arg0) throws ParseException {
        WebElement PsAmount = driver.findElement(By.xpath("//*[@id='currency-exchange-app']//table/tbody/tr[" + arg0 + "]/td[4]"));
        Number number = format.parse(PsAmount.getText().trim());
        return number.toString();
    }

    public String getSwedBankAmount(int arg0) throws ParseException {
        WebElement SwedBankAmount = driver.findElement(By.xpath("//*[@id='currency-exchange-app']//table/tbody/tr[" + arg0 + "]/td[5]/span/span/span[1]"));
        Number number = format.parse(SwedBankAmount.getText().trim());
        return number.toString();
    }

    public String getSwedBankLossAmount(int arg0) {
        WebElement lossAmn = driver.findElement(By.xpath("//*[@id='currency-exchange-app']//table/tbody/tr[" + arg0 + "]/td[5]/span/span/span[2]"));
        return lossAmn.getText().trim();
    }

    public boolean isLossAmnShowing(int arg0) {
        return driver.findElement(By.xpath("//*[@id='currency-exchange-app']//table/tbody/tr[" + arg0 + "]/td[5]/span/span/span[2]")).isDisplayed();

    }

}
