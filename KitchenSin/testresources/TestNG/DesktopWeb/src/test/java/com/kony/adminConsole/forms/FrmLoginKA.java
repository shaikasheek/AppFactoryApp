package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleNames;
import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmLoginKA {

	public FrmLoginKA() throws Exception {
		AppElement frmobject = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_Header"));
	}

	public static boolean isFrmLoginKA() throws Exception {

		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmLoginKA_Header"));
	}
	
	public static void navigateFromLoginForm() throws Exception {
		if(!isFrmLoginKA()) {
			if (FrmDashboardKA.isFormDashboardKA()) {
				FrmDashboardKA frmDashboardKA = new FrmDashboardKA();
				frmDashboardKA.clickLogout();

			} else if (FrmChangePassword.isFrmChangePassword()) {
				FrmChangePassword frmUpdatePassword = new FrmChangePassword();
				frmUpdatePassword.clickCancel();
				
			} else if (FrmDownTime.isFrmDownTime()) {
				FrmDownTime frmDownTime = new FrmDownTime();
				frmDownTime.clickRetry();
			}
		}
	}

	public Object doLogin(String username, String password) throws Exception {
		enterUsername(username);
		enterPassword(password);
		return clickLogin();
	}

	public void enterUsername(String username) throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmLoginKA_usernameTextField"));
		AppElement usernameTextField = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_usernameTextField"));
		usernameTextField.type(username);
	}

	public void enterPassword(String password) throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmLoginKA_passwordTextField"));
		AppElement passwordTextField = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_passwordTextField"));
		passwordTextField.type(password);
	}

	public void enableRememberMe() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmLoginKA_flxLogin"));
		AppElement chk = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_flxLogin")).getChildElementsByClassName(AdminConsoleWidgetId.getWidgetId("frmLoginKA_RemembermeClassName")).get(0);
		if(!chk.isChecked())
			chk.click();
	}
	
	public void disableRememberMe() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmLoginKA_flxLogin"));
		AppElement chk = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_flxLogin")).getChildElementsByClassName(AdminConsoleWidgetId.getWidgetId("frmLoginKA_RemembermeClassName")).get(0);
		if(chk.isChecked())
			chk.click();
	}

	public Object clickLogin() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmLoginKA_btnSignin"));
		AppElement btnSignin = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_btnSignin"));
		btnSignin.click();
		return returnNextFrom();
	}
	
	private Object returnNextFrom() throws Exception {
		waitForLoginCompletion();

		if (FrmLoginKA.isFrmLoginKA()) {
			return new FrmLoginKA();

		} else if (FrmDashboardKA.isFormDashboardKA()) {
			return new FrmDashboardKA();

		} else if (FrmChangePassword.isFrmChangePassword()) {
			return new FrmChangePassword();

		}  else if (FrmAccountSuspended.isFrmAccountSuspended()) {
			return new FrmAccountSuspended();

		} else
			return new FrmDownTime();
	}

	public void waitForLoginCompletion() throws Exception {
		int count = 15;
		while (count > 0) {

			if (!AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmLoginKA_loadingWidget")))
				break;
			count--;
			Thread.sleep(1000);
		}
	}
	
	public String getErrorText() throws Exception {
		String ErrText = null;
		AppElement ErrLabel = null;
		
		if(AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmLoginKA_txtError1"))) {
			ErrLabel = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_txtError1"));
		
		}else if(AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmLoginKA_txtErrorOneAttemptLeft"))) {
			ErrLabel = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_txtErrorOneAttemptLeft"));
		}
		
		if(ErrLabel == null)
			return null;
		
		ErrText = ErrLabel.getText();
		return ErrText;
	}
	
	public String getSessionTimeoutMsg() throws Exception {
		AppElement TimeOutLabel = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_lblTimeExpired"));
		String ErrText = TimeOutLabel.getText();
		return ErrText;
	}
	
	public String getUsernameText() throws Exception {
		AppElement UsernameTxt = new AppElement(AdminConsoleWidgetId.getWidgetId("frmLoginKA_usernameTextField"));
		String Text = UsernameTxt.getText();
		return Text;
	}
}
