/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 08.10.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private SearchFormElements searchFormElements;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.searchFormElements = new SearchFormElements(driver);
    }

    public ResultPage search(String query){
        searchFormElements.search(query);
        return new ResultPage(driver);
    }

    public void enterText(String query){
        searchFormElements.enterText(query);
    }

    public String getSuggests(){
        return searchFormElements.getSuggests();
    }

}
