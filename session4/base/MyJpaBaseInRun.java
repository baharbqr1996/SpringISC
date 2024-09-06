package ir.freeland.springboot.persistence.base;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import ir.freeland.springboot.persistence.model.CorruptedItem;
import ir.freeland.springboot.persistence.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class MyJpaBaseInRun {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	public void sampleRun() {
		entityManager=entityManager.getEntityManagerFactory().createEntityManager();		
		Session session = entityManager.unwrap(Session.class);
		Transaction transaction = session.getTransaction();
		
		//Save*****************************************
		transaction.begin();
		Item item1 = new Item();
        item1.setName("soap");
        item1.setPrice(100.0);
        item1.setCategory("washing");
        session.persist(item1);
        transaction.commit();
		
        transaction.begin();
        Item item2 = new Item();
        item2.setName("soda");
        item2.setPrice(200.0);
        item2.setCategory("drink");
        session.persist(item2);
        transaction.commit();
        
        transaction.begin();
        CorruptedItem corruptedItem = new CorruptedItem();
        corruptedItem.setItem(item2);
        session.persist(corruptedItem);
    	transaction.commit();
    	
    	//Find*****************************************
    	transaction.begin();
    	List<Item> allItems = session.createQuery("SELECT * FROM item" , Item.class).getResultList();
    	allItems.stream().forEach( s -> System.out.println(s));    	
    	transaction.commit();

    	//Update *****************************************
    	transaction.begin();
    	allItems.get(0).setName("shampoo");
    	transaction.commit();
    	
    	//Delete *****************************************
    	transaction.begin();
        deleteItem(session, 1L);
        transaction.commit();
    	
    	
	}


	private void deleteItem(Session session, long l) {
		// TODO Auto-generated method stub
		
	}
}
