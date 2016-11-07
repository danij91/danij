package view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import dao.BoardDao;
//다운로드창을 띄우기 위한 뷰페이지
public class BoardDownLoadView extends AbstractView{
	private BoardDao dao;
	
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	public BoardDownLoadView() {
	}
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {		
		int rno=Integer.parseInt(req.getParameter("rno"));
		//System.out.println(num);
		//System.out.println(model.get("num"));
		String root=req.getSession().getServletContext().getRealPath("/");
		String saveDirectory=root+"temp"+File.separator;
		String upload=dao.getFile(rno);
		String fileName= upload.substring(upload.indexOf("_")+1);
		//파일명이 한글일때 인코딩 작업을 한다.
		String str=URLEncoder.encode(fileName,"UTF-8");
		//원본파일명에서 공백있을떄 +로 표시가 되므로 공백으로 다시 처리해줌
		str=str.replaceAll("\\+", "%20");
		
		//컨텐트 타입
		resp.setContentType("application/octet-stream");
		//다운로드창에 보여줄 파일명 지정
		resp.setHeader("Content-Disposition", "attachment;filename="+str+";");
		//서버에 저장된 파일을 읽어와 클라이언트에 출력해준다.
		FileCopyUtils.copy(new FileInputStream(new File(saveDirectory,upload)), resp.getOutputStream());
		
	}//end renderMergedOutputModel()
	
}//end class
