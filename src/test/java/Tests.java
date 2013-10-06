import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.String;

public class Tests {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "hub", "url"})
    public void setUp(String browser, String hub, String url) throws MalformedURLException {
        if (browser.toLowerCase().equals("chrome"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.chrome());
        else if (browser.toLowerCase().equals("firefox"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.firefox());
        else
            throw new NotImplementedException();
        this.driver.manage().window().maximize();
        this.driver.get(url);
    }

    @Test
    public void SanityTest() {
        Search TestPage = new Search(driver);
        TestPage.search("ВКонтакте");
        Assert.assertTrue(TestPage.getTitle().contains("ВКонтакте"));
    }

    @Test
    public void exchangeRatesTest() throws UnsupportedEncodingException {
        Search TestPage = new Search(driver);
        TestPage.search("Восход солнца Хабаровск");
        Assert.assertEquals(TestPage.getCity(), ": Хабаровск");

        TestPage.search("Восход солнца Липецк");
        Assert.assertEquals(TestPage.getCity(), ": Липецк");
    }

    @Test
    public void suggestsTest() throws InterruptedException {
        Search TestPage = new Search (driver);
        TestPage.enterText("mail");
        Thread.sleep(1000);
        Assert.assertTrue(TestPage.getSuggests().contains("mail.ru"));
    }

    @Test
    public void suggestsTest_sec() throws InterruptedException {
        Search TestPage = new Search (driver);
        TestPage.enterText("восход");
        Thread.sleep(1000);
        Assert.assertTrue(TestPage.getSuggests().contains("восход солнца"));
    }

    @Test
    public void changeCityTest() throws InterruptedException {
        Search TestPage = new Search (driver);
        TestPage.search("Восход солнца Москва");
        Assert.assertEquals(TestPage.getCity(), ": Москва");
        TestPage.changeCityAndClickEnter("Тамбов");
        Assert.assertEquals(TestPage.getCity(), ": Тамбов");
    }

    @Test
    public void suggestsCityTest() throws InterruptedException{
        Search TestPage = new Search (driver);
        TestPage.search("Восход солнца");
        TestPage.changeCity("Хант");
        Thread.sleep(1000);
        Assert.assertTrue(TestPage.getSuggestsCity().contains("Ханты-Мансийск"));
    }

    @AfterMethod
    public void tearDown() {
        this.driver.close();
        this.driver.quit();
    }
}
