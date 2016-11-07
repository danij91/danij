package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardServiceImp implements BoardService {

	private BoardDao dao;

	public BoardServiceImp() {
		
	}

	public void setDao(BoardDao dao) {
		this.dao = dao;
	}

	@Override
	public List<BoardDTO> boardListProcess() {
		return dao.boardListMethod();
	}

	@Override
	public BoardDTO boardViewProcess(int bno) {
		// TODO Auto-generated method stub
		return dao.boardViewMethod(bno);
	}

	@Override
	public List<ReplyDTO> replyListProcess(ReplyDTO rdto) {
		dao.replyInsertMethod(rdto);
		return dao.replyListMethod(rdto.getBno());
	}

	@Override
	public List<ReplyDTO> replyDeleteProcess(ReplyDTO rdto,HttpServletRequest request) {
		String upload=dao.getFile(rdto.getRno());
		if (upload != null) {
			String root = request.getSession().getServletContext().getRealPath("/");
			String saveDirectory = root + "temp" + File.separator;
			File fe = new File(saveDirectory, upload);
			fe.delete();
		}
		dao.replyDeleteMethod(rdto.getRno());
		return dao.replyListMethod(rdto.getBno());
	}

	@Override
	public List<ReplyDTO> replyUpdateProcess(ReplyDTO rdto,HttpServletRequest request) {
		String filename = dao.getFile(rdto.getRno());
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "temp" + File.separator;
		MultipartFile file = rdto.getFilename();
		// 수정할 첨부파일이 있으면
		if (file!=null&&!file.isEmpty()) {
			// 중복방지용 난수발생
			UUID random = UUID.randomUUID();
			// 기존 첨부파일이 있으면...
			if (filename != null) {
				File fe = new File(saveDirectory, filename);
				fe.delete();
			}
			String fileName = file.getOriginalFilename();
			rdto.setRupload(random + "_" + fileName);
			File ff = new File(saveDirectory, random + "_" + fileName);
			try {
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dao.replyUpdateMethod(rdto);
		return dao.replyListMethod(rdto.getBno());
	}


}
