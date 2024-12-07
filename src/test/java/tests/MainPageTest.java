package tests;

import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;




@RunWith(Parameterized.class)
public class MainPageTest {

    private WebDriver driver;
    private static MainPage mainPage;

    private static final String EXPECTED_TEXT_HOWMUCH = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String EXPECTED_TEXT_SEVERALSCOOTERS = "Пока что у нас так: один заказ — один самокат. " +
            "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String EXPECTED_TEXT_RENTTIME = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 " +
            "мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String EXPECTED_TEXT_RENTTODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String EXPECTED_TEXT_RENTEXTEND = "Пока что нет! Но если что-то срочное — всегда можно " +
            "позвонить в поддержку по красивому номеру 1010.";
    private static final String EXPECTED_TEXT_CHARGER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на " +
            "восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String EXPECTED_TEXT_CANCELORDER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String EXPECTED_TEXT_MKADORDER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private final String expectedText;
    private int index;

    public MainPageTest(int index, String expectedText) {

        this.index = index;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {0, EXPECTED_TEXT_HOWMUCH},
                {1, EXPECTED_TEXT_SEVERALSCOOTERS},
                {2, EXPECTED_TEXT_RENTTIME},
                {3, EXPECTED_TEXT_RENTTODAY},
                {4, EXPECTED_TEXT_RENTEXTEND},
                {5, EXPECTED_TEXT_CHARGER},
                {6, EXPECTED_TEXT_CANCELORDER},
                {7, EXPECTED_TEXT_MKADORDER}

        };
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru");
        mainPage = new pages.MainPage(driver);
    }

    @Test
    public void TextOnMainPageTest() {
        mainPage.scrollBeforeClick();

        // Клик по элементу меню по индексу
        mainPage.clickMenuButton(index);

        // Получаем текст из соответствующего элемента
        String actualText = mainPage.getTextMenu(index);

        // Проверка
        assertEquals(expectedText, actualText);
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
