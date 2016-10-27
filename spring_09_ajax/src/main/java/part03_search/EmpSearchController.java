package part03_search;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmpSearchController {
	private EmpDao dao;
	
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}
	@RequestMapping("/search.do")
	public String searchForm(){
		return "part03_search/searchForm";
	}
	
	@RequestMapping("/process.do")
	public @ResponseBody List<EmployeeDTO> searchProcess(String data){
		return dao.search(data); 
	}
}
