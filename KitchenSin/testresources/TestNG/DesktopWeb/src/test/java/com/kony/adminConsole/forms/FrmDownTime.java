package com.kony.adminConsole.forms;

import com.kony.adminConsole.AdminConsoleWidgetId;

import test.common.AppElement;

public class FrmDownTime {

	public FrmDownTime() throws Exception {
		AppElement frmObj = new AppElement(AdminConsoleWidgetId.getWidgetId("frmDownTime_Header"));
	}

	public static boolean isFrmDownTime() throws Exception {
		return AppElement.isElementVisible("id", AdminConsoleWidgetId.getWidgetId("frmDownTime_Header"));
	}
	
	public FrmLoginKA clickRetry() throws Exception {
		AppElement.waitForEnable(AdminConsoleWidgetId.getWidgetId("frmDownTime_btnRetry"));
		AppElement btnRetry = new AppElement(AdminConsoleWidgetId.getWidgetId("frmDownTime_btnRetry"));
		btnRetry.click();
		return new FrmLoginKA();
	}

}
