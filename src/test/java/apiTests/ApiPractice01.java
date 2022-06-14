package apiTests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Base64;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiPractice01 {

    WebDriver driver;

    String email = "zakd1198@gmail.com";
    String password = "password";

    static {
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

    }

    @BeforeMethod
    public void setUpTests(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        driver.manage().window().maximize();

    }


    //Successfully created a account. Results success.
    @Test(enabled = false)
    public void testingFlow_API_to_UI(){


       String email = "zakd1198@gmail.com";
       String password = "password";

        given().
                body( " {\n" +
                        "   \"first_name\": \"Zaki\",\n" +
                        "    \"last_name\": \"Darw\",\n" +
                        "    \"email\": \""+email+"\",\n" +
                        "    \"password\": \""+password+"\"\n" +
                        "\n"+
                        "}").
                when().log().all().
                post("/register.php").
                then().log().all().
                assertThat().
                statusCode( 200 ).
                body( "status",equalTo( 201 ) ).
                body( "message",equalTo( "You have successfully registered.") );


        // Verify the user creation through logging in with same creditable

        driver.get( "http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php" );

        driver.findElement( By.id( "exampleInputEmail1" ) ).sendKeys( email,Keys.TAB,password,Keys.ENTER );

        Assert.assertTrue( driver.getTitle().contains( "Loan Application" ) );

        driver.quit();

    }

    @Test //successfully logged in
    public void verifyAccount(){
        // Verify the user creation through logging in with same creditable

        driver.get( "http://qa-duobank.us-east-2.elasticbeanstalk.com/index.php" );

        driver.findElement( By.id( "exampleInputEmail1" ) ).sendKeys( email,Keys.TAB,password,Keys.ENTER );

        Assert.assertTrue( driver.getTitle().contains( "Loan Application" ) );

        driver.quit();
    }

    @Test
    public void basicAuthOfAccount(){





        String basicAuth = Base64.getEncoder().encodeToString( "postman:password".getBytes() );

        given().
                header( "Authorization","Basic "+basicAuth ).
        when().log().all().
                  get("https://postman-echo.com/basic-auth").

        then().log().all().
                  statusCode( 200 ).
                   body( "authenticated",equalTo( true ) );



    }
}
