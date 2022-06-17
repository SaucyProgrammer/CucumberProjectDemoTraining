package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import utilities.DBUtility;

import java.time.Duration;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUpTests(){
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }


    @Before("@db")
    public void setUpDB(){
        DBUtility.createConnection();


    }

    @After ("@db")
    public void tearDownDB(Scenario scenario){
        DBUtility.close();
    }


    @After
    public void tearDownSession(){
        driver.quit();
    }
}
