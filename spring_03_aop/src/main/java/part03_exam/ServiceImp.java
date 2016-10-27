package part03_exam;

public class ServiceImp implements Service {

	@Override
	public void prn() {
		System.out.println("prn");
		int sum=0;
		for(int i=0; i<1000000000; i++)
			sum+=i;
	}

}
