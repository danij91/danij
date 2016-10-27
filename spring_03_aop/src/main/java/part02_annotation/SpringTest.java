package part02_annotation;
/*
 * AOP(Aspect Oriented Programming)
 * 1. AOP는 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법이다.
 * 2. AOP는 문제를 해결하기 위한 핵심관심사항과 전체에 적용되는 공통관심사항을 기준으로
 *    프로그래밍함으써 공통 모듈을 여러 코드에 쉽게 적용할 수 있도록 해준다.
 */
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("part02_annotation/aop.xml");
		Service svc=(Service) context.getBean("svc");
		svc.prn1();
		svc.prn1(20);
		svc.prn1(1,2);
		svc.prn2();
		svc.prn3();
		//svc.prn4();
		svc.prn5();
	}

}
