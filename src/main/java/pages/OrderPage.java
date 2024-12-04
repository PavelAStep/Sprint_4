package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;

public class OrderPage {
    private By orderButtonOnTop = By.className("Button_Button__ra12g");
    private By orderButtonBellow = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[contains(text(), 'Заказать')]");
    private WebDriver driver;
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By station = By.xpath("//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By buttonNext = By.xpath("//button[text()='Далее']");
    private By dateFeld = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentTime = By.className("Dropdown-control");
    private By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private By yesButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[2]");
    private By orderPlaced = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ')");
    private By homeFinishButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[contains(text(), 'Заказать')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ScrollToHomeFinishButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement buttonOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(homeFinishButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonOrder);
    }


    public void clickOrderButtonOnTop() {

        driver.findElement(orderButtonOnTop).click();
    }

    public void clickOrderButtonBellow() {

        driver.findElement(orderButtonBellow).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setAdress(String adress) {
        driver.findElement(addressField).sendKeys(adress);
    }

    public void selectStation(String visibleText) {
        driver.findElement(station).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select-search__option")));

        List<WebElement> options = driver.findElements(By.className("select-search__option"));

        for (WebElement option : options) {
            String optionText = option.findElement(By.className("Order_Text__2broi")).getText();
            if (optionText.equals(visibleText)) {
                option.click();
                break;
            }
        }
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));
    }

    public void setDate(String date) {

        driver.findElement(dateFeld).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + date + "']")));

        dayElement.click();
    }

    public void selectTime(String time) {

        driver.findElement(rentTime).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement timeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + time + "']")));

        timeElement.click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickYesButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Да']")));

        driver.findElement(yesButton).click();
    }

    public String getTextOrderPlaced() {
        return driver.findElement(orderPlaced).getText();
    }

    public void waitOrderPlaced(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPlaced));
    }

    //метод для заполнения формы
    public void fillAndSubmitOrderForm(String name, String lastName, String address,
                                       String stationName, String phone, String date, String time) {
        setName(name);
        setLastName(lastName);
        setAdress(address);
        selectStation(stationName);
        setPhone(phone);
        clickButtonNext();
        setDate(date);
        selectTime(time);
        clickOrderButton();
        clickYesButton();
        waitOrderPlaced();
    }

}

