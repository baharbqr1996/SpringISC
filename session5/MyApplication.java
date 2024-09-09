package ir.freeland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import ir.freeland.springboot.persistence.repo.MyRepositoryRun;

@SpringBootApplication
//@EnableJpaRepositories("ir.freeland.springboot.persistence.repo")
@EntityScan(basePackages =  {"ir.freeland.springboot.persistence.model"})
public class MyApplication {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(SpringJPAApplication.class, args);
		
		MyRepositoryRun repositoryRun = context.getBean( MyRepositoryRun.class);
		repositoryRun.sampleRun();
    }
}
