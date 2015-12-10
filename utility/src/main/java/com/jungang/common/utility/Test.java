package com.jungang.common.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

//@PropertySource("classpath:properties.xml")
@PropertySource(value = { "classpath:application.properties" })
public class Test {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	@Autowired
	static Environment env;

    @Value("${SMTP_HOST_NAME:/temp/input}")
    private static String source;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String workingDirectory = "";

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:properties.xml");
		Environment environment = context.getEnvironment();
		System.out.println(source);
	}

}
