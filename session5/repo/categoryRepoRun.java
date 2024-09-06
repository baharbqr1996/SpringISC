package ir.freeland.springboot.persistence.repo;

import org.springframework.data.repository.CrudRepository;

import ir.freeland.springboot.persistence.model.Category;

public interface categoryRepoRun extends CrudRepository<Category, Long>{

}
