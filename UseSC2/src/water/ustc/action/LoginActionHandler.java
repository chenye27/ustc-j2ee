package water.ustc.action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoginActionHandler implements InvocationHandler {

	private Class<?> act;
	private Method actM1;
	private Method actM2;
	private Object target;
	private String action;
	private Object ob;
	
	public  LoginActionHandler(Object target,String action,String actionName,String actionMethod1,String actionMethod2)throws Exception {
		// TODO Auto-generated constructor stub
		this.action = action;
		this.target = target;
		this.act = Class.forName(actionName);
		this.actM1 = act.getDeclaredMethod(actionMethod1, String.class);
		this.actM2 = act.getDeclaredMethod(actionMethod2, String.class);
		this.ob = act.newInstance();
	}
	@Override
	public Object invoke(Object proxy,Method method,Object[] args)throws Throwable {
		actM1.invoke(ob,action);
		String result = (String)method.invoke(target, args);
		actM2.invoke(ob,result);
		return result;
		
	}

	
}
