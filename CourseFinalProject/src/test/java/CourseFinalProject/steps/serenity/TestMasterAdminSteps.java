package CourseFinalProject.steps.serenity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import CourseFinalProject.pages.TestMasterAdminPage;
import common.Constants;
import net.thucydides.core.annotations.Step;

public class TestMasterAdminSteps {

	TestMasterAdminPage testMasterAdminPage;

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
	public void complete_providing_info() {
		testMasterAdminPage.complete_providing_info();
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
	public void add_file() {
		testMasterAdminPage.add_file();
	}

	@Step
	public void send_news() {
		testMasterAdminPage.send_news();
	}

	@Step
	public void send_news_later() {
		testMasterAdminPage.send_news_later();
	}

	@Step
	public void enter_all_news_info(String name, String subject, String newsContent) {		
		testMasterAdminPage.enter_name(name);
		testMasterAdminPage.enter_subject(subject);		
		testMasterAdminPage.enter_news_content(newsContent);
	}

	@Step
	public void enter_all_news_info_and_time_to_send(String name, String subject, String newsContent, String date,
			String time) {
		testMasterAdminPage.enter_name(name);
		testMasterAdminPage.enter_subject(subject);
		testMasterAdminPage.enter_news_content(newsContent);
		testMasterAdminPage.enter_date(date);
		testMasterAdminPage.enter_time(time);
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
}
