package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/GetProduct.feature",
        glue = {"stepDefinitions"},
     //   tags = "@smoke",  // Tags to include or exclude specific scenarios
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        dryRun = false
)
public class TestRunner {
	
}
