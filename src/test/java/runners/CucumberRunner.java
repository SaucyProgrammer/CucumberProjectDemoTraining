package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(

        tags = "@db",
//        "@search or all",
        //"@search "will only run scenarios with the given tag
        //"@search or productDetails" will runs scenarios that have it separately on their own scenario
        //"@search and productDetails" will only run scenario's that have these tags together on top of it.
        //"productDetails and not search" will only run productDetails and not search

        features = "src/test/resources",

        glue ="stepDefinitions",

        dryRun = true, //step definitions is skipped, used for generating snippets without running the code.

        plugin = {"pretty",
                  "html:target/built-in-report/built-in-report.html",//generates a built in html report
                   "json:target/cucumber.json", //generates a built in json report which is needed for the "pretty report
                    "rerun:target/failed.txt"}//prints the report

)
public class CucumberRunner {
}





