package database;

import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
	public static Connection con = null;
	private static Database instance = new Database();
	public static Database getInStance() {
		return instance;
	}
	public static void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=HKVTravel;";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void disconnect() {
		if(con!=null)
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	public Connection getConnection() {
		connect();
		return con;
	}
//	public static void main(String[] args) {
//		connect();
//		if(con != null)
//			System.out.println("Success");
//		disconnect();
//	}
	
}
