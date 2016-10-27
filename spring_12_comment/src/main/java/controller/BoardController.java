package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.ReplyDTO;
import service.BoardService;

@Controller
public class BoardController {

	private BoardService service;

	public BoardController() {

	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/board.do")
	public ModelAndView boardListPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.boardListProcess());
		mav.setViewName("boardList");
		return mav; 
	}// end boardListPage();
	@RequestMapping("/boardView.do")
	public ModelAndView boardViewPage(int bno){
		ModelAndView mav= new ModelAndView();
		mav.addObject("boardDTO",service.boardViewProcess(bno));
		mav.setViewName("boardView");
		return mav;
	}
	
	@RequestMapping("/replyInsertList.do")
	public @ResponseBody List<ReplyDTO> replyListPage(ReplyDTO rdto){
		return service.replyListProcess(rdto);
	}
	
	@RequestMapping("/replyDelete.do")
	public @ResponseBody List<ReplyDTO> replyDeleteListPage(ReplyDTO rdto){
		return service.replyDeleteProcess(rdto);
	}
	
	@RequestMapping("/replyUpdate.do")
	public @ResponseBody List<ReplyDTO> replyUpdateListPage(ReplyDTO rdto){
		return service.replyUpdateProcess(rdto);
	}
}// end class
