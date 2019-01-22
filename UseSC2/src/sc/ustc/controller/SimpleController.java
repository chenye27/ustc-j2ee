package sc.ustc.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import water.ustc.action.LoginActionHandler;
import water.ustc.action.LoginInterface;


public class SimpleController extends HttpServlet {
	private static final long SerialVersionUID = 1L;
	private List<Element> actions = null;
	private List<Element> itcpts = null;
	
	@Override
	public void init(){
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("controller.xml");
			Document dc = (new SAXReader()).read(is);
			this.itcpts = dc.getRootElement().elements("interceptor");
			this.actions = dc.getRootElement().element("controller").elements();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("初始化读取配置文件完成");
	}
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
			ServletContext sc = getServletContext();
			response.setContentType("text/html;charset=utf-8");
			String actstr = request.getRequestURL().toString();
			String actname = actstr.substring(actstr.lastIndexOf("/")+1,actstr.lastIndexOf(".sc")).toString();
//			File f = new File("/Users/baoshun/eclipse-workspace/UseSC2/src/controller.xml");
//          InputStream is = new FileInputStream(f);
//			InputStream is = this.getClass().getClassLoader().getResourceAsStream("controller.xml");		
			try {
//				Document dc = (new SAXReader()).read(is);
//				List<Element> actions = dc.getRootElement().element("controller").elements();
				String rstName = null;
				for(Element ele: actions) {
					if(actname.equals(ele.attribute("name").getValue())) {
						String className = ele.attribute("class").getStringValue();
						String classMethod = ele.attribute("method").getStringValue();
						String username = request.getParameter("name");
						String pwd = request.getParameter("password");
						String itcptr = ele.elementText("interception-ref");
						System.out.println(itcptr);
						Class<?> cls = null;
						Method m = null;
						if(itcptr!=null) {
							String itcptCls = null;
							String itcptMtd1 = null;
							String itcptMtd2 = null;
							for(Element itcpt: itcpts){
								if(itcpt.attribute("name").getValue().equals(itcptr)) {
									itcptCls = itcpt.attribute("class").getValue();
									itcptMtd1 = itcpt.attribute("predo").getValue();
									itcptMtd2 = itcpt.attribute("afterdo").getValue();
									
									cls = Class.forName(className);
									System.out.println("cls1"+cls);
									Object actobj = (LoginInterface)cls.newInstance();
									System.out.println(actobj);
									LoginActionHandler handler = new LoginActionHandler(actobj, actname, itcptCls, itcptMtd1,itcptMtd2);
									Object proxy = Proxy.newProxyInstance(actobj.getClass().getClassLoader(), actobj.getClass().getInterfaces(), handler);
									cls = proxy.getClass();
									System.out.println("cls2"+cls);
									m = cls.getDeclaredMethod(classMethod, new Class[] {String.class,String.class});
									System.out.println(m);
									rstName = (String)m.invoke(proxy, new Object[] {username,pwd});
									System.out.println(rstName);
									break;
								}
							}
							
						}else {
							cls =  Class.forName(className);
							m = cls.getDeclaredMethod(classMethod, new Class[]{String.class,String.class});
							rstName =(String) m.invoke(cls.newInstance(), request.getParameter("name"),request.getParameter("password"));
						}
						List<Element> rst = ele.elements("result");
						for(Element rstEle:rst) {
							System.out.println(rstEle);
							if(rstName.equals(rstEle.attribute("name").getStringValue())) {
								String rstValue = rstEle.attribute("value").getStringValue();
								if(rstEle.attribute("type").equals("forword")) {
									sc.getRequestDispatcher(rstValue).forward(request, response);
								}else response.sendRedirect(rstValue);
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException {
		doPost(request, response);
	
	}
	
	
}
