package part04_ajax_daum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchDaumController {
	@RequestMapping("/daumForm.do")
	public String form(){
		return "part04_ajax_daum/daumForm";
	}
	
	/*@RequestMapping("/searchOpen.do")
	public ModelAndView process(String search) throws MalformedURLException, IOException{
		ModelAndView mav=new ModelAndView();
		String url="https://apis.daum.net/search/book?apikey=8a7cf97a377368fd5f2b521a2cbe6972&q="+URLEncoder.encode(search,"UTF-8")+"&output=xml";
		HttpURLConnection con=(HttpURLConnection)new URL(url).openConnection();
		BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String input=null;
		String total="";
		while((input=reader.readLine())!=null){
			total+=input;
		}
		mav.addObject("total",total);
		mav.setViewName("part04_ajax_daum/daumResult");
		return mav;
	}*/
	@RequestMapping("/searchOpen.do")
	public @ResponseBody BookList process(String search) throws MalformedURLException, IOException, JAXBException{
		String url="https://apis.daum.net/search/book?apikey=8a7cf97a377368fd5f2b521a2cbe6972&q="+URLEncoder.encode(search,"UTF-8")+"&output=xml";
		HttpURLConnection con=(HttpURLConnection)new URL(url).openConnection();
		
		JAXBContext jaxbContext=null;
		BookList booklist=null;
		jaxbContext=JAXBContext.newInstance(BookList.class);
		booklist=(BookList)jaxbContext.createUnmarshaller().unmarshal(con.getInputStream());
		return booklist;
	}
}

