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

public class SignUpStepDefs {

    //This class is a example of data integrity testing

    String userN;
    String firstN;
    String lastN;
    String email;
    String password;

    String expectedFirstName;
    String expectedLastName;
    String expectedEmail;
    String expectedPassword;






    @Given("I am on the duotify homepage")
    public void i_am_on_the_duotify_homepage() {
        Driver.getDriver().get( "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php" );
    }

    @When("I sign up using valid credentials")
    public void i_sign_up_using_valid_credentials() {
       Faker faker = new Faker();
       SignUpPage signUpPage = new SignUpPage();
                userN = faker.name().username();
                firstN = faker.name().firstName();
                 lastN = faker.name().lastName();

        //These two variables are used for later to be accessible by the user for account:
                  email = faker.internet().emailAddress();
                 password = faker.internet().password();



        SeleniumUtils.waitFor( 2 );
        signUpPage.signUpLink.click();


        signUpPage.username.sendKeys( userN );

        expectedFirstName = faker.name().firstName();
        signUpPage.firstname.sendKeys( expectedFirstName );




          expectedLastName = faker.name().lastName();
          signUpPage.lastname.sendKeys( expectedLastName);


           expectedEmail = faker.internet().emailAddress();
          signUpPage.email.sendKeys( expectedEmail );

          signUpPage.email2.sendKeys( expectedEmail);

          expectedPassword = faker.internet().password();
           signUpPage.password.sendKeys( expectedPassword );
           signUpPage.password2.sendKeys( expectedPassword );

        SeleniumUtils.waitFor( 2 );
        signUpPage.registerButton.click();



    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Using datables instead of faker class, we use this type when we are given data **/
    @When("I sign up using the following credentials")
    public void i_sign_up_using_the_following_credentials(List<Map<String,String>> dataTable) {

        Map<String,String> map = dataTable.get( 0 );

        SignUpPage signUpPage = new SignUpPage();
        userN = map.get( "username" );
        firstN = map.get( "first" );
        lastN = map.get( "last" );
        email = map.get( "email" );
        password = map.get( "password" );
        SeleniumUtils.waitFor( 2 );


        signUpPage.signUpLink.click();

        signUpPage.username.sendKeys( userN );

        expectedFirstName = firstN;
        signUpPage.firstname.sendKeys( expectedFirstName );
        expectedLastName = lastN;
        signUpPage.lastname.sendKeys( expectedLastName);
        expectedEmail = email;
        signUpPage.email.sendKeys( expectedEmail );
        signUpPage.email2.sendKeys( expectedEmail);
        expectedPassword = password;
        signUpPage.password.sendKeys( expectedPassword );
        signUpPage.password2.sendKeys( expectedPassword );

        SeleniumUtils.waitFor( 2 );
        signUpPage.registerButton.click();

        System.out.println("User has been created");




    }

    @Then("I should be able to land on the homepage")
    public void i_should_be_able_to_land_on_the_homepage() {
        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?",Driver.getDriver().getCurrentUrl());
    }


    @Then("I should be able to verify that the user details are in the database")
    public void i_should_be_able_to_verify_that_the_user_details_are_in_the_database() throws SQLException {


        String query = "SELECT * from users where username= '"+ userN +"'";
        System.out.println(query);


        List<Map<String,Object>> listOfMaps = DBUtility.getQueryResultListOfMaps( query );

        //Then save map into another object to be operated on for validation and verification
//        System.out.println(listOfMaps);

        System.out.println(listOfMaps.get( 0 ));


       Map<String,Object> map = listOfMaps.get( 0 );




        //Hard assertions will fail:


       Assert.assertEquals( userN,listOfMaps.get( 0 ).get( "username" ) );
       SeleniumUtils.waitFor( 1 );
       Assert.assertEquals( firstN,listOfMaps.get(0).get( "first" ) );
       SeleniumUtils.waitFor( 1 );
       Assert.assertEquals( lastN,listOfMaps.get(0).get( "last" ) );
        SeleniumUtils.waitFor( 1 );
        Assert.assertEquals( email,listOfMaps.get(0).get( "email" ) );
        SeleniumUtils.waitFor( 1 );
//
//       // DigestUtils.md5Hex(  );//This converts string into a md5Hex string text, comes from apache commons
//
       Assert.assertEquals(DigestUtils.md5Hex(password  ) ,listOfMaps.get(0).get( "password" ) );


       /** This helps saves the database space and keeps code running efficiently, after each test suite run or regression suites
        * for over 100 scenarios **/

       /** to use more data we can use Mockaroo.com for fake data and we'll use a scenario outline and place the whole datatable in the
        *
        * Scenario outline from a csv or data-delimiter from mockaroo into the scenario outline.
        *
        * See the sign-up.feature file for more.**/


       String updateQuery = "delete from users where username = '"+userN+"';";
       DBUtility.updateQuery(updateQuery ); // cleans database of test user we just created

        System.out.println("User has been deleted.");



    }

// //--------------------------------------------------------------------------------------------------------------------------------------------------
//
//    String expectedUserN;
//    String expectedFirstN;
//    String expectedLastN;
//    String expectedEmail1;
//    String expectedPassword1;
//    String hexPass;
//
//
//    /** To create a new user we need to send the query to the database **/
//    @Given("I create a new user in the Database with the following details")
//    public void i_create_a_new_user_in_the_database_with_the_following_details(List<Map<String,String>> dataTable) throws SQLException {
//
//        Driver.getDriver().get( "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php" );
//
//        Map<String,String> map = dataTable.get(0);
//
//        expectedUserN = map.get( "username" );
//        expectedFirstN = map.get( "first" );
//        expectedLastN = map.get( "last" );
//        expectedEmail1 = map.get( "email" );
//        expectedPassword1 = map.get( "password" );
//
//        hexPass =  DigestUtils.md5Hex( expectedPassword1 );//Creates a password using md5hash
//
//        String queryCreate = "insert into users(username, first,Last,email,password)\n"+
//                        "values('"+expectedUserN+"', '"+expectedFirstN+"', '"+expectedLastN+"', '"+expectedEmail1+"', '"+DigestUtils.md5Hex( expectedPassword1 )+"')"; //dynamic query, allows us to pass parameters or data
//
//        DBUtility.updateQuery( queryCreate );
//
//
//
//    }
//    @When("I login with the same credentials on the UI")
//    public void i_login_with_the_same_credentials_on_the_ui() throws InterruptedException {
//
//        SignUpPage signUpPage1 = new SignUpPage();
//
//       signUpPage1.loginUsername.sendKeys( expectedUserN );
//
//        Thread.sleep( 1000 );
//
//        signUpPage1.loginPassword.sendKeys( expectedPassword1 );
//
//        Thread.sleep( 1000 );
//
//        signUpPage1.loginButton.click();
//
//
//    }
//
//    /** Actual validation method to allow us to test the data we were given **/
//    @Then("firstname, lastname and email should be correct")
//    public void firstname_lastname_and_email_should_be_correct() throws SQLException {
//
//        HomePageDuo homepage = new HomePageDuo();
//
//        String [] arr = homepage.nameFirstAndLast.getText().split( " " ); // this will verify the text
//
//        homepage.UserDetails.click();
//
//       String actualEmail  = homepage.emailInput.getAttribute("value");
//
//
//        Assert.assertEquals( expectedFirstN,arr[0] ); //the customer info was stored as a array and we are comparing with array elements
//        Assert.assertEquals( expectedLastN,arr[1] );
//        Assert.assertEquals( expectedEmail1,actualEmail );
//
//        String updateQuery = "delete from users where username = '"+expectedUserN+"';";
//        DBUtility.updateQuery(updateQuery ); // cleans database of test user we just created
//
//        System.out.println("User has been deleted.");
//
//
//
//    }

}
