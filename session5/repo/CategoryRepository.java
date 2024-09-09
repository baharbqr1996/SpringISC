package ir.freeland.springboot.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Category;
import ir.freeland.springboot.persistence.model.Item;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	List<Item> findByName(String name);
    List<Item> findByNameIgnoreCase(String name);
    List<Item> findByNameEndingWith(String name);
    List<Item> findByNameEndingWithIgnoreCase(String name);
}
