package water.ustc.interceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;





public class LogInterceptor {

	private Element crtEle;
	private Document dc;
	private String logXml;
	private String name;
	private String s_time;
	public LogInterceptor() throws DocumentException{
		logXml = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"log.xml";
		System.out.println(logXml);
		dc = (new SAXReader()).read(new File(logXml));
		crtEle = dc.getRootElement().addElement("log");
	} 
	public  void preAction(String recordStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.name = recordStr;
		this.s_time = sdf.format(new Date());
		
	}
	
	public  void afterAction(String result)throws IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		crtEle.addElement("name",this.name);
		crtEle.addElement("s-time",this.s_time);
		crtEle.addElement("e-time",sdf.format(new Date()));
		crtEle.addElement("result",result);
		System.out.println("    "+crtEle.elements());
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
//		Writer out = new FileWriter(this.logXml);
		OutputStream out = new FileOutputStream(this.logXml);
		XMLWriter  writer = new XMLWriter(out, format);
		System.out.println(dc);
		writer.write(dc);
		writer.close();
		System.out.println("写入logxml完成");
	}
	
}
