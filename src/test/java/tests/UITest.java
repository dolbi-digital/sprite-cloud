package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SampleAppPage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Sprite Cloud Test Task")
@Feature("UI tests")
public class UITest {
    private WebDriver driver;

    @BeforeClass
    void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @BeforeTest
    void setUp() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    void goTo() {
        driver.get("http://www.uitestingplayground.com");
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Step
    @Test
    void checkChapters() {
        HomePage homePage = new HomePage(driver);
        assertTrue(driver.getTitle().contains("UI Test Automation"));
        assertEquals(homePage.getActualText(homePage.getNames()), homePage.getExpectedText("chapters.csv"));
        assertEquals(homePage.getActualText(homePage.getDescriptions()),
                homePage.getExpectedText("descriptions.csv"));
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"pwd1", "pwd1"}, {"pwd", ""}, {"", "pwd"}, {"pwd", "pwd1"}, {"", ""}};
    }

    @Step
    @Test(dataProvider = "data-provider")
    void checkFailLogin(String user, String pass) {
        HomePage homePage = new HomePage(driver);
        homePage.goToChapter("Sample App");
        SampleAppPage sampleAppPage = new SampleAppPage(driver);
        assertTrue(driver.getTitle().contains("Sample App"));
        assertEquals(sampleAppPage.checkAuthMessage(user, pass), "Invalid username/password");
    }

    @Step
    @Test
    void checkSuccessLogin() {
        String user = "user1";
        HomePage homePage = new HomePage(driver);
        homePage.goToChapter("Sample App");
        SampleAppPage sampleAppPage = new SampleAppPage(driver);
        assertTrue(driver.getTitle().contains("Sample App"));
        assertEquals(sampleAppPage.checkAuthMessage(user, "pwd"), "Welcome, "+ user +"!");
        assertEquals(sampleAppPage.checkLogoutMessage(), "User logged out.");
    }
}