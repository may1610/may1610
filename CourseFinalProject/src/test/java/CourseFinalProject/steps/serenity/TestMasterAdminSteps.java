package CourseFinalProject.steps.serenity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import CourseFinalProject.pages.TestMasterAdminPage;
import common.Constants;
import net.thucydides.core.annotations.Step;

public class TestMasterAdminSteps {

	TestMasterAdminPage testMasterAdminPage;
	String currentTagText = "";

	@Step
	public void do_login() {
		testMasterAdminPage.open();
		testMasterAdminPage.enter_email(Constants.ADMIN_ACC_EMAIL);
		testMasterAdminPage.enter_password(Constants.ADMIN_ACC_PASSWORD);
		testMasterAdminPage.login();		
	}
	
	@Step
	public void select_send_news_page() {
		testMasterAdminPage.select_send_news();
	}

	@Step
	public void is_on_home_page() {
		testMasterAdminPage.open();
	}
	
	@Step
	public void complete_providing_news_info() {
		testMasterAdminPage.complete_providing_news_info();
	}
	
	@Step
	public void enter_email(String email) {
		testMasterAdminPage.enter_email(email);
	}

	@Step
	public void enter_password(String password) {
		testMasterAdminPage.enter_password(password);
	}

	@Step
	public void start_login() {
		testMasterAdminPage.login();
	}

	@Step
	public void enter_name(String name) {
		testMasterAdminPage.enter_name(name);
	}

	@Step
	public void enter_subject(String subject) {
		testMasterAdminPage.enter_subject(subject);
	}

	@Step
	public void enter_news_content(String content) {
		testMasterAdminPage.enter_news_content(content);
	}

	@Step
	public void add_files() throws Exception {
		testMasterAdminPage.add_files();
	}

	@Step
	public void send_news() {
		testMasterAdminPage.send_news();
	}

	@Step
	public void send_news_later(String date, String time) {
		testMasterAdminPage.send_news_later(date, time);
	}

	@Step
	public void enter_all_news_info(String name, String subject, String newsContent) {		
		testMasterAdminPage.enter_name(name);
		testMasterAdminPage.enter_subject(subject);		
		testMasterAdminPage.enter_news_content(newsContent);
	}

	@Step
	public void shoud_see_send_news_success(String successMassage) throws Exception {
		assertThat(testMasterAdminPage.get_send_news_success_message(), is(equalTo(successMassage)));
		assertThat(testMasterAdminPage.is_receive_news_email(), is(true));
	}

	@Step
	public void shoud_see_required_name_message(String requiredNameMessage) {
		assertThat(testMasterAdminPage.get_required_name_message(), is(equalTo(requiredNameMessage)));
	}

	@Step
	public void shoud_see_required_subject_message(String requiredSubjectMessage) {
		assertThat(testMasterAdminPage.get_required_subject_message(), is(equalTo(requiredSubjectMessage)));
	}

	@Step
	public void shoud_see_required_content_message(String requiredContentMessage) throws Exception {
		assertThat(testMasterAdminPage.get_required_content_message(), is(equalTo(requiredContentMessage)));		
	}
	
	@Step
	public void shoud_see_merge_tag_list_displayed() throws Exception {
		assertThat(testMasterAdminPage.is_merge_tag_displayed(), is(true));		
	}
	
	@Step
	public void select_merge_tag(String tagText){
		currentTagText = tagText;		
		testMasterAdminPage.select_merge_tag(tagText);		
	}
	
	@Step
	public void should_see_the_merge_tag_will_be_marked_as_block() {		
		assertThat(testMasterAdminPage.is_merge_tag_marked_as_block(currentTagText), is(true));		
	}
	
	@Step
	public void should_see_the_list_of_files_with_corresponding_file_icon() {		
		assertThat(testMasterAdminPage.is_list_of_attached_files_display(), is(true));		
	}
}
