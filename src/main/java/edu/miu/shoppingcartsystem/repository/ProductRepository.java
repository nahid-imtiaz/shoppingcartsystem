package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value="select p.id,p.name, p.price from Product as p")
    List<Product> findProduct();

//@Query("select new package.DeadlineType(a.id, a.code) from ABDeadlineType a ")
//    @Query("select new Product(p.id, p.name, p.price) from Product as p")
//    List<Product> findProduct();
}

