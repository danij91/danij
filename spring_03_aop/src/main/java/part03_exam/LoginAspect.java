package part03_exam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
@Aspect
public class LoginAspect {
	private Log log=LogFactory.getLog(getClass());
	
	@Around(value="execution(* part03_exam.ServiceImp.prn())")
	public void logging(ProceedingJoinPoint joinPoint){
		StopWatch stopWatch=new StopWatch();
		log.info("기록시작");
		stopWatch.start();
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopWatch.stop();
		log.info("기록종료");
		log.info(joinPoint.getSignature().getName()+"_메서드 실행시간:"+stopWatch.getTotalTimeMillis());
		
	}
}
