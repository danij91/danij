package part09_annotation_xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:part09_annotation_xml/di1.xml","classpath:part09_annotation_xml/di2.xml"})
public class Config {
	
}
