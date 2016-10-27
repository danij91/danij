package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressController {
	
	@RequestMapping("/address.do")
	public String form(){
		return "address";
	}//end form()
}//end class
