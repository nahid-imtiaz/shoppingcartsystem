package edu.miu.shoppingcartsystem.repository;


import edu.miu.shoppingcartsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

