package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private pages.OrderPage OrderPage;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String rentDuration;
    private final String rentPeriod;
    private static final String EXPECTED_TEXT_ORDERPLACED = "Заказ оформлен";

    public OrderPageTest(String firstName, String lastName, String address, String subwayStation, String phoneNumber, String rentDuration, String rentPeriod) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.rentDuration = rentDuration;
        this.rentPeriod = rentPeriod;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { "павел", "павлович", "Лескова 5", "Бибирево", "78005553535", "15", "сутки" },
                { "Иван", "Иванов", "Коровинское 15", "Аэропорт", "88005553535", "28", "пятеро суток" }

        };
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru");
        OrderPage = new pages.OrderPage(driver);
    }

    @Test
    public void testOrderFormButtonOnTop() {
        OrderPage.clickOrderButtonOnTop();
        OrderPage.fillAndSubmitOrderForm(firstName, lastName, address, subwayStation, phoneNumber, rentDuration, rentPeriod);
        String actualText = OrderPage.getTextOrderPlaced();
        assertEquals(EXPECTED_TEXT_ORDERPLACED, actualText);

    }

    @Test
    public void testOrderFormButtonBelow() {
        OrderPage.ScrollToHomeFinishButton();

        OrderPage.clickOrderButtonBellow();
        OrderPage.fillAndSubmitOrderForm(firstName, lastName, address, subwayStation, phoneNumber, rentDuration, rentPeriod);

        String actualText = OrderPage.getTextOrderPlaced();
        assertEquals(EXPECTED_TEXT_ORDERPLACED, actualText);

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
