package sc.ustc.dao;

import java.sql.*;

public abstract class BaseDAO {
	protected  String driver;
	protected  String url;
	protected  String userName;
	protected  String userPassword;
	
	public BaseDAO(String driver,String url,String userName,String userPassword ) {
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public Connection openDBConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			System.out.println("Success loading Driver!");
			if(this.userName.equals("")) {
				//连接的是SQLite数据库
				System.out.println("Connecting SQLite ...");
				con = DriverManager.getConnection(this.url);
			}
			else {
				//连接的是MySQL数据库
				System.out.println("Connecting MySQL ...");
				con = DriverManager.getConnection(
						this.url, this.userName, this.userPassword);
			}
			System.out.println("Success connecting DBMS!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error loading DBMS Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting DBMS!");
			e.printStackTrace();
		}

		return con;
	}
	public boolean closeDBConnection(Connection con) {
		if(con == null) {
			return false;
		}
		else {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}
	public abstract Object query(String sql);
	
	public abstract boolean insert(String sql);
	
	public abstract boolean update(String sql);
	
	public abstract boolean delete(String sql);
	
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
