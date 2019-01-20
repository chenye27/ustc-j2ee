package water.ustc.action;

public class LoginAction {

	public  String handlelogin(String name,String pwd) {
		// TODO Auto-generated method stub
		if(name.equals("world")&&pwd.equals("abc")) {
			return new String("success");
		}else  return new String("failure"); 
		
	}

}
