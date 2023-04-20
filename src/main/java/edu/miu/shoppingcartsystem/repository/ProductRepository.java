package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value="select p.id,p.name,p.price from Product as p")
    List<Product> findProduct();
}

