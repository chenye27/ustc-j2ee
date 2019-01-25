package sc.ustc.dao;

import java.lang.annotation.ElementType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.dom4j.Element;


import water.ustc.action.UserBean;

public class Conversation {
	static {
		try {
			Class.forName(Configuration.getDbDriver());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static Connection openDBConnection()throws Exception {
		Connection con = null;
		con = DriverManager.getConnection(Configuration.getDbUrl(),Configuration.getDbName(),Configuration.getDbPwd());
		return con;
	}
	
	public static boolean closeDBConnection(Connection con ,Statement stat,ResultSet rs) {
		try {
			if(stat!=null) stat.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static UserBean getUser(String property, Object value)throws Exception{
		System.out.println("getUser:");
		UserBean  userbean =  null;
		List<Element> properties = null;	
		String table = null;
		String PK = null;
		String field = null;
		String type = null;
		boolean islazy = false;
		Connection con = openDBConnection();
		for(Element ele:Configuration.getClasses()) {
			if(ele.elementText("name").equals("UserBean")){
				table = ele.elementText("table");
				properties = ele.elements("property");
				for(Element p : properties) {
					if(p.elementText("name").equals(property)) {
						PK = p.elementText("column");
					}else if(p.elementText("name").equals("userPass")) {
						field = p.elementText("column");
						type = p.elementText("type");
						islazy = (p.elementText("lazy").equals("true"))?true:false;
					}
					
				}
			}
			break;
		}
		if(table == null) return null;
		System.out.println(table);
		String sql = "select "+PK+((islazy)?"":","+field) + " from " +table+" where " +PK+"='"+(String)value+"'";
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		int count =0;
		String userId = null;
		String userName = null;
		String password = null;
		while(rs.next()) {
			userId = rs.getString("id");
			userName = rs.getString("username");
			password = rs.getString("password");
			System.out.println(userId+"  "+userName+"   "+password);
			count++;
		}
		if(count == 1) {
			//若找到用户则为userBean对象初始化赋值
			userbean = new UserBean(userId, userName, password);
		}
		closeDBConnection(con, stat, rs);
		
		return userbean;
	}
	
	public static UserBean getUserNoLazy(String property, Object value) {
		return null;
	}
}
