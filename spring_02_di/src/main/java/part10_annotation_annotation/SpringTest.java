package part10_annotation_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context= new AnnotationConfigApplicationContext(MainConfig.class);
		StringBuffer sb=(StringBuffer)context.getBean("sb");
		System.out.println(sb.reverse());
		
	}

}
