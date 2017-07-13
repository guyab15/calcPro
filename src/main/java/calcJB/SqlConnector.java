package calcJB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

public class SqlConnector {
public static void main(String[] args) {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/calc","root","");
		System.out.println("good connection");
		stmt = con.createStatement();
		//rs = stmt.executeQuery("INSERT INTO Actions values(,'15:55','5+5-3','13')");
		//stmt.executeUpdate("INSERT INTO Actions values('5','15:55','5+5-3','13')");
	
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		
	}
	 
	
	
	
}
	
	
}
