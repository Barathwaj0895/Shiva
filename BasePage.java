package rudhra.framework.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private static final int TIMEOUT = 5; //seconds
    private static final int POLLING = 100; //milliseconds

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    public static WebDriver changeLocation() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }

    protected void dismissPopUp(String text) {
        wait.withMessage(text);
    }

    protected void goBack() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(3000);
    }

    protected void getURL() {
        driver.getCurrentUrl();
    }

    protected void checkOffers(String text) {
        driver.getPageSource().contains(text);
    }

    protected void getPageTitle(String text) {
        driver.getTitle().equals(text);
    }

    public static void MouseOver(WebElement we){
        WebDriver driver = new ChromeDriver();
        Actions actObj=new Actions(driver);
        actObj.moveToElement(we).build().perform();
    }

    public static String fn_GetTimeStamp(){
        DateFormat DF= DateFormat.getDateTimeInstance();
        Date dte=new Date();
        String DateValue=DF.format(dte);
        DateValue=DateValue.replaceAll(":", "_");
        DateValue=DateValue.replaceAll(",", "");
        return DateValue;
    }

    public static String fn_TakeSnapshot(WebDriver driver, String DestFilePath) throws IOException {
        String TS=fn_GetTimeStamp();
        TakesScreenshot tss=(TakesScreenshot) driver;
        File srcfileObj= tss.getScreenshotAs(OutputType.FILE);
        DestFilePath=DestFilePath+TS+".png";
        File DestFileObj=new File(DestFilePath);
        FileUtils.copyFile(srcfileObj, DestFileObj);
        return DestFilePath;
    }

    //select the dropdown using "select by visible text", so pass VisibleText as 'Yellow' to funtion
    public static void fn_SelectVisibleText(WebElement WE, String VisibleText){
        Select selObj=new Select(WE);
        selObj.selectByVisibleText(VisibleText);
    }

    //select the dropdown using "select by index", so pass IndexValue as '2'
    public static void fn_SelectIndex(WebElement WE, int IndexValue){
        Select selObj=new Select(WE);
        selObj.selectByIndex(IndexValue);
    }

    //select the dropdown using "select by value", so pass Value as 'thirdcolor'
    public static void fn_Select(WebElement WE, String Value){
        Select selObj=new Select(WE);
        selObj.selectByValue(Value);
    }

    public boolean objClick(WebElement element) throws UiObjectNotFoundException {
        if(element != null){
            element.click();
        }
        return element.isDisplayed();
    }
}