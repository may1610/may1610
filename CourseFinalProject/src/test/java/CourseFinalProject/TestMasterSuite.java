package CourseFinalProject;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@CucumberOptions(features = "src/test/resources/features/SubscribeNews.feature")
@CucumberOptions(features = "src/test/resources/features/SendNews.feature")
public class TestMasterSuite {
}
