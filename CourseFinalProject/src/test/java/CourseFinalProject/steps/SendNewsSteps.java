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

	@Before("@Backend")
	public void login() {
		may.do_login();
	}

	@Given("^the user is on send news page$")
	public void the_user_is_on_send_news_page() {
		may.select_send_news_page();
	}

	@When("^the user does not provide news information$")
	public void the_user_does_not_provide_news_information() {
		may.complete_providing_news_info();
	}

	@When("^the user provides news information and sends$")
	public void the_user_provides_news_information_and_sends() throws Exception {
		may.enter_all_news_info("May", "Test subject", "Test content");
		may.add_files();
		may.send_news();
	}

	@Then("^they should see the success message \"([^\"]*)\"$")
	public void they_should_see_the_success_message(String successMassage) throws Exception {
		may.shoud_see_send_news_success(successMassage);
	}

	@When("^the user provides news information and sends on schedule$")
	public void the_user_provides_news_information_and_sends_on_schedule() throws Exception {
		may.enter_all_news_info("May", "Test subject", "Test content");
		may.add_files();
		may.send_news_later("14/01/2019", "14:00");
	}

	@When("^the user type letter \"([^\"]*)\" into news content$")
	public void the_user_type_letter_into_news_content(String letter) {
		may.enter_news_content(letter);
	}

	@Then("^they should see merge tag list displayed$")
	public void they_should_see_merge_tag_list_displayed() throws Exception {
		may.shoud_see_merge_tag_list_displayed();
	}

	@When("^the user select merge tag \"([^\"]*)\"$")
	public void the_user_select_merge_tag(String tagText) {
		may.select_merge_tag(tagText);
	}

	@Then("^they should see the merge tag will be marked as block$")
	public void they_should_see_the_merge_tag_will_be_marked_as_block() {
		may.should_see_the_merge_tag_will_be_marked_as_block();
	}

	@When("^the user add files$")
	public void the_user_add_files() throws Exception {
		may.add_files();
	}

	@Then("^they should see the list of files with corresponding file icon$")
	public void they_should_see_the_list_of_files_with_corresponding_file_icon() {
		may.should_see_the_list_of_files_with_corresponding_file_icon();
	}

}
