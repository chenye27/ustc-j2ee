package sc.ustc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException,IOException{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<HTML>");
			out.println("<HEAD><TITLE>SimpleController!</TITLE></HEAD>");
			out.println("<BODY>");
			out.println("欢迎使用SimpleController!");
		    out.println("</BODY>");
		    out.println("</HTML>");
			out.flush();
			out.close();
			
	}
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException {
		doPost(request, response);
	
	}
	
	
}
