package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.Homepage;

import java.time.Duration;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUpTests(){
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }


    @After
    public void tearDownSession(){
        driver.quit();
    }
}
