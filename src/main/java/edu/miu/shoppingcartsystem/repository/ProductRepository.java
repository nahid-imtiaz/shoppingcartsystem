package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

