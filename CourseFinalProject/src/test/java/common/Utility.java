package common;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility {
	/**
	 * This method will set any parameter string to the system's clipboard.
	 */
	private static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	/**
	 * This method is used to put the file location to select file upload dialog
	 * 
	 * @param fileLocation
	 */
	public static void uploadFile(String fileLocation) {
		try {
			// Setting clip board with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * Scroll element into browser view
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrolled_element_into_view(WebDriver driver, WebElement element) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void moveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Switch to windows. If windows Handler is empty, the top windows will be
	 * switched to
	 * 
	 * @param driver
	 * @param windowHandler
	 */
	public static void switch_window(WebDriver driver, String windowHandler) {

		if (!windowHandler.isEmpty()) {
			driver.switchTo().window(windowHandler);
		} else {
			for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
			}
		}
	}

	/**
	 * The method to check the specific file is existed or not
	 * 
	 * @param fileLocation
	 * @return
	 */
	public static boolean checkExistFile(String fileLocation) {
		File f = new File(fileLocation);
		if (f.exists() && !f.isDirectory())
			return true;
		return false;
	}

	public static void selectByTextDisplay(WebDriver driver, String selectText) {
		WebElement element = driver
				.findElement(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'" + selectText + "')]"));
		element.click();
	}

	public static String getHref(String content) {
		String str = "<a href=\"http://testmaster.vn/\">Click</a>";
		Pattern p = Pattern.compile("href=\\\"(?<link>.*)\\\"");
		Matcher m = p.matcher(content);
		if (m.find()) {
			return m.group(m.groupCount());
		}
		return "";
	}

	public static String getHref(String content, String linkPrefix) {
		Pattern p = Pattern.compile("href=\"(" + linkPrefix + "[^\"]*)\"");
		Matcher m = p.matcher(content);
		if (m.find()) {
			return m.group(m.groupCount());
		}
		return "";
	}
}
