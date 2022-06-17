package stepDefinitions.duotifyStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.duotifyPages.SignUpPage;
import utilities.Driver;

public class SignUpStepDefs {

    @Given("I am on the duotify homepage")
    public void i_am_on_the_duotify_homepage() {
        Driver.getDriver().get( "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php" );
    }
    @When("I sign up using valid credentials")
    public void i_sign_up_using_valid_credentials() {
       Faker faker = new Faker();
       SignUpPage signUpPage = new SignUpPage();

        signUpPage.signUpLink.click();
        signUpPage.username.sendKeys( faker.name().username() );
        signUpPage.firstname.sendKeys( faker.name().firstName() );
        signUpPage.lastname.sendKeys( faker.name().lastName() );

        //These two variables are used for later to be accessible by the user for account:
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        signUpPage.email.sendKeys( email );
        signUpPage.email2.sendKeys( email);
        signUpPage.password.sendKeys( password );
        signUpPage.password2.sendKeys( password );


        signUpPage.registerButton.click();

        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php",Driver.getDriver().getCurrentUrl());

    }
    @Then("I should be able to land on the homepage")
    public void i_should_be_able_to_land_on_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should be able to verify that the user details are in the database")
    public void i_should_be_able_to_verify_that_the_user_details_are_in_the_database() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
