package water.ustc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sc.ustc.dao.BaseDAO;
import sc.ustc.dao.Conversation;
import water.ustc.action.UserBean;

public class UserDAO {

	public UserBean query(String userName,boolean ignLazy)throws Exception {
		return (ignLazy)? Conversation.getUserNoLazy("userName",userName)
				:Conversation.getUser("userName",userName);
	}

		
}
