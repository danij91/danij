package part05;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemInsertController {
	private MemDAO dao;

	public void setDao(MemDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="/memInsert.htm",method=RequestMethod.GET)
	public String form(){
		return "view/part05/memForm";
	}
	@RequestMapping(value="/memInsert.htm",method=RequestMethod.POST)
	public String submit(MemDTO dto){
		dao.insertMethod(dto);
		return "redirect:/memList.htm";
	}
}
