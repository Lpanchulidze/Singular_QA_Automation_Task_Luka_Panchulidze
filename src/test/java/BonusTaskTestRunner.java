import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/.feature",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true)
public class BonusTaskTestRunner {
}
