package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
