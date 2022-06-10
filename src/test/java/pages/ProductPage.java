package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage {


        WebDriver driver = null;

        @FindBy(xpath = "//h1[@itemprop='name']")
        public WebElement proTitle;



        public ProductPage(WebDriver driver) {
            this.driver = driver;
        }






    }

