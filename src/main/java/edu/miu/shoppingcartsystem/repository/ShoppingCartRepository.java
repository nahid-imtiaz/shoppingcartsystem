package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
}
