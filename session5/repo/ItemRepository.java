package ir.freeland.springboot.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Item2;

public interface ItemRepository extends CrudRepository<Item2, Long>{
	List<Item2> findByName(String name);
    List<Item2> findByNameIgnoreCase(String name);
    List<Item2> findByNameConsistOf(String name);
    List<Item2> findByNameConsistOfIgnoreCase(String name);
}
