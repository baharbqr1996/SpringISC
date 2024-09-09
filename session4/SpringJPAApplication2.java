package ir.freeland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import ir.freeland.springboot.persistence.base.MyJpaBaseInRun;




@SpringBootApplication
public class SpringJPAApplication2 {


	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJPAApplication2.class, args);
		
		MyJpaBaseInRun JpaBaseInRun = context.getBean( MyJpaBaseInRun.class);
    	JpaBaseInRun.sampleRun();

	}

}
