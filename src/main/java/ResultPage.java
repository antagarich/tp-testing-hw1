/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 08.10.13
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.Date;

public class ResultPage {
    private WebDriver driver;
    private SearchFormElements searchFormElements;
    public String city;
    public String[] citysplitter;
    public String date;
    public String[] datesplitter;

    public ResultPage(WebDriver driver){
        this.driver = driver;
        this.searchFormElements = new SearchFormElements(driver);
    }

    public ResultPage changeCity(String query){
        driver.findElement(By.className("sunrise__search-input")).sendKeys(query);
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCity(){
         city = driver.findElement(By.className("sunrise__title__city")).getText();
         citysplitter = city.split("\\: ");
         city = citysplitter[1];
         return city;
    }

    public ResultPage changeCityAndClickEnter(String query){
        this.changeCity(query).driver.findElement(By.className("sunrise__search-input")).sendKeys(Keys.ENTER);
        return this;
    }

    public String getSuggestsCity(){
        return this.driver.findElement(By.className("autocomplete_item")).getText();
    }

    public String getDate(){
        date = driver.findElement(By.className("sunrise__today-input")).getText();
        datesplitter = date.split("\\,");
        date = datesplitter[0];
        return date;
    }
}