package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmDashboardKA {
	
	public FrmDashboardKA() throws Exception {
		AppElement frmObj = new AppElement(AdminConsoleWidgetId.getWidgetId("frmDashboard_Header"));
	}

	public static boolean isFormDashboardKA() throws Exception {

		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmDashboard_Header"));
	}

	public static void navigateFromLoginForm() throws Exception {
		if(!isFormDashboardKA()) {
			if (FrmLoginKA.isFrmLoginKA()) {
				AppSpecificFunctions.login();

			} else if (FrmChangePassword.isFrmChangePassword()) {
				FrmChangePassword frmUpdatePassword = new FrmChangePassword();
				Object frmObj = frmUpdatePassword.clickCancel();
				if(frmObj.getClass().equals(FrmLoginKA.class)) {
					AppSpecificFunctions.login();
				}
				
			} else if (FrmDownTime.isFrmDownTime()) {
				FrmDownTime frmDownTime = new FrmDownTime();
				frmDownTime.clickRetry();
				AppSpecificFunctions.login();
			}
		}
	}
	
	public FrmLoginKA clickLogout() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmDashboard_btnLogOut"));
		AppElement btnLogout = new AppElement(AdminConsoleWidgetId.getWidgetId("frmDashboard_btnLogOut"));
		btnLogout.click();
		return new FrmLoginKA();
	}

	public FrmChangePassword clickUpdatePassword() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmDashboard_btnChangePassword"));
		AppElement btnUpdatePwd = new AppElement(AdminConsoleWidgetId.getWidgetId("frmDashboard_btnChangePassword"));
		btnUpdatePwd.click();
		return new FrmChangePassword();
	}
	
}
