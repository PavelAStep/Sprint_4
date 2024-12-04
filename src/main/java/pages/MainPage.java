package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage {
    private WebDriver driver;
    private By menuButtonLocator(int index) {
        return By.xpath("//div[@id='accordion__heading-" + index + "']");
    }

    private By textLocator(int index) {
        return By.xpath("//div[@id='accordion__panel-" + index + "']/p");
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickMenuButton(int index) {
        driver.findElement(menuButtonLocator(index)).click();
    }

    public String getTextMenu(int index) {
        return driver.findElement(textLocator(index)).getText();
    }

    public void scrollBeforeClick() {
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }



}
