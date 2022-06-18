package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DBUtility;
import utilities.Driver;

import java.time.Duration;

public class Hooks {
    @Before
    public void setUpTests(){
        Driver.getDriver().manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
        Driver.getDriver().manage().window().maximize();
      //  Driver.getDriver().manage().deleteAllCookies();

    }


    @Before("@db")
    public void setUpDB(){
        DBUtility.createConnection();


    }

    @After ("@db")
    public void tearDownDB(){
        DBUtility.close();
    }



    @Before("@datable")
    public void setUpDBTests(){
        DBUtility.createConnection();


    }

    @After ("@datable")
    public void tearDownDBTests(){
        DBUtility.close();
    }





//    @After
//    public void tearDownSession(){
//        driver.quit();
//    }
}
