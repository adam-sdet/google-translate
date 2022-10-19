package google.translate.pages;

import google.translate.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TranslatePage {

    public TranslatePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "button[aria-label='More source languages']")
    public WebElement moreSourceLanguages;

    @FindBy(css = "input[aria-label='Search languages']")
    public WebElement searchLanguages;

    @FindBy(css = "button[aria-label='More target languages'] div")
    public WebElement moreTargetLanguages;

    @FindBy(css = "textarea[aria-label='Source text']")
    public WebElement sourceText;

    @FindBy(css = "div[aria-live='polite'] span")
    public WebElement translatedText;

    @FindBy(css = "button[aria-label='Swap languages (Cmd+Shift+S)']")
    public WebElement swapLanguages;

    public WebElement selectSourceLanguage(String sourceLanguage) throws InterruptedException {
        WebElement moreLanguage =Driver.getDriver().findElement(By.cssSelector("button[aria-label='More source languages']"));
        moreLanguage.click();
        WebElement searchLanguage =Driver.getDriver().findElement(By.cssSelector("input[aria-label='Search languages']"));
        searchLanguage.sendKeys(sourceLanguage);
        WebElement selectedLanguage =Driver.getDriver().findElement(By.xpath("//*[text()='" + sourceLanguage +"']"));
        return selectedLanguage;
    }

    public WebElement selectTargetLanguage(String sourceLanguage) throws InterruptedException {
        WebElement moreTargetLanguages =Driver.getDriver().findElement(By.cssSelector("button[aria-label='More target languages'] div"));
        moreTargetLanguages.click();
        WebElement searchLanguage =Driver.getDriver().findElement(By.cssSelector("input[aria-label='Search languages']"));
        searchLanguage.sendKeys(sourceLanguage);
        WebElement selectedLanguage =Driver.getDriver().findElement(By.xpath("//*[text()='" + sourceLanguage +"']"));
        return selectedLanguage;
    }

}
