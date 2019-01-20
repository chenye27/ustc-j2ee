package sc.ustc.controller;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Document dc = (new SAXReader()).read("/src/controller.xml");
			List<Element> actions = dc.getRootElement().elements();
			System.out.println(actions);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
