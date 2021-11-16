package com.qa.util;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
    WebDriver driver;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToPixel(int arg0) {

        System.out.println("Total scroll pixel: " + arg0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, " + arg0 + ")");
    }

    public void scrollWindowHeight(int arg0) {
        Dimension initial_size = driver.manage().window().getSize();
        int height = initial_size.getHeight();

        int scrollHeight = height + arg0;
        System.out.println("Total scroll pixel: " + scrollHeight);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, " + scrollHeight + ")");
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void waitAndSendKeys(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(value);
    }

}
