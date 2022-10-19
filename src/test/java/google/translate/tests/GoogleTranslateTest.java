package google.translate.tests;

import google.translate.pages.TranslatePage;
import google.translate.utilities.ConfigurationReader;
import google.translate.utilities.Driver;
import google.translate.utilities.ExcelReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleTranslateTest {

  TranslatePage translatePage = new TranslatePage();
    private WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    ExcelReader excelReader =new ExcelReader();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    String sourceFilePath = "src/test/resources/xlsx/SourceText.xlsx";
    String translatedFilePath = "src/test/resources/xlsx/TranslatedText.xlsx";
    String languagesFilePath = "src/test/resources/xlsx/Languages.xlsx";


    @BeforeAll
    public void init() {
        String sourceLanguageCode = excelReader.readCellData(1, 1, languagesFilePath);
        String translatedLanguageCode = excelReader.readCellData(2, 1, languagesFilePath);
        String sourceLanguage = "&sl=" + sourceLanguageCode;
        String targetLanguage = "&tl=" + translatedLanguageCode;
        driver.get(ConfigurationReader.getProperty("google-translate") + sourceLanguage + targetLanguage);
    }

    @Test
    public void TranslateTest() throws InterruptedException {

        String title = driver.getTitle();
        assertEquals("Google Translate", title);

        String sourceText = excelReader.readCellData(1, 1, sourceFilePath);
        translatePage.sourceText.sendKeys(sourceText);
        System.out.println("translated text is: " + translatePage.translatedText.getText());

        String translatedText = excelReader.readCellData(1, 1, translatedFilePath);
        assertEquals(translatedText, translatePage.translatedText.getText());

        translatePage.swapLanguages.click();

        wait.until(ExpectedConditions.textToBePresentInElement(translatePage.translatedText, sourceText));
        System.out.println("swapped translated text is: " + translatePage.translatedText.getText());
        assertEquals(sourceText, translatePage.translatedText.getText());



        // TODO  selectTargetLanguage method is not working properly
        /*
        WebElement sourceLanguage = translatePage.selectSourceLanguage("German");
        jse.executeScript("arguments[0].click();", sourceLanguage);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(translatePage.moreTargetLanguages));
        jse.executeScript("arguments[0].click();", translatePage.moreTargetLanguages);

        WebElement targetLanguage = translatePage.selectTargetLanguage("Spanish");
        jse.executeScript("arguments[0].click();", targetLanguage);
        */

    }

    @AfterAll
    public void teardown() {
        Driver.closeDriver();
    }



}
