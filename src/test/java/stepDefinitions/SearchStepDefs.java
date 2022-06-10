package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.Homepage;
import pages.SearchResultsPage;
import org.openqa.selenium.support.PageFactory;

public class SearchStepDefs {

    //This is the syntax to writing the test class, this is where the actual test classes are.
    //--This is a normal java class that we use for our actual testing


    WebDriver driver;
    Homepage homepage;


        @Given("I am on the home page")
        public void i_am_on_the_home_page() {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get( "http://automationpractice.com/index.php" );
            homepage = new Homepage( driver );

           }
    @When("I search for a Blouse")
    public void i_search_for_a_blouse() {
        System.out.println("Searching for blouse test");
        Homepage homepage = PageFactory.initElements( driver,Homepage.class );
        homepage.searchBar.sendKeys( "Blouse",Keys.ENTER );
    }
    @Then("I should see the Blouse when I land on the results page")
    public void i_should_see_the_blouse_when_i_land_on_the_results_page() {
        System.out.println("Verify test");
        SearchResultsPage searchResultsPage = PageFactory.initElements( driver,SearchResultsPage.class );
        Assert.assertTrue( searchResultsPage.blouseProduct.getText().contains( "Blouse" ) );
    }







}
