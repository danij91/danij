package dao;

import java.util.List;

import dto.BoardDTO;
import dto.ReplyDTO;

public interface BoardDao {
	public List<BoardDTO> boardListMethod();	
	public BoardDTO boardViewMethod(int bno);
	public void replyInsertMethod(ReplyDTO rdto);
	public List<ReplyDTO> replyListMethod(int bno);
	public void replyDeleteMethod(int rno);
	public void replyUpdateMethod(ReplyDTO rdto);
	public String getFile(int rno);
}// end class
