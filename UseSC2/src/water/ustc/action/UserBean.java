package water.ustc.action;

import water.ustc.dao.UserDAO;

public class UserBean {
	private UserDAO userdao;
	private String userId;
	private String userName;
	private String userPass;
	
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/2333";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "bhpw4065";
	
	public UserBean(String userId,String userName,String userPass) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public boolean signIn() {
		UserDAO  userdao = new UserDAO();
		try{
			if(userPass.equals((userdao.query(userName, false)).getUserPass())) return true;
			else return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
