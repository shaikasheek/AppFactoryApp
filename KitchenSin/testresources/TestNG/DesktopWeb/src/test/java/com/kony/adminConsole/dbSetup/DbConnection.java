package com.kony.adminConsole.dbSetup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;

import test.common.SgConfiguration;

public class DbConnection {
	SgConfiguration sg = SgConfiguration.getInstance();

	String dbURL = sg.getKeyValue("dbURL");
	String dbusername = sg.getKeyValue("dbusername");
	String dbpassword = sg.getKeyValue("dbpassword");
	String schemaName = sg.getKeyValue("schemaName");
	String tableName = sg.getKeyValue("tableName");
	
	Connection dbCon = null;

	public DbConnection() throws SQLException {
		dbCon = DriverManager.getConnection(dbURL, dbusername, dbpassword);
	}

	public void resetData() throws SQLException {

		String ScriptFilePath = "AdminConsole.sql";
		try {
			ScriptRunner sr = new ScriptRunner(dbCon, false, false);
			Reader reader = new BufferedReader(new FileReader(ScriptFilePath));
			sr.runScript(reader);

		} catch (Exception e) {
			System.err.println("Failed to Execute" + ScriptFilePath + " The error is " + e.getMessage());
		} finally {
			dbCon.close();
		}

	}
	public void resetPassword() throws SQLException {

		try {
			String query = "UPDATE `" + schemaName + "`.`" + tableName
					+ "` SET Password='$2a$11$0qKljBwr86lxCMd.FsRKM.pDQwI021FGs5zO.aS8ljM8Ft5Ym.ZY2', PasswordSalt='$2a$11$0qKljBwr86lxCMd.FsRKM.' WHERE id='AutoAdmin1'";
			Statement stmt = dbCon.createStatement();
			stmt.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println("Failed to update password" + e.getMessage());
		} finally {
			dbCon.close();
		}

	}
	
	

}
