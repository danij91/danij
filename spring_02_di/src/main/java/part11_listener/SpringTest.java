package part11_listener;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("part11_listener/di.xml");
		Service svc=(Service)context.getBean("svc");
		svc.prn();
		context.destroy();
	}

}
