package org.example;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class DemoNopDigital
{
    static WebDriver driver;

    public static void TypeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public static void clickElement(By by) {
        driver.findElement(by).click();
    }

    public static long timestamp() {
        return (System.currentTimeMillis());
    }

    public static String getTextfromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static void selectFromDropDownByValue(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectFromDropDownByindex(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    public static void selectFromDropDownByVisibleText(By by, String n) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(n);
    }

    @BeforeMethod
    public static void Initialisation() {
        System.setProperty("webdriver.chrome.driver", "C:\\SOFT\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }
    @AfterMethod
    public static void closeBrowser() {
        driver.close();
    }



    @Test( priority= 1)
    public static void Registration() {

        clickElement(By.linkText("Register"));
        TypeText(By.xpath("//input[@id=\"FirstName\"]"), "Shreyarthi");
        TypeText(By.xpath("//input[@id=\"LastName\"]"), "Patel");
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "3");
        selectFromDropDownByindex(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "January");
        selectFromDropDownByindex(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "1912");
        //selectFromDropDownByVisibleText(By.xpath("//select[@name=\"//DateOfBirthYear\"]"), "1912");
        TypeText(By.name("Email"), "shreyarthi+" + timestamp() + "@gmail.com");
        TypeText(By.id("Company"), "ABC LTD");
        clickElement(By.xpath("//input[@id=\"Newsletter\"]"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        TypeText(By.xpath("//input[@name=\"Password\"]"), "My@password");
        TypeText(By.xpath("//input[@name=\"ConfirmPassword\"]"), "My@password");
        clickElement(By.name("register-button"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //String expectedText = "Your registration completed";
        // String actulText = getTextfromElement(By.linkText("Your registration completed"));
        //Assert.assertEquals(actulText,expectedText);
        String expectedText = "Your registration completed";
        String actualText = getTextfromElement(By.xpath("//div[@class=\"result\"]"));
        //String actualText = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        Assert.assertEquals(actualText, expectedText);
        if (actualText.contentEquals(expectedText)) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
    }

    @Test(priority = 2)
    public static void Computer() {
        Registration();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        clickElement(By.linkText("Computers"));
        clickElement(By.linkText("Desktops"));
        clickElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //clickElement(By.xpath("input[@value=\"Email a friend\"]"));
        clickElement(By.xpath("//input[@value=\"Email a friend\"]"));
        TypeText(By.xpath("//input[@id=\"FriendEmail\"]"), "abcdef@gmail.net");
        //TypeText(By.xpath("//input[@id=\"YourEmailAddress\"]"),"nitucp999+"+timestamp()+"@gmail.com");
        TypeText(By.xpath("//textarea[@id=\"PersonalMessage\"]"), "You are being invited ");
        clickElement(By.xpath("//input[@name=\"send-email\"]"));
        String expectedText1 = "Your message has been sent.";
        String actualText1 = getTextfromElement(By.xpath("//div[@class=\"result\"]"));
        //String actualText = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        Assert.assertEquals(actualText1, expectedText1);
    }

    @Test(priority = 3)
    public static void Books(){
        //click on Book
        clickElement(By.linkText("Books"));
        //click on Book Categories 1
        clickElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        clickElement(By.xpath("//input[@id=\"add-to-cart-button-37\"]"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //click on Book Categories 2
        clickElement(By.linkText("First Prize Pies"));
        clickElement(By.xpath("//input[@id=\"add-to-cart-button-38\"]"));
        clickElement(By.xpath("//span[@class=\"cart-label\"]"));
        String expectedText = "Fahrenheit 451 by Ray Bradbury";
        String actualText = getTextfromElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        Assert.assertEquals(actualText,expectedText);
        String expectedText1 = "First Prize Pies";
        String actualText1 = getTextfromElement(By.linkText("First Prize Pies"));
        Assert.assertEquals(actualText,expectedText);

    }
}
