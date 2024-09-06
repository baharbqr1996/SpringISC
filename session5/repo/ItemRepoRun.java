package ir.freeland.springboot.persistence.repo;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ItemRepoRun {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ItemRepository repository;

	public void sampleRun() {
	var allItems = repository.findAll();
    System.out.println(allItems);
    
    var item1 = repository.findByName("shampoo");
    System.out.println(item1);
    
    var item2 = repository.findByNameIgnoreCase("shampoo");
    System.out.println(item2);
    
    var item3 = repository.findByNameConsistOf("play");
    System.out.println(item3); //??
    
    var item4 = repository.findByNameConsistOfIgnoreCase("play");
    System.out.println(item4); 
}
}