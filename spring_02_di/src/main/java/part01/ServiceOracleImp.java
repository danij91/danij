package part01;
/*
 * 참조가 되는 객체 정의
 * 1 인터페이스를 상속받아 정의한다
 * 2 무인자 생성자를 정의한다.
 */
public class ServiceOracleImp implements Service{

	@Override
	public void prn() {
		// TODO Auto-generated method stub
		System.out.println("oracle");
	}

}
