package part04_daoSupport;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("part04_daoSupport/jdbc.xml");
		
		MemDao dao = (MemDao) context.getBean("dao");
		//dao.insertMethod(new MemDTO("박나리",40,"서울"));
		//dao.updateMethod(new MemDTO(49,"박나래"));
		//dao.deleteMethod(new Integer(41));
		
		
		/*List<MemDTO> list = dao.list();
		for(MemDTO dto : list)
			System.out.printf("%d %s %d %s\n",
					dto.getNum(), dto.getName(),
					dto.getAge(), dto.getLoc());*/
		
		/*MemDTO dto = dao.one(42);
		System.out.printf("%d %s %d %s\n",
				dto.getNum(), dto.getName(),
				dto.getAge(), dto.getLoc());*/
		
		System.out.println("count:"+dao.countMethod());
		
		
	}//end main()

}//end class
