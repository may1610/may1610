package CourseFinalProject.pages;
import javax.mail.Message;
import org.openqa.selenium.WebDriver;

import common.Constants;
import common.DataHelper;
import common.MailReader;
import common.Utility;
//import common.*;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Scroll;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://testmaster.vn/")
public class TestMasterPage extends PageObject {

	private DataHelper dataHelper = new DataHelper("dataFiles\\email.xlsx", "Sheet1");
	private String[] currentEmailInfo;

	@FindBy(xpath = ".//input[@type='email']")
	private WebElementFacade txtEmail;

	@FindBy(xpath = ".//div[@class='footer']//button[@class='next-btn-primary']")
	private WebElementFacade btnRegister;

	@FindBy(xpath = ".//div[@class='footer']//div[contains(@class,'error')]//span")
	private WebElementFacade invalidEmailMessage;

	@FindBy(id = "fullname")
	private WebElementFacade txtName;

	@FindBy(id = "ddlGender")
	private WebElementFacade ddlGender;

	@FindBy(id = "ddlNewsType")
	private WebElementFacade ddlNewsType;

	@FindBy(id = "allow-button")
	private WebElementFacade btnAgree;

	@FindBy(xpath = ".//div[@class='error']")
	private WebElementFacade requiredNameMessage;

	@FindBy(xpath = ".//div[@id='popover']//div[@class='body-message']")
	private WebElementFacade dialogMessage;

	/**
	 * current info of email processing: email and row index in excel file
	 * 
	 * @return
	 */
	public String[] getCurrentEmailInfo() {
		return currentEmailInfo;
	}

	
	public void scroll_to_register() {
		WebDriver driver = getDriver();
		driver.manage().window().maximize();
		//Utility.scrolled_element_into_view(driver, btnRegister);
	}

	public void enter_email(String email) {
		Scroll.to(txtEmail);
		txtEmail.clear();
		txtEmail.type(email);
	}

	public void enter_valid_email() {
		/**
		 * get brand new email
		 */
		currentEmailInfo = dataHelper
				.getOneMatchCell(Constants.EMAIL_COL_NAME, Constants.USED_EMAIL_COL_NAME, Constants.UN_USED_STATUS)
				.split(";");
		String email = currentEmailInfo[1];
		enter_email(email);
		System.out.println("email[1] " + email);
		btnRegister.waitUntilClickable();
	}

	public String enter_registered_email() {
		/**
		 * get already registered email
		 */
		String[] emailInfo = dataHelper
				.getOneMatchCell(Constants.EMAIL_COL_NAME, Constants.USED_EMAIL_COL_NAME, Constants.USED_STATUS)
				.split(";");
		String email = emailInfo[1];
		enter_email(email);
		return email;
	}

	public void register() {
		btnRegister.click();
	}

	public void enter_name(String name) {
		/**
		 * click register then wait for txtName presented first
		 */
		txtName.waitUntilPresent();
		txtName.clear();
		txtName.type(name);
	}

	public void enter_gender(String gender) {
		ddlGender.click();
		Utility.selectByTextDisplay(this.getDriver(), gender);
	}

	public void enter_news_type(String newsType) {
		ddlNewsType.click();
		Utility.selectByTextDisplay(this.getDriver(), newsType);
	}

	public void do_agree() {
		btnAgree.click();	
	}

	public String get_invalid_email_message() {
		return invalidEmailMessage.getText();
	}

	public String get_required_name_message() {
		return requiredNameMessage.getText();
	}

	public String get_success_registed_message() {
		dialogMessage.waitUntilPresent();
		return dialogMessage.getText();
	}

	public String get_existed_message() {
		dialogMessage.waitUntilPresent();
		return dialogMessage.getText();
	}

	public void set_email_used(String[] currEmailInfo) {
		/**
		 * set email has been used
		 */
		dataHelper.update(Constants.USED_EMAIL_COL_NAME, Integer.parseInt(currEmailInfo[0]), Constants.USED_STATUS);
	}

	/**
	 * Fix one email to check mail. do forward email receive from test master in someone's email inbox to that fix mail then check 
	 * @return
	 * @throws Exception
	 */
	public boolean is_open_registed_link_ok() throws Exception {
		String emailContent = "";
		MailReader mailReader;
		String activationLink = "";
		long startTime = System.currentTimeMillis();
		Message message;
		while (System.currentTimeMillis() - startTime < (60 * 1000) * 10) { // wait for maximum 10 minutes
			mailReader = new MailReader(Constants.FIX_SUBSCRIBER_EMAIL, Constants.FIX_SUBSCRIBER_PASSWORD);  
			if (mailReader.messages.length > 0) {
				message = mailReader.messages[mailReader.messages.length - 1]; // get the newest email
				emailContent = mailReader.getContent(message);
				System.out.println(emailContent);

			}
			if (emailContent == "")
				Thread.sleep(10000);
			else {/* if found subscription mail from testmaster page then break */
				activationLink = Utility.getHref(emailContent, Constants.TESTMASTER_URL);
				if (!activationLink.isEmpty()) {
					System.out.println("activationLink =================" + activationLink);
					break;
				}
			}
		}

		if (!activationLink.isEmpty()) {
			openUrl(activationLink);
			waitForTitleToAppear("Kích hoạt đăng ký thành công - Testmaster.vn");
			return true;
		} else
			return false;
	}	
}
