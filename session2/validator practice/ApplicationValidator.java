package ir.freeland.spring.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationValidator {
	
	public static void main(String[] args)  {
		ApplicationContext applicationContext =  SpringApplication.run(ApplicationValidator.class, args);
		
		Input input = new Input();
		input.setNumberBetweenOneAndTen(0);
		input.setIpAddress("invalid");
		input.setEmailAddress("12#34#56");
		input.setNotEmpty(null);    
		
		var validator = applicationContext.getBean(ProgrammaticallyValidatingService.class);		
		
		validator.validateInput(input);
	}
}
