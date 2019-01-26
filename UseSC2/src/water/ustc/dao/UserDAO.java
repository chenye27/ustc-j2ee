package water.ustc.dao;


import sc.ustc.dao.Conversation;
import water.ustc.action.UserBean;

public class UserDAO {

	public UserBean query(String userName,boolean ignLazy)throws Exception {
		return Conversation.getUser("userName",userName,ignLazy);
	}

		
}
