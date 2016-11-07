package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.PersonDao;
import dto.PersonDTO;
/*
GET(/rest/person/list)     		자료의 조회용
GET(/rest/person/list/3)   		자료의 조회용
DELETE(/rest/person/3)     		자료의 삭제
POST(/rest/person/)+데이터    		자료의 등록
PUT(/rest/person/update)+데이터 	자료의 수정  

@PathVariable-URI의 경로에서 원하는 데이터를 추출하는 용도로 사용
@RequestBody - 전송된 JSON데이터를 객체로 변환해 주는 어노테이션으로 
     @ModeAndView와 유사한 역할을 하지만 JSON에서 사용된다는 점이 차이
*/

@Controller
public class PersonController {
	private PersonDao dao;

	public PersonController() {
		// TODO Auto-generated constructor stub
	}

	public void setDao(PersonDao dao) {
		this.dao = dao;
	}

	// http://127.0.0.1:8090/rest/person/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<PersonDTO> listMethod() {
		return dao.list();
	}// end listMethod()

	// http://127.0.0.1:8090/rest/person/list/2
	@RequestMapping(value = "list/{ss}", method = RequestMethod.GET)
	public @ResponseBody PersonDTO listMethod(@PathVariable("ss") int num) {
		return dao.list(num);
	}// end listMethod()

	// http://127.0.0.1:8090/rest/person/list/2/이영희
	/*
	 * @RequestMapping(value = "list/{num}/{name}", method = RequestMethod.GET)
	 * public @ResponseBody PersonDTO listMethod(@PathVariable("num") int
	 * num, @PathVariable("name") String name) {
	 * 
	 * PersonDTO dto=new PersonDTO(); dto.setName(name); dto.setNum(num); return
	 * dao.list(dto); }// end listMethod()
	 */
	/*@RequestMapping(value = "list/{num}/{name}", method = RequestMethod.GET)
	public @ResponseBody PersonDTO listMethod(PersonDTO dto) {
		return dao.list(dto);
	}// end listMethod()
*/
	@RequestMapping(value = "list/{num}/{name}", method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> listMethod(PersonDTO dto) {
		ResponseEntity<PersonDTO> entity = new ResponseEntity<PersonDTO>(dao.list(dto), HttpStatus.OK);
		return entity;
	}// end listMethod()
	
	/*
	 * $.ajax({
	 * 			dataType:'text',
	 * 			type:'POST',
	 * 			data:JSON.stringify({"id":"siri","pass":"4253","name":"최순실"}),
	 * 			url:'/'
	 * });
	 */
	//http://127.0.0.1:8090/rest/person/
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> insertMethod(@RequestBody PersonDTO dto) {
		ResponseEntity<String> entity = null;

		try {
			dao.register(dto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;

	}// end insertMethod()

	/*
	 * @RequestMapping(value = "insert/{id}/{name}/{pass}", method =
	 * RequestMethod.GET) public @ResponseBody void insertMethod(PersonDTO dto)
	 * { dao.register(dto); }// end insertMethod()
	 */
	//http://127.0.0.1:8090/rest/person/update
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updateMethod(@RequestBody PersonDTO dto) {
		ResponseEntity<String> entity = null;

		try {
			dao.update(dto);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;

	}// end updateMethod()
	
	//http://127.0.0.1:8090/rest/person/1
	@RequestMapping(value = "/{num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteMethod(@PathVariable("num") int num) {
		ResponseEntity<String> entity = null;

		try {
			dao.delete(num);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;

	}// end deleteMethod()
}// end class
