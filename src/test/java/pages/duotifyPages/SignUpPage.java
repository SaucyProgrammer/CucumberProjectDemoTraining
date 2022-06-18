package pages.duotifyPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignUpPage {
    public SignUpPage() {
        PageFactory.initElements( Driver.getDriver(),this );
    }

    @FindBy(id="hideLogin")
    public WebElement signUpLink;

    @FindBy(id="username")
    public WebElement username;

    @FindBy(id="firstname")
    public WebElement firstname;

    @FindBy(id="lastname")
    public WebElement lastname;

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="email2")
    public WebElement email2;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(id="password2")
    public WebElement password2;

    @FindBy(name="registerButton")
    public WebElement registerButton;

    //Added these to for flow of db to ui:


    @FindBy(id="loginUsername")
    public WebElement loginUsername;

    @FindBy(id="loginPassword")
    public WebElement loginPassword;

    @FindBy(name="loginButton")
    public WebElement loginButton;




}