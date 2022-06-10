package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class Homepage {

WebDriver driver = null;

    @FindBy(id ="search_query_top")
    public WebElement searchBar;

    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts'][1]")
    public WebElement fadedTShirt;


    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToProducts(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( "window.scrollBy(0,600)");
    }




}
