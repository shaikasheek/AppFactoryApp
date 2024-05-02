package com.kony.adminConsole.forms;

import test.common.SgConfiguration;

public class AppSpecificFunctions {

	static SgConfiguration sg = SgConfiguration.getInstance();
	
	public static void waitForClientSessionTimeout() throws Exception {
		int TimeToSleep = (Integer.parseInt(sg.getKeyValue("clientSessionTimeOut")) * 60 * 1000)+1;
		Thread.sleep(TimeToSleep);
	}

	public static Object login() throws Exception {
		FrmLoginKA frmLoginKA = new FrmLoginKA();
		return frmLoginKA.doLogin(sg.getKeyValue("username"), sg.getKeyValue("password"));
		
	}
}
