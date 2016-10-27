package part03_exam;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("part03_exam/aop.xml");
		Service svc=(Service)context.getBean("svc");
		svc.prn();
	}

}
