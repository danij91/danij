package part10_annotation_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondConfig {
	@Autowired
	private String sn;
	
	@Bean
	public StringBuffer sb(){
		return new StringBuffer(sn);
	}
}
