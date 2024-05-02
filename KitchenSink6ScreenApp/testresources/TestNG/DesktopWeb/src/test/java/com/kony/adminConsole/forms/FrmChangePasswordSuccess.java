package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmChangePasswordSuccess {
	
	public FrmChangePasswordSuccess() throws Exception {
		AppElement frmObj = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePasswordSuccess_Header"));
	}

	public static boolean isFrmChangePasswordSuccess() throws Exception {
		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmChangePasswordSuccess_Header"));
	}
	
	public FrmLoginKA clickReLogin() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmChangePasswordSuccess_btnRelogin"));
		AppElement btnReLogin = new AppElement(AdminConsoleWidgetId.getWidgetId("frmChangePasswordSuccess_btnRelogin"));
		btnReLogin.click();
		return new FrmLoginKA();
	}
}
