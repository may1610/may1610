package CourseFinalProject.pages;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BusinessObject.Account;
import common.Constants;
import common.MailReader;
import common.Utility;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Scroll;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://testmaster.vn/admin")
public class TestMasterAdminPage extends PageObject {

	@FindBy(xpath = ".//input[@type='email']")
	private WebElementFacade txtEmail;

	@FindBy(xpath = ".//input[@type='password']")
	private WebElementFacade txtPassword;

	@FindBy(xpath = ".//button[@type='button']")
	private WebElementFacade btnLogin;

	@FindBy(xpath = ".//a[@action='/subscription/sendnews']")
	private WebElementFacade mnuSendNews;

	@FindBy(id = "txtName")
	private WebElementFacade txtName;

	@FindBy(xpath = ".//input[@id='txtName']/../div[@class='error']")
	private WebElementFacade lblNameRequired;

	@FindBy(id = "txtSubject")
	private WebElementFacade txtSubject;

	@FindBy(xpath = ".//input[@id='txtSubject']/../div[@class='error']")
	private WebElementFacade lblSubjectRequired;

	@FindBy(xpath = ".//div[contains(@class,'note-editable')]")
	private WebElementFacade editorBody;

	@FindBy(xpath = ".//div[@class = 'note-editing-area']/../../div[@class='error']")
	private WebElementFacade lblBodyRequired;

	@FindBy(xpath = ".//input[@type='file' and @name='myfile']")
	private WebElementFacade btnAttach;

	@FindBy(xpath = ".//button[contains(@class,'btn-next')]")
	private WebElementFacade btnNext;

	@FindBy(id = "sendNow")
	private WebElementFacade btnSendNow;

	@FindBy(id = "input-date")
	private WebElementFacade calDate;

	@FindBy(id = "input-time")
	private WebElementFacade calTime;

	@FindBy(id = ".//button[contains(@class,'btn-save-news')]")
	private WebElementFacade btnSendLater;

	@FindBy(xpath = ".//div[@id='popover']//div[@class='body-message']")
	private WebElementFacade dialogMessage;
	
	@FindBy(xpath = ".//div[@class='content']")
	private WebElementFacade divInfo;
	

	public void scroll_to_attach() {		
		Utility.scrolled_element_into_view(getDriver(), btnAttach);
	}

	public void enter_email(String email) {
		txtEmail.clear();
		txtEmail.type(email);
	}

	public void enter_password(String password) {
		txtPassword.clear();
		txtPassword.type(password);
	}

	public void login() {
		btnLogin.click();
		waitForTitleToAppear("Home Page - Testmaster.vn");
		getDriver().manage().window().maximize();
	}

	public void enter_name(String name) {
		txtName.clear();
		txtName.type(name);
	}

	public void enter_subject(String subject) {
		txtSubject.clear();
		txtSubject.type(subject);
	}

	public void enter_news_content(String content) {
		editorBody.clear();
		editorBody.type(content);		
	}

	public void select_send_news() {
		mnuSendNews.click();		
	}

	public void add_file() {
		Utility.scrolled_element_into_view(getDriver(), btnAttach);
		upload("dataFiles\\test.txt").to(btnAttach);
	}

	public void complete_providing_info() {
		btnNext.click();
	}

	public void send_news() {
		// btnNext.click();
		btnSendNow.click();
	}

	public void enter_date(String date) {
		calDate.typeAndTab(date);
	}

	public void enter_time(String time) {
		calDate.typeAndTab(time);
	}

	public void send_news_later() {
		btnSendLater.click();
	}

	public String get_send_news_success_message() {
		dialogMessage.waitUntilPresent();
		return dialogMessage.getText();
	}

	public String get_required_name_message() {
		return lblNameRequired.getText();
	}

	public String get_required_subject_message() {
		return lblSubjectRequired.getText();
	}

	public String get_required_content_message() {
		return lblBodyRequired.getText();
	}

	/**
	 * List email get from system to send news, just add sample here
	 * 
	 * @return
	 */
	private List<Account> createSubscriberList() {
		List<Account> lstEmail = new ArrayList<Account>();
		Account email1 = new Account(Constants.FIX_SUBSCRIBER_EMAIL, Constants.FIX_SUBSCRIBER_PASSWORD);
		lstEmail.add(email1);
		return lstEmail;
	}

	public boolean is_receive_news_email() throws Exception {
		List<Account> lstAccount = createSubscriberList();
		String emailContent = "";
		MailReader mailReader;
		String newsLink = "";
		long startTime = System.currentTimeMillis();
		Message message;
		boolean isAllSubReceiveMail = false;

		for (Account account : lstAccount) {
			/* reset check */
			isAllSubReceiveMail = false;
			while (System.currentTimeMillis() - startTime < (60 * 1000) * 10) { // wait for maximum 10 minutes per email
				mailReader = new MailReader(account.getEmail(), account.getPassword());
				if (mailReader.messages.length > 0) {
					message = mailReader.messages[mailReader.messages.length - 1]; // get the newest email
					emailContent = mailReader.getContent(message);
					System.out.println(emailContent);

				}
				if (emailContent == "")
					Thread.sleep(10000);
				else {/* if found news mail from testmaster page then break */
					newsLink = Utility.getHref(emailContent, Constants.TESTMASTER_URL);
					if (!newsLink.isEmpty()) {
						System.out.println("News link =================" + newsLink);
						isAllSubReceiveMail = true;
						break;
					}
				}
			}
		}

		return isAllSubReceiveMail;
	}
}
