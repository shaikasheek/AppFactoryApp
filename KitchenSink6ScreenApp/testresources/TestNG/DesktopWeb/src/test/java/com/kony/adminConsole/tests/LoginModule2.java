package com.kony.adminConsole.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kony.adminConsole.AdminConsoleBaseTest;
import com.kony.adminConsole.AdminConsoleNames;
import com.kony.adminConsole.AdminConsoleWidgetId;
import com.kony.adminConsole.forms.AppSpecificFunctions;
import com.kony.adminConsole.forms.FrmAccountSuspended;
import com.kony.adminConsole.forms.FrmChangePassword;
import com.kony.adminConsole.forms.FrmChangePasswordSuccess;
import com.kony.adminConsole.forms.FrmDashboardKA;
import com.kony.adminConsole.forms.FrmDownTime;
import com.kony.adminConsole.forms.FrmLoginKA;

import test.common.AppElement;
import test.common.SgConfiguration;
public class LoginModule2 extends AdminConsoleBaseTest{

SgConfiguration sg = SgConfiguration.getInstance();

	@BeforeMethod
	public void setupBeforeTest() throws Exception {
		
		FrmLoginKA.navigateFromLoginForm();
		// Check for login form
		if (!FrmLoginKA.isFrmLoginKA())
			throw new Exception("Failed to navigate to LonginKA form!");
	}

	@Test(description = "Test case to login with empty fields")
	public void LoginWithEmptyFields() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmLoginKA.isFrmLoginKA(),
				"Empty fields test failed! Application navigated to form other than loginKA with empty uername and password fields.");
		FrmLoginKA frmLoginKA = (FrmLoginKA) nextForm;
		String errorLbl = frmLoginKA.getErrorText();
		if(errorLbl == null)
			Assert.fail("Invalid Password error msg not visible");
		
		String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_EmptyFieldsErr");
		Assert.assertEquals(errorLbl, expectedErrorLbl);

	}
@Test(description = "Test case to login with empty pwd field")
	public void LoginWithEmptyPwdField() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmLoginKA.isFrmLoginKA(),
				"Empty password field test failed! Application navigated to form other than loginKA with empty password field.");
		FrmLoginKA frmLoginKA = (FrmLoginKA) nextForm;
		String errorLbl = frmLoginKA.getErrorText();
		if(errorLbl == null)
			Assert.fail("Invalid Password error msg not visible");
		
		String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_EmptyPwdFieldErr");
		Assert.assertEquals(errorLbl, expectedErrorLbl);


	}

	@Test(description = "Test case to login with wrong password")
	public void LoginWithWrongPassword() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("wrongPassword"));
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmLoginKA.isFrmLoginKA(),
				"Wrong password test failed! Application navigated to form other than loginKA with wrong password.");
		FrmLoginKA frmLoginKA = (FrmLoginKA)nextForm;
		String errorLbl = frmLoginKA.getErrorText();
		if(errorLbl == null)
			Assert.fail("Invalid Password error msg not visible");
		
		String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_WrongPasswordErr");
		Assert.assertEquals(errorLbl, expectedErrorLbl);

	}
	@Test(description = "HeadlessChromeDriverTest")
	  public void HeadlessChromeDriverTest() throws IOException {
	  
	  System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
	  ChromeOptions chromeOptions = new ChromeOptions();
	  chromeOptions.addArguments("--headless");
	 // chromeOptions.addArguments("--no-sandbox");
	   chromeOptions.setBinary("/usr/bin/my_project/node_modules/puppeteer/.local-chromium/linux-555668/chrome-linux/chrome");
	  //chromeOptions.setBinary(sg.getKeyValue(browserPath));
	  //System.out.println("****************8"+sg.getKeyValue(browserPath));
	  WebDriver driver = new ChromeDriver(chromeOptions);
	  driver.navigate().to("https://www.avenuecode.com/");
	  
	  String pageTitle = driver.getTitle();
	 // log.info("Page opened: {}", pageTitle);
	  
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  //copying the file into /screenshots directory
	  FileUtils.copyFile(scrFile, new File("output/screenshots/homepage.png"));
	  
	  Assert.assertTrue(pageTitle.contains("Avenue Code"));
	  System.out.println("Quitting driver");
	  driver.quit();
	  }
}
