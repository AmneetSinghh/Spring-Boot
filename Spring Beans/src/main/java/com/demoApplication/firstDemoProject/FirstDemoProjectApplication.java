package com.demoApplication.firstDemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/*
-- @SpringBootApplication annotation is combination of 3 annotations. @ComponentScan -- @enableAutoConfiguration -- @SpringBootConfiguration
@ComponentScan basically scan all @controller, @service, @repository, @Component and put into beans, at the start of application.
*/

public class FirstDemoProjectApplication {

	public static void main(String[] args) {

		ApplicationContext apc = SpringApplication.run(FirstDemoProjectApplication.class, args);
		/*
		 Spring IOC Container handles all the beans...
		 */
		for (String s : apc.getBeanDefinitionNames()) {
			System.out.println(s);
		}
	}


	/*
		Put below configs into config file please.
	 */
	@Bean
	public String getName(){
		return "Amneeet";
	}



}
