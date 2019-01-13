package CourseFinalProject.steps.serenity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import CourseFinalProject.pages.TestMasterPage;
import net.thucydides.core.annotations.Step;

public class TestMasterUserSteps {

	TestMasterPage testMasterPage;

	String currentEmail;

	@Step
	public void is_on_home_page() {
		testMasterPage.open();
		testMasterPage.scroll_to_register();
	}

	@Step
	public void enter_email(String email) {
		testMasterPage.enter_email(email);
	}

	@Step
	public void start_register() {
		testMasterPage.register();
	}

	@Step
	public void enter_valid_email() {
		testMasterPage.enter_valid_email();
		testMasterPage.register();
	}

	@Step
	public void enter_registered_email() {
		currentEmail = testMasterPage.enter_registered_email();
		testMasterPage.register();
	}

	// do register by pressing enter

	@Step
	public void enter_name(String name) {
		testMasterPage.enter_name(name);
	}

	@Step
	public void enter_gender(String gender) {
		testMasterPage.enter_gender(gender);
	}

	@Step
	public void enter_news_type(String newsType) {
		testMasterPage.enter_news_type(newsType);
	}

	@Step
	public void do_register() {
		testMasterPage.do_agree();
	}

	@Step
	public void enter_all_extra_info(String name, String gender, String newsType) {
		testMasterPage.enter_name(name);
		testMasterPage.enter_gender(gender);
		testMasterPage.enter_news_type(newsType);
		testMasterPage.do_agree();
	}

	@Step
	public void shoud_see_invalid_email_message(String invalidMessage) {
		assertThat(testMasterPage.get_invalid_email_message(), is(equalTo(invalidMessage)));
	}

	@Step
	public void shoud_see_existed_email_message() {
		System.out.println("testMasterPage.getExistedMessage() " + testMasterPage.get_existed_message());
		String existedMessage = "E-mail " + currentEmail + " đã được sử dụng, bạn hãy chọn một E-mail khác";
		assertThat(testMasterPage.get_existed_message(), is(equalTo(existedMessage)));
	}

	@Step
	public void shoud_see_required_name_message(String requiredNameMessage) {
		assertThat(testMasterPage.get_required_name_message(), is(equalTo(requiredNameMessage)));
	}

	@Step
	public void shoud_see_success_registed_email_message(String registeredMessage) throws Exception {
		assertThat(testMasterPage.get_success_registed_message(), is(equalTo(registeredMessage)));
		testMasterPage.set_email_used(testMasterPage.getCurrentEmailInfo());
		assertThat(testMasterPage.is_open_registed_link_ok(), is(true));
	}
}
