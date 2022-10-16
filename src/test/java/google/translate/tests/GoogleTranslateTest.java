package google.translate.tests;

import google.translate.pages.TranslatePage;
import google.translate.utilities.ConfigurationReader;
import google.translate.utilities.Driver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTranslateTest {

  TranslatePage translatePage = new TranslatePage();
    private WebDriver driver = Driver.getDriver();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    String sourceLanguage = "&sl=" + "az";
    String targetLanguage = "&tl=" + "en";

//    private WebDriver driver = Driver.getHeadlessChrome();

    @Test
    public void TranslateTest() throws InterruptedException {
        driver.get(ConfigurationReader.getProperty("google-translate") + sourceLanguage + targetLanguage);

        String title = driver.getTitle();
        assertEquals("Google Translate", title);

        translatePage.sourceText.sendKeys("Salam");

//        WebElement sourceLanguage = translatePage.selectSourceLanguage("Azerbaijani");
//        jse.executeScript("arguments[0].click();", sourceLanguage);
//
//        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(translatePage.moreTargetLanguages));
//        jse.executeScript("arguments[0].click();", translatePage.moreTargetLanguages);
//        Thread.sleep(5000);
//
//        WebElement targetLanguage = translatePage.selectTargetLanguage("Turkish");
//        jse.executeScript("arguments[0].click();", targetLanguage);


//         Driver.closeDriver();

    }



}
