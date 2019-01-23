package water.ustc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sc.ustc.dao.BaseDAO;
import water.ustc.action.UserBean;

public class UserDAO extends BaseDAO {

	public UserDAO(String driver,String url,String userName,String userPassword) {
		super(driver,url,userName,userPassword);
	}
	
	@Override
	public Object query(String sql) {
		// TODO Auto-generated method stub
		System.out.println("Call UserDAO.query ...");
		System.out.println("sql:" + sql);
		// TODO Auto-generated method stub
		UserBean userBean = null;
		String userId = "";
		String userName = "";
		String password = "";
		int count = 0;
		try {
			//建立数据库链接
			Connection c = openDBConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				userId = rs.getString("id");
				userName = rs.getString("username");
				password = rs.getString("password");
				count++;
			}
			if(count == 1) {
				//若找到用户则为userBean对象初始化赋值
				userBean = new UserBean(userId, userName, password);
			}
			rs.close();
			st.close();
			//关闭数据库链接
			closeDBConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error executing sql.");
			e.printStackTrace();
		}
		return userBean;

	}

	@Override
	public boolean insert(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String sql) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
