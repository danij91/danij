package part03_exam;

public class StopWatch {
	private long cal1,cal2;
	public void start(){
		cal1=System.currentTimeMillis();
		System.out.println("start:"+cal1);
	}
	public void stop(){
		cal2=System.currentTimeMillis();
		System.out.println("stop:"+cal2);
	}
	public long getTotalTimeMillis(){
		return cal2-cal1;
	}
}
