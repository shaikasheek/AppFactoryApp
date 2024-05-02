package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmAccountSuspended {
	public FrmAccountSuspended() throws Exception {
		AppElement frmObj = new AppElement(AdminConsoleWidgetId.getWidgetId("frmAccountSuspended_Header"));
	}

	public static boolean isFrmAccountSuspended() throws Exception {
		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmAccountSuspended_Header"));
	}
	
	public String getErrMessage() throws Exception {
		return new AppElement("id", AdminConsoleWidgetId.getWidgetId("frmAccountSuspended_Msglbl")).getText();
	}
	
	public String getContactNumber() throws Exception {
		return new AppElement("id", AdminConsoleWidgetId.getWidgetId("frmAccountSuspended_ContactNumber")).getText();
	}
	
	public FrmLoginKA clickBack() throws Exception {
		AppElement.deviceBack();
		return new FrmLoginKA();
	}
}
