package pages.duotifyPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePageDuo {



    public HomePageDuo() {
        PageFactory.initElements( Driver.getDriver(),this );
    }

    @FindBy(id="nameFirstAndLast")
    public WebElement nameFirstAndLast;

    @FindBy(xpath = "//button[.='USER DETAILS']")
    public WebElement UserDetails;

    @FindBy(name = "email")
    public WebElement emailInput;
}
