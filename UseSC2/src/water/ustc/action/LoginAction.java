package water.ustc.action;

public class LoginAction implements LoginInterface {

	@Override
	public  String handlelogin(String name,String pwd) {
		// TODO Auto-generated method stub
		UserBean userbean = new UserBean(null,name,pwd);
		boolean flag = userbean.signIn();
		if(flag==true) {
			return new String("success");
		}else  return new String("failure"); 
		
	}
}
