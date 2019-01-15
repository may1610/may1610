package CourseFinalProject.steps;

import CourseFinalProject.steps.serenity.TestMasterUserSteps;
import cucumber.api.java.en.*;
import net.thucydides.core.annotations.Steps;

public class RegisterSteps {
	@Steps
	TestMasterUserSteps may;

	@Given("^the user is on testmaster home page$")
	public void the_user_is_on_testmaster_home_page() throws Exception {
		may.is_on_home_page();
	}

	@When("^the user register with an invalid \"([^\"]*)\"$")
	public void the_user_register_with_an_invalid(String email) throws Exception {
		may.enter_email(email);
	}

	@Then("^they should see the error message \"([^\"]*)\"$")
	public void they_should_see_the_error_message(String arg1) throws Exception {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^the user register with a valid email$")
	public void the_user_register_with_a_valid_email() throws Exception {
		may.enter_valid_email();
	}

	@When("^fill in extra information \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void fill_in_extra_information(String name, String gender, String newsType) throws Exception {
		may.enter_all_extra_info(name, gender, newsType);

	}

	@Then("^they should see the successfull message \"([^\"]*)\"$")
	public void they_should_see_the_successfull_message(String registeredMessage) throws Exception {
		may.shoud_see_success_registed_email_message(registeredMessage);
	}

	@When("^the user register with a registered email$")
	public void the_user_register_with_a_registered_email() throws Exception {
		may.enter_registered_email();
	}

	@Then("^they should see the existed message$")
	public void they_should_see_the_existed_message() throws Exception {
		may.shoud_see_existed_email_message();

	}

	@When("^the user does not provide name$")
	public void the_user_does_not_provide_name() throws Exception {
		may.do_register();
	}

	@Then("^they should see the required name message \"([^\"]*)\"$")
	public void they_should_see_the_required_name_message(String requiredNameMessage) throws Exception {

		may.shoud_see_required_name_message(requiredNameMessage);
	}

}
