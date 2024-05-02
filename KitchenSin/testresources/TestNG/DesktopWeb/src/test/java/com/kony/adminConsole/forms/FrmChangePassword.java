package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmChangePassword {
	
	public FrmChangePassword() throws Exception {
		AppElement frmObj = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_Header"));
	}

	public static boolean isFrmChangePassword() throws Exception {

		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmChangePassword_Header"));
	}
	
	public static void navigateFromLoginForm() throws Exception {
		if(!isFrmChangePassword()) {
			if (FrmLoginKA.isFrmLoginKA()) {
				Object frmObj = AppSpecificFunctions.login();
				if(frmObj.getClass().equals(FrmDashboardKA.class)) {
					FrmDashboardKA dashboardKA = (FrmDashboardKA)frmObj;
					dashboardKA.clickUpdatePassword();
				}

			} else if (FrmDashboardKA.isFormDashboardKA()) {
				FrmDashboardKA dashboardKA = new FrmDashboardKA();
				dashboardKA.clickUpdatePassword();
			} 
		}
	}
	
	public Object clickCancel() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePassword_btnCancel"));
		AppElement btnCancel = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_btnCancel"));
		btnCancel.click();
		return returnNextFrom();
	}
	
	private Object returnNextFrom() throws Exception {
		waitForLoadingScreen();
		
		if (FrmChangePassword.isFrmChangePassword()) {
			return new FrmChangePassword();

		}else if (FrmChangePasswordSuccess.isFrmChangePasswordSuccess()) {
			return new FrmChangePasswordSuccess();

		}else if (FrmLoginKA.isFrmLoginKA()) {
			return new FrmLoginKA();

		}else {
			return new FrmDashboardKA();
		} 
	}
	
	public void enterPreviousPwd(String PrevoiusPwd) throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtCurrentPassword"));
		AppElement txtCurrentPwd = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtCurrentPassword"));
		txtCurrentPwd.type(PrevoiusPwd);
	}
	
	public void enterNewPwd(String newPwd) throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtNewPassword"));
		AppElement txtNewPwd = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtNewPassword"));
		txtNewPwd.type(newPwd);
	//	txtNewPwd.pressEnter();
		
	}
	
	public void enterConfirmPwd(String confirmPwd) throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtReenterPassword"));
		AppElement txtConfirmPwd = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_txtReenterPassword"));
		txtConfirmPwd.type(confirmPwd);
	//	txtConfirmPwd.pressEnter();
	}
	
	public Object clickSubmit() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePassword_btnResetPassword"));
		AppElement btnSubmit = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_btnResetPassword"));
		btnSubmit.click();
		return returnNextFrom();
	}
	
	public String getIncorrectPwdErr() throws Exception {
		AppElement ErrLbl = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_InvalidPwdLbl"));
		String Text = ErrLbl.getText();
		return Text;
	}

	public String getIncorrectConfirmPwdErr() throws Exception {
		AppElement ErrLbl = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePassword_InvalidConfirmPwdLbl"));
		String Text = ErrLbl.getText();
		return Text;
	}
	
	public void waitForLoadingScreen() throws Exception {
		int count = 15;
		while (count > 0) {

			if (!AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmChangePassword_loadingWidget")))
				break;
			count--;
			Thread.sleep(1000);
		}
	}
}
