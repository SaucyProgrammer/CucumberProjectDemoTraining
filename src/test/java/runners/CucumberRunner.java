package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(

        tags = "@api",
//        "@search or all",
        //"@search "will only run scenarios with the given tag
        //"@search or productDetails" will runs scenarios that have it separately on their own scenario
        //"@search and productDetails" will only run scenario's that have these tags together on top of it.
        //"productDetails and not search" will only run productDetails and not search

        features = "src/test/resources",
        glue ="stepDefinitions/duobank",
        dryRun = true, //shows up separately running
        plugin = {"pretty"}//prints the report

)
public class CucumberRunner {
}





