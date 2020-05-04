package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources",
        glue = "com.seera",
        strict = true,
        tags = {"@challenge02"},
        plugin = { "pretty", "html:build/cucumber-html-reports"},
        monochrome = true
)
public class TestRunner {

}
