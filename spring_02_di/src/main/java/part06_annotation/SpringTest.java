package part06_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new AnnotationConfigApplicationContext(config.class);
		Service svc=(Service)context.getBean("svc");
		svc=(Service)context.getBean("ss");
		svc.prn();
		/*config config=new config();
		config.svc().prn();*/
	}

}
