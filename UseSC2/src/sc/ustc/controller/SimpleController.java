package sc.ustc.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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


public class SimpleController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
			ServletContext sc = getServletContext();
			response.setContentType("text/html;charset=utf-8");
			String actstr = request.getRequestURL().toString();
			String actname = actstr.substring(actstr.lastIndexOf("/")+1,actstr.lastIndexOf(".sc")).toString();
//			File f = new File("/Users/baoshun/eclipse-workspace/UseSC2/src/controller.xml");
//          InputStream is = new FileInputStream(f);
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("controller.xml");		
			try {
				Document dc = (new SAXReader()).read(is);
				List<Element> actions = dc.getRootElement().element("controller").elements();	
				for(Element ele: actions) {
					if(actname.equals(ele.attribute("name").getValue())) {
						String className = ele.attribute("class").getStringValue();
						String classMethod = ele.attribute("method").getStringValue();
						Class cls =  Class.forName(className);
						Method m = cls.getDeclaredMethod(classMethod, new Class[]{String.class,String.class});
						String rstName =(String) m.invoke(cls.newInstance(), request.getParameter("name"),request.getParameter("password"));
						List<Element> rst = ele.elements();
						for(Element rstEle:rst) {
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
