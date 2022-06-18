package stepDefinitions.duotifyStepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Assert;
import pages.duotifyPages.HomePageDuo;
import pages.duotifyPages.SignUpPage;
import utilities.DBUtility;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SignUpStepDefs2 {




    String expectedUserN;
    String expectedFirstN;
    String expectedLastN;
    String expectedEmail1;
    String expectedPassword1;
    String hexPass;


    /** To create a new user we need to send the query to the database **/
    @Given("I create a new user in the Database with the following details")
    public void i_create_a_new_user_in_the_database_with_the_following_details(List<Map<String,String>> dataTable) throws SQLException {

        Driver.getDriver().get( "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php" );

        Map<String,String> map = dataTable.get(0);

        expectedUserN = map.get( "username" );
        expectedFirstN = map.get( "first" );
        expectedLastN = map.get( "last" );
        expectedEmail1 = map.get( "email" );
        expectedPassword1 = map.get( "password" );

        hexPass =  DigestUtils.md5Hex( expectedPassword1 );//Creates a password using md5hash

        String queryCreate = "insert into users(username, first,Last,email,password)\n"+
                        "values('"+expectedUserN+"', '"+expectedFirstN+"', '"+expectedLastN+"', '"+expectedEmail1+"', '"+DigestUtils.md5Hex( expectedPassword1 )+"')"; //dynamic query, allows us to pass parameters or data

        DBUtility.updateQuery( queryCreate );



    }
    @When("I login with the same credentials on the UI")
    public void i_login_with_the_same_credentials_on_the_ui() throws InterruptedException {

        SignUpPage signUpPage1 = new SignUpPage();

       signUpPage1.loginUsername.sendKeys( expectedUserN );

        Thread.sleep( 1000 );

        signUpPage1.loginPassword.sendKeys( expectedPassword1 );

        Thread.sleep( 1000 );

        signUpPage1.loginButton.click();


    }

    /** Actual validation method to allow us to test the data we were given **/
    @Then("firstname, lastname and email should be correct")
    public void firstname_lastname_and_email_should_be_correct() throws SQLException {

        HomePageDuo homepage = new HomePageDuo();

        String [] arr = homepage.nameFirstAndLast.getText().split( " " ); // this will verify the text

        homepage.UserDetails.click();

       String actualEmail  = homepage.emailInput.getAttribute("value");


        Assert.assertEquals( expectedFirstN,arr[0] ); //the customer info was stored as a array and we are comparing with array elements
        Assert.assertEquals( expectedLastN,arr[1] );
        Assert.assertEquals( expectedEmail1,actualEmail );

        String updateQuery = "delete from users where username = '"+expectedUserN+"';";
        DBUtility.updateQuery(updateQuery ); // cleans database of test user we just created

        System.out.println("User has been deleted.");



    }

}
