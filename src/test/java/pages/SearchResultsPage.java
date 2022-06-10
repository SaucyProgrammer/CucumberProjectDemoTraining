package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchResultsPage {//According to POM page object model, each seperate webpage must have its own WebPage, this is the results page

    WebDriver driver = null;

    public SearchResultsPage(WebDriver driver) {
       this.driver = driver;

    }

    @FindBy(xpath ="//a[@class='product-name'][@title='Blouse']" )
    public WebElement blouseProduct;
}
