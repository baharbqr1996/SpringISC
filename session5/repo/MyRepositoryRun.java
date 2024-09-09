package ir.freeland.springboot.persistence.repo;

import org.springframework.beans.factory.annotation.Autowired;

import ir.freeland.springboot.persistence.model.Category;
import ir.freeland.springboot.persistence.model.Item2;

public class MyRepositoryRun {
	
    @Autowired
    private ItemRepository itemRepo;
    
    @Autowired
    private CategoryRepository catRepo;
    
    public void sampleRun() {
    	
    	var allItems = itemRepo.findAll();
        System.out.println(allItems);
        
        var item1 = itemRepo.findByName("shampoo");
        System.out.println(item1);
        
        var item2 = itemRepo.findByNameIgnoreCase("soda");
        System.out.println(item2);
        
        var item3 = itemRepo.findByNameConsistOf("play");
        System.out.println(item3);
        
        var item4 = itemRepo.findByNameConsistOf("play");
        System.out.println(item4);
        
        
    	var allCategories = catRepo.findAll();
        System.out.println(allCategories);
        
        var cat1 = catRepo.findByName("drink");
        System.out.println(cat1);
        
        var cat2 = catRepo.findByNameIgnoreCase("drink");
        System.out.println(cat2);
        
        var cat3 = catRepo.findByNameEndingWith("g");
        System.out.println(cat3); 
        
        var cat4 = catRepo.findByNameEndingWithIgnoreCase("g");
        System.out.println(cat4); 
        
        Category category = new Category();
        category.setName("Electronics");
        catRepo.save(category);

        Item2 item = new Item2();
        item.setName("Laptop");
        item.setCategory(category);
        itemRepo.save(item);
    
        var itemWithCategory = itemRepo.findById(item.getId()).orElse(null);
        if (itemWithCategory != null) {
            System.out.println("Item Name: " + itemWithCategory.getName());
            System.out.println("Associated Category: " + itemWithCategory.getCategory().getName());
        }

        var categoryWithItem = catRepo.findById(category.getId()).orElse(null);
        if (categoryWithItem != null) {
            System.out.println("Category Name: " + categoryWithItem.getName());
            System.out.println("Associated Item: " + categoryWithItem.getItem2().getName());
        }
    
    }
}
