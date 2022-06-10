package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.Homepage;
import pages.ProductPage;

import java.time.Duration;

public class VerifyProductDetailsSteps {

//Hooks class logic can be directly placed in the same class
    //But for easy maintenance we place it in the Hooks class

//    @Before
//    public void setUpTests(){
//        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 5 ) );
//        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//
//    }
//
//
//    @After
//    public void tearDownSession(){
//        driver.quit();
//    }

    WebDriver driver;
    Homepage homepage;
    ProductPage productPage;
    String productName = "Blouse";

    /** Example of a parameterized test in cucumber**/


    @When("I search for a {string}")
    public void i_search_for_a(String string) {
        System.out.println(string);
    }
    @When("I land on the product details page with title containing {string}")
    public void i_land_on_the_product_details_page_with_title_containing(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("I am on the home page2")
    public void i_am_on_the_home_page2() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get( "http://automationpractice.com/index.php" );
        homepage = PageFactory.initElements( driver ,Homepage.class );
        productPage = PageFactory.initElements( driver ,ProductPage.class );

    }

    @When("I land on a product details page with title containing Faded Short Sleeve T-shirts")
    public void i_click_on_a_product_faded_short_sleeve_t_shirts() throws InterruptedException {
        homepage.scrollToProducts();
        Thread.sleep( 1000 );
        homepage.fadedTShirt.click();

    }

    @When("I land on a product details page")
    public void i_land_on_a_product_details_page() {

        System.out.println( productPage.proTitle.isDisplayed() );
    }

    @Then("The title of the product should be the same")
    public void the_title_of_the_product_should_be_the_same() {
        Assert.assertTrue( productPage.proTitle.getText().contains( productName ) );
        System.out.println( "Test complete!" );

    }


    public static class verifyPrintedDress {
        @When("I search for a Printed Dress")
        public void i_search_for_a_printed_dress() {
            // Write code here that turns the phrase above into concrete actions

        }

        @Then("I should see the Printed Dress when I land on the results page")
        public void i_should_see_the_printed_dress_when_i_land_on_the_results_page() {
            // Write code here that turns the phrase above into concrete actions

        }


    }


    public static class verifyPrintedSummerDress {
        @When("I search for a Printed Summer Dress")
        public void i_search_for_a_printed_summer_dress() {
            // Write code here that turns the phrase above into concrete actions

        }

        @Then("I should see the Printed Summer Dress when I land on the results page")
        public void i_should_see_the_printed_summer_dress_when_i_land_on_the_results_page() {
            // Write code here that turns the phrase above into concrete actions

        }

    }
}
