package stepDefinitions.duobank;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DuoBankSteps {


    WebDriver driver;
    String email ;
    String password;
    RequestSpecification requestSpecification;
    Response response;


    static String uri = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api/register.php";
    static String endpoint = "/register.php";
    static Integer int1 = 200;
    static String message = "You have successfully registered";


    @Given("The base URI is set to uri")
    public void the_base_uri_is_set_to() {
        baseURI = uri;

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        driver.manage().window().maximize();
    }

    @And("The valid body is added to the request")
    public void the_valid_body_is_added_to_the_request() {
         email = new Faker().internet().emailAddress();
         password = new Faker().internet().password();


             requestSpecification = given().
                            body( " {\n" +
                                    "   \"first_name\": \"Zake\",\n" +
                                    "    \"last_name\": \"Darwesc\",\n" +
                                    "    \"email\": \"" + email + "\",\n" +
                                    "    \"password\": \"" + password + "\"\n" +
                                    "\n" +
                                    "}" );


    }
    @When("I send a POST request to endpoint")
    public void i_send_a_post_request_to_endpoint() {

     response = requestSpecification.when().log().all().
                                     post(endpoint);

    }
    @Then("the status code should be int and response body or payload should contain message.")
    public void the_status_code_should_be_and_response_body_or_payload_should_contain_message() {
        response.then().log().all().
                assertThat().
                statusCode( int1 ).
                body( "message",equalTo( message) );
    }
    @And("I navigate to homepage")
    public void i_navigate_to_homepage() {
        driver.get( "http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php" );
    }
    @When("I enter the same credentials sent by API request")
    public void i_enter_the_same_credentials_sent_by_api_request() {
        driver.findElement( By.id( "exampleInputEmail1" ) ).sendKeys( email,Keys.TAB,password,Keys.ENTER );
    }
    @Then("I should be able to login")
    public void i_should_be_able_to_login() {
        Assert.assertTrue( driver.getTitle().contains( "Loan Application" ) );

        driver.quit();
    }

}
