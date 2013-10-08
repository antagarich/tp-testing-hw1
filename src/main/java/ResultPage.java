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

public class ResultPage {
    private WebDriver driver;
    private SearchFormElements searchFormElements;

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
        return this.driver.findElement(By.className("sunrise__title__city")).getText();
    }

    public ResultPage changeCityAndClickEnter(String query){
        this.changeCity(query).driver.findElement(By.className("sunrise__search-input")).sendKeys(Keys.ENTER);
        return this;
    }

    public String getSuggestsCity(){
        return this.driver.findElement(By.className("autocomplete_item")).getText();
    }

    public String getDate(){
        return this.driver.findElement(By.className("sunrise__today-input")).getText();
    }
}
