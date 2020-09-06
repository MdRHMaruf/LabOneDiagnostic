package com.ShareClass; 
import java.sql.*;
import javax.swing.*;
public class db_coonection {
	public String user = "sa";
	public String password = "Cursor777";
	//public String url = "jdbc:sqlserver://192.168.0.230:1433;databaseName =LabOneHaathazari";
	public String url = "jdbc:sqlserver://localhost;databaseName =LabOneHaathazari";
	//public String url = "jdbc:sqlserver://ONEDIAGSERVER:1433;databaseName =LabOneHaathazari";
	public Connection con = null;
	public Statement sta = null;
	public Statement conect()
	{
		try
		{
			//System.setProperty("java.net.preferIPv6Addresses", "true"); 
			con = DriverManager.getConnection(url,user,password);
			sta=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println("YesHI Sucess");
		}
		catch (SQLException i)
		{
			i.printStackTrace();
			JOptionPane.showMessageDialog(null, i);
		}
		return sta;
	}


}