package part06_annotation;
import org.springframework.context.annotation.Bean;
//자바코드에서 어노테이션을 이용한 스프링 환경설정
//현재 클래스(config)를 스프링 환경설정으로 사용함을 의미한다.
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
	public config() {
		// TODO Auto-generated constructor stub
	}
	
	public User user(){
		return new User("kim","a1234");
	}
	
	@Bean
	public ServiceImp svc(){
		return new ServiceImp(user());
	}
	
	@Bean(name="ss")
	public ServiceImp serviceImp(){
		return new ServiceImp(user());
	}
}//end class
