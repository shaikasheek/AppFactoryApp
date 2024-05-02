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

public class LoginModule extends AdminConsoleBaseTest{

SgConfiguration sg = SgConfiguration.getInstance();

	@BeforeMethod
	public void setupBeforeTest() throws Exception {
		
		//FrmLoginKA.navigateFromLoginForm();
		// Check for login form
		//if (!FrmLoginKA.isFrmLoginKA())
		//	throw new Exception("Failed to navigate to LonginKA form!");
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
	@Test(description = "Test case to login with correct credentials")
	public void login() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("password"));
		Object nextForm = frmLogin.clickLogin();

		if (nextForm.getClass().equals(FrmDashboardKA.class)) {

			Assert.assertTrue(FrmDashboardKA.isFormDashboardKA());
			FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;
			frmDashboardKA.clickLogout();
			Assert.assertTrue(FrmLoginKA.isFrmLoginKA());
		} else {
			Assert.fail("Login failed! wrong credentials");
		}

	}

	@Test(description = "Test case to update password for expired user")
	public void resetPasswordForAExpiredUser() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("expiredUsername"));
		frmLogin.enterPassword(sg.getKeyValue("expiredPassword"));
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmChangePassword.isFrmChangePassword(),
				"Reset password For a expired user Failed. Did not navigate to change password page");
		FrmChangePassword frmChangePassword = (FrmChangePassword) nextForm;
		frmChangePassword.enterPreviousPwd(sg.getKeyValue("expiredPassword"));
		
		//Enter wrong new password
		frmChangePassword.enterNewPwd(sg.getKeyValue("wrongPassword"));
		String errorLbl = frmChangePassword.getIncorrectPwdErr();
		String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmChangePassword_IncorrectNewPwd");
		Assert.assertEquals(errorLbl, expectedErrorLbl);
		
		//Correct new pwd
		frmChangePassword.enterNewPwd(sg.getKeyValue("newPassword"));
		
		//Enter wrong confirm new password
		frmChangePassword.enterConfirmPwd(sg.getKeyValue("wrongPassword"));
		String errorLabel = frmChangePassword.getIncorrectConfirmPwdErr();
		String expectedErrorLabel = AdminConsoleNames.getWidgetName("frmChangePassword_IncorrectConfirmPwd");
		Assert.assertEquals(errorLabel, expectedErrorLabel);
		
		//correct confirm pwd
		frmChangePassword.enterConfirmPwd(sg.getKeyValue("newPassword"));
		Object changePwdForm = frmChangePassword.clickSubmit();
		Assert.assertTrue(FrmChangePasswordSuccess.isFrmChangePasswordSuccess(),
				"Password change text failed! Did not navigate to success page");
		FrmChangePasswordSuccess frmChangePasswordSuccess = (FrmChangePasswordSuccess) changePwdForm;
		FrmLoginKA frmLoginKA = frmChangePasswordSuccess.clickReLogin();

		frmLoginKA.enterUsername(sg.getKeyValue("expiredUsername"));
		frmLoginKA.enterPassword(sg.getKeyValue("newPassword"));
		nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(), "Login failed with new password!");
		FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;
		frmDashboardKA.clickLogout();
		Assert.assertTrue(FrmLoginKA.isFrmLoginKA());

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

	@Test(description = "Test case to update password for a user")
	public void resetPasswordAfterLogin() throws Exception {

		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("password"));
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(),
				"Reset password after login Failed! Did not navigate to dashboard page");
		FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;

		// Navigate to Update password page
		FrmChangePassword frmChangePassword = frmDashboardKA.clickUpdatePassword();
		Assert.assertTrue(FrmChangePassword.isFrmChangePassword());

		// Enter previous and current passwords
		frmChangePassword.enterPreviousPwd(sg.getKeyValue("password"));
		frmChangePassword.enterNewPwd(sg.getKeyValue("newPassword"));
		frmChangePassword.enterConfirmPwd(sg.getKeyValue("newPassword"));

		// Submit change password details
		Object fromObj = frmChangePassword.clickSubmit();
		Assert.assertTrue(FrmChangePasswordSuccess.isFrmChangePasswordSuccess(), "Change password failed.");
		FrmChangePasswordSuccess frmChangePasswordSuccess = (FrmChangePasswordSuccess)fromObj;
		FrmLoginKA frmLoginKA = frmChangePasswordSuccess.clickReLogin();

		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("newPassword"));
		nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(), "Login failed with new password!");
		FrmDashboardKA frmDashboard = (FrmDashboardKA) nextForm;
		frmDashboard.clickLogout();
		Assert.assertTrue(FrmLoginKA.isFrmLoginKA());

	}
	
	@Test(description = "Test case to suspend a user")
	public void suspendAUser() throws Exception {
		int AttemptsToSuspend = Integer.parseInt(sg.getKeyValue("attemptsToSuspendAcc"));

		for (int count = 1; count <= 4; count++) {
			FrmLoginKA frmLogin = new FrmLoginKA();
			frmLogin.enterUsername(sg.getKeyValue("suspendUser"));
			frmLogin.enterPassword(sg.getKeyValue("wrongPassword"));
			Object nextForm = frmLogin.clickLogin();
			if (count < AttemptsToSuspend) {

				Assert.assertTrue(FrmLoginKA.isFrmLoginKA(),
						"Suspend a user Failed! Application navigated to form other than loginKA with wrong password.");
				FrmLoginKA frmLoginKA = (FrmLoginKA)nextForm;
				
				if (count == AttemptsToSuspend - 1) {
					String errorLbl = frmLoginKA.getErrorText();
					if(errorLbl == null)
						Assert.fail("Invalid Password error msg not visible");
					
					String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_oneAttemptLeftErr");
					Assert.assertEquals(errorLbl, expectedErrorLbl);
				} else {
					String errorLbl = frmLoginKA.getErrorText();
					if(errorLbl == null)
						Assert.fail("Invalid Password error msg not visible");
					
					String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_WrongPasswordErr");
					Assert.assertEquals(errorLbl, expectedErrorLbl);
				}

			} else if (count == AttemptsToSuspend) {
				Assert.assertTrue(FrmAccountSuspended.isFrmAccountSuspended(),
						"Suspend a user Failed! Application did not navigate to suspended account page after "
								+ AttemptsToSuspend + " incorrect  login attempts");
				FrmAccountSuspended frmAccountSuspended = (FrmAccountSuspended)nextForm;
				//frmAccountSuspended.clickBack();
			}
		}
	}
	
	@Test(description = "Test case to verify session expiry")
	public void sessionExpiry() throws Exception {
		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("password"));
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(),
				"verify session expiry Failed! Did not navigate to dashboard page");
		FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;
		
		AppSpecificFunctions.waitForClientSessionTimeout();
		
		Assert.assertTrue(FrmLoginKA.isFrmLoginKA(),
				"verify session expiry Failed! Did not navigate to login page after idle timeout");
		frmLogin = new FrmLoginKA();
		String errorLbl = frmLogin.getSessionTimeoutMsg();
		String expectedErrorLbl = AdminConsoleNames.getWidgetName("frmLoginKA_SessionExpiredMsg");
		Assert.assertEquals(errorLbl, expectedErrorLbl);
	}
	
	@Test(description = "Test case to verify remember me")
	public void rememberMeEnabled() throws Exception {
		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("password"));
		frmLogin.enableRememberMe();
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(),
				"Remember me Failed! Did not navigate to dashboard page");
		FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;
		FrmLoginKA frmLoginKA = frmDashboardKA.clickLogout();
		
		String txt = frmLoginKA.getUsernameText();
		String expectedTxt = sg.getKeyValue("username");
		Assert.assertEquals(txt, expectedTxt);
		
		
	}

	@Test(description = "Test case to verify remember me")
	public void rememberMeDisabled() throws Exception {
		FrmLoginKA frmLogin = new FrmLoginKA();
		frmLogin.enterUsername(sg.getKeyValue("username"));
		frmLogin.enterPassword(sg.getKeyValue("password"));
		frmLogin.disableRememberMe();
		Object nextForm = frmLogin.clickLogin();

		Assert.assertTrue(FrmDashboardKA.isFormDashboardKA(),
				"Remember me Failed! Did not navigate to dashboard page");
		FrmDashboardKA frmDashboardKA = (FrmDashboardKA) nextForm;
		FrmLoginKA frmLoginKA = frmDashboardKA.clickLogout();
		
		String txt = frmLoginKA.getUsernameText();
		String expectedTxt = "";
		Assert.assertEquals(txt, expectedTxt);
		
		
	}
	@Test(enabled=false,description = "HeadlessChromeDriverTest")
	  public void HeadlessChromeDriverTest() throws IOException {
	  
	  System.setProperty("webdriver.chrome.driver", System.getProperty("DRIVER_PATH"));
	  ChromeOptions chromeOptions = new ChromeOptions();
	  chromeOptions.addArguments("--headless");
	 // chromeOptions.addArguments("--no-sandbox");
	   chromeOptions.setBinary(System.getProperty("BROWSER_PATH"));
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
