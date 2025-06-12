package busReserve;

import java.sql.*;

public class DbConnection {
	private static final String url="jdbc:mysql://localhost:3306/busReserve";
	private static final String userName="root";
	private static final	String passWord="2007";
	
	public static Connection getConnection() throws SQLException{
	return DriverManager.getConnection(url,userName,passWord);
	}
}
