package ir.freeland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ir.freeland.springboot.persistence.base.JpaBaseInRun;
import ir.freeland.springboot.persistence.lifecycle.LifecycleInRun;
import ir.freeland.springboot.persistence.repo.RepositoryRun;
import ir.freeland.springboot.relation.joincolumn.JoinColoumnRun;
import ir.freeland.springboot.relation.manytomany.ManyToManyRun;
import ir.freeland.springboot.relation.onetoone.foreignkeybased.ForeignKeyBasedRun;
import ir.freeland.springboot.relation.onetoone.jointable.JointableRun;



@SpringBootApplication
public class SpringJPAApplication2 {


	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJPAApplication2.class, args);
		
		JpaBaseInRun JpaBaseInRun = context.getBean( JpaBaseInRun.class);
    	JpaBaseInRun.sampleRun();

	}

}
