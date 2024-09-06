package ir.freeland.springboot.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
	List<Item> findByName(String name);
    List<Item> findByNameIgnoreCase(String name);
    List<Item> findByNameConsistOf(String name);
    List<Item> findByNameConsistOfIgnoreCase(String name);
}
