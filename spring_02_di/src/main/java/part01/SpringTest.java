package part01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	/*
	 * 컨테이너(container)
	 * 1 BeanFactory getBean()
	 * 2 ApplicationContext  container가 생성될때
	 * 3 WebApplicationContext  was(tomcat)가 생성될떄
	 */
	public static void main(String[] args) {
		/*
		 * 환경설정(part01/di.xml) 에 선언된 빈들을 생성하고 관리해주는 객체를 container라 한다.
		 * 현재 사용되고 있는 container은 ApplicationContext이다.
		 */
		ApplicationContext context=new ClassPathXmlApplicationContext("part01/di.xml");
		/*Service svc=(Service)context.getBean("svc");
		svc.prn();*/
	}

}
