package part02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//HelloModelController : xml에 등록시켜야함
@Controller
public class HelloModelController {
	@RequestMapping("/view/part02/helloModel.htm")
	public Model search(){
		Model model = new ExtendedModelMap();
		model.addAttribute("id","korea");
		//ModelAndView 에서 일단 모델값만 리턴한다.
		return model;
	}
}//end class
