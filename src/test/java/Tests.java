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
        MainPage TestPage = new MainPage(driver);
        ResultPage resultTestPage = TestPage.search("ВКонтакте");
        Assert.assertTrue(resultTestPage.getTitle().contains("ВКонтакте"));
    }

    @Test
    public void citySunriseTest() throws UnsupportedEncodingException {
        MainPage TestPage = new MainPage(driver);
        ResultPage resultTestPage = TestPage.search("Восход солнца Хабаровск");
        Assert.assertEquals(resultTestPage.getCity(), ": Хабаровск");

        resultTestPage = TestPage.search("Восход солнца Липецк");
        Assert.assertEquals(resultTestPage.getCity(), ": Липецк");
    }

    @Test
    public void suggestsTest() throws InterruptedException {
        MainPage TestPage = new MainPage (driver);
        TestPage.enterText("mail");
        Thread.sleep(1000);
        Assert.assertTrue(TestPage.getSuggests().contains("mail.ru"));
    }

    @Test
    public void changeCityTest() throws InterruptedException {
        MainPage TestPage = new MainPage (driver);
        ResultPage resultTestPage = TestPage.search("Восход солнца Москва");
        Assert.assertEquals(resultTestPage.getCity(), ": Москва");
        resultTestPage.changeCityAndClickEnter("Тамбов");
        Assert.assertEquals(resultTestPage.getCity(), ": Тамбов");
    }

    @Test
    public void suggestsCityTest() throws InterruptedException{
        MainPage TestPage = new MainPage (driver);
        ResultPage resultTestPage = TestPage.search("Восход солнца");
        resultTestPage.changeCity("Хант");
        Thread.sleep(1000);
        Assert.assertTrue(resultTestPage.getSuggestsCity().contains("Ханты-Мансийск"));
    }

    @Test
    public void dateSunriseTest() throws InterruptedException {
        MainPage TestPage = new MainPage(driver);
        ResultPage resultTestPage = TestPage.search("Восход солнца 2 мая 2012");
        Assert.assertEquals(resultTestPage.getDate(), "2 мая 2012, среда");
    }

    @Test
    public void datedefinitionSunriseTest() throws InterruptedException {
        MainPage TestPage = new MainPage(driver);
        ResultPage resultTestPage = TestPage.search("Восход солнца воскресенье");
        Assert.assertEquals(resultTestPage.getDate(), "13 октября 2013, воскресенье");
    }

    @Test
    public void complexWorkTest() throws InterruptedException{
        MainPage TestPage = new MainPage(driver);
        ResultPage resultTestPage = TestPage.search("восход солнца в москве 25 августа 2012");
        Assert.assertEquals(resultTestPage.getCity(), ": Москва");
        Assert.assertEquals(resultTestPage.getDate(), "25 августа 2012, суббота");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.close();
        this.driver.quit();
    }
}
