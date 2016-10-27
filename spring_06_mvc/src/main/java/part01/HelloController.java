package part01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//http://127.0.0.1:8080/springmvc/hello.htm

@Controller
public class HelloController {
	
	@RequestMapping("/hello.htm")
		public String search(){//url로 들어갔을 때 실제 컨트롤로 실행이 될 메서드 //hello.htm=> search 메서드 실행됨
		return "view/part01/hello"; //응답해 줄 페이지를 리턴
		
		//view/part01/hello 앞 뒤 주소는 dispatcher-one.xml에서 설정해준다
	}
}//end class
