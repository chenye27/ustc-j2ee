package sc.ustc.dao;
import java.io.File;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class Configuration {
	private static List<Element> classes = null;
	private static String dbDriver = null;
	private static String dbUrl = null;
	private static String dbName = null;
	private static String dbPwd = null;
	
	static {
		try {
			String file = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"or_mapping.xml";
			Document dc = (new SAXReader()).read(new File(file));
			List<Element> jdbc = dc.getRootElement().element("jdbc").elements("property");
			classes = dc.getRootElement().elements("class");
			
			for(Element ele : jdbc){
				String name = ele.elementText("name");
				String value = ele.elementText("value");
				if(name.equals("driver_class")) {
					dbDriver = value;
				}else if(name.equals("url_path")) {
					dbUrl = value;
				}else if(name.equals("db_username")) {
					dbName = value;
				}else if(name.equals("db_userpassword")) {
					dbPwd = value;
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Element> getClasses() {
		return classes;
	}

	public static String getDbDriver() {
		return dbDriver;
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static String getDbName() {
		return dbName;
	}

	public static String getDbPwd() {
		return dbPwd;
	}

	public static void setClasses(List<Element> classes) {
		Configuration.classes = classes;
	}

	public static void setDbDriver(String dbDriver) {
		Configuration.dbDriver = dbDriver;
	}

	public static void setDbUrl(String dbUrl) {
		Configuration.dbUrl = dbUrl;
	}

	public static void setDbName(String dbName) {
		Configuration.dbName = dbName;
	}

	public static void setDbPwd(String dbPwd) {
		Configuration.dbPwd = dbPwd;
	}
	
}
