package CourseFinalProject.steps;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.ScenarioType;

import CourseFinalProject.steps.serenity.TestMasterAdminSteps;
import CourseFinalProject.steps.serenity.TestMasterUserSteps;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import net.thucydides.core.annotations.Steps;

public class SendNewsSteps {
	@Steps
	TestMasterAdminSteps may;
	
	
	@Before
	public void login() {
		may.do_login();
	}
	
	@Given("^the user is on send news page$")
	public void the_user_is_on_send_news_page() {
	   may.select_send_news_page();
	}


	@When("^the user does not provide news information$")
	public void the_user_does_not_provide_news_information(){
	    may.complete_providing_info();
	}

	@When("^the user provides news information and sends$")
	public void the_user_provides_news_information_and_sends(){
	   may.enter_all_news_info("May", "Test subject", "Test content");
	   //may.add_file();
	   may.send_news();
	}

	@Then("^they should see the success message \"([^\"]*)\"$")
	public void they_should_see_the_success_message(String successMassage) throws Exception{
	    may.shoud_see_send_news_success(successMassage);
	}

	@When("^the user provides news information and sends on schedule$")
	public void the_user_provides_news_information_and_sends_on_schedule() {
		may.enter_all_news_info("May", "Test subject", "Test content");
		may.send_news_later();
	}

}
