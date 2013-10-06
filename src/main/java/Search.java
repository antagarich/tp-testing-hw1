import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Search {
    private WebDriver driver;

    public Search(WebDriver driver) {
        this.driver = driver;
    }

    public Search enterText(String query) {
	driver.findElement(By.id("q")).clear();
        driver.findElement(By.id("q")).sendKeys(query);
        return this;
    }

    public Search clickSubmitButton(){
        this.driver.findElement(By.className("go-form__submit")).click();
	return this;
    }

    public Search search(String query) {
        this.enterText(query);
        this.clickSubmitButton();
        return new Search (driver);
    }

    public String getCity(){
        return this.driver.findElement(By.className("sunrise__title__city")).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getSuggests(){
        return this.driver.findElement(By.id("go-suggests")).getText();
    }

    public Search changeCity(String query){
        driver.findElement(By.className("sunrise__search-input")).sendKeys(query);
        return this;
    }

    public Search changeCityAndClickEnter(String query){
        this.changeCity(query).driver.findElement(By.className("sunrise__search-input")).sendKeys(Keys.ENTER);
        return this;
    }

    public String getSuggestsCity(){
        return this.driver.findElement(By.className("autocomplete_item")).getText();
    }
}
