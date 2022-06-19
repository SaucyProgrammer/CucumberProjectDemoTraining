package stepDefinitions.duotifyStepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.List;

public class BusinessLogicAndRules {

    List<String> actualColumnNames;
    String shareUserNWithMethod;
    String  shareLastName;

    /** Since these two tests do not need a ui, it is only database testing to confirm, we can leave out the UI **/
    @When("I send a query to retrieve column names for songs table")
    public void i_send_a_query_to_retrieve_column_names_for_songs_table() {

         actualColumnNames  = DBUtility.getColumnNames( "select * from songs;" ); //we send the actual database query
        /*
        These are sorted this way in the feature file so that we can compare by list of strings

      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |*/

    }
    @Then("the column names should be the following")
    public void the_column_names_should_be_the_following(List<String> expectedColumnNames) {

        Assert.assertEquals( expectedColumnNames,actualColumnNames );

    }


//----------------------------------------------------------------------------------------------------------------------------------------------------------

    /** Updating a customers last name for unicode support **/
    @When("I update the last name of the user with the username {string} with {string}")
    public void i_update_the_last_name_of_the_user_with_the_username_with(String username, String expectedLastName) throws SQLException {
        shareUserNWithMethod = username;
        shareLastName = expectedLastName;
        String query = "update users set lastName='"+expectedLastName+"' where the username='"+username+"'";
        DBUtility.updateQuery( query );

    }
    @Then("the value should be updated correctly")
    public void the_value_should_be_updated_correctly() {
        String query = "select lastName from users where username='"+shareUserNWithMethod+"';";
        List<List<Object>> lastName = DBUtility.getQueryResultAsListOfLists(query);

        Assert.assertEquals( shareLastName,lastName.get( 0 ).get( 0 ).toString() );




    }
}
