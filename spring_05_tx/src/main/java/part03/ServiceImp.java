package part03;

import org.springframework.transaction.annotation.Transactional;

public class ServiceImp implements Service {
	private MemDaoImp dao;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}
	
	
	@Transactional(rollbackFor=java.lang.Exception.class)
	@Override
	public void insertProcess() {

		dao.insertMethod(new MemDTO(52, "용팔이", 50, "경기"));
		dao.insertMethod(new MemDTO(53, "유대위", 20, "대전"));

	}// end insertProcess()
	
	// 트랜잭션으로 설정하는 메서드에서는 예외처리를 하면 안된다(트랜잭션 적용안됨)
	// 트랜잭션을 호출하는 곳에서 예외처리 가능함
	@Transactional(rollbackFor=java.lang.Exception.class)
	@Override
	public void testProcess() throws Exception{
	//트랜잭션이 설정된 메소드에서는 try~catch 을 설정하면 안되고
	//메소드를 호출하는 곳에서 try~catch을 한다.
		try{
			//2개가 다 들어가면 안됨, 하나는 들어가짐 예외처리할꺼면 메소드명 옆에다가 throws Exception 해야함
			dao.insertMethod(new MemDTO(55, "팔팔이", 50, "경기"));
			dao.insertMethod(new MemDTO(55, "박대위", 20, "대전"));
		}catch(Exception e){
			
		}
	}//testProcess()

}// end class
