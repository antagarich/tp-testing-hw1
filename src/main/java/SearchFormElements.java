/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 08.10.13
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchFormElements {
    private WebDriver driver;

    public SearchFormElements(WebDriver driver) {
        this.driver = driver;
    }

    public SearchFormElements clickSubmitButton(){
        this.driver.findElement(By.className("go-form__submit")).click();
        return this;
    }

    public void search(String query) {
        this.enterText(query);
        this.clickSubmitButton();
    }

    public SearchFormElements enterText(String query) {
        driver.findElement(By.id("q")).clear();
        driver.findElement(By.id("q")).sendKeys(query);
        return this;
    }

    public String getSuggests(){
        return this.driver.findElement(By.id("go-suggests")).getText();
    }
}
