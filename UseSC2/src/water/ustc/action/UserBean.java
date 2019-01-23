package water.ustc.action;

import water.ustc.dao.UserDAO;

public class UserBean {
	private UserDAO userdao;
	private String userId;
	private String userName;
	private String userPass;
	
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/2333";
	private static final String MYSQL_USERNAME = "baoshun";
	private static final String MYSQL_PASSWORD = "12345678";
	
	public UserBean(String userId,String userName,String userPass) {
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public boolean signIn() {
		boolean flag = false;
		String sql = "select * from user "+"where username='"+userName+"'";
		UserBean userBean = (UserBean) new UserDAO(
				MYSQL_DRIVER, MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD).query(sql);
		if(userBean == null) {
			//用户不存在
			return false;
		}
		else {
			if(userBean.getUserPass().equals(userPass)) {
				return true;
			}
			else {
				//密码错误
				return false;
			}
		}
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
