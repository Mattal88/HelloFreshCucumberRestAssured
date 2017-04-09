package stepdefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber Test Runner Class , Has the options for Cucumber
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features"
        ,glue={"stepdefinitions"}
)
public class TestRunner {
}
