package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.model.Product;
import edu.miu.shoppingcartsystem.payload.request.CategorySaleReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIsActive(boolean b);

    @Query(value="SELECT new edu.miu.shoppingcartsystem.payload.request.CategorySaleReportDTO(c.name, sum(p.quantity), sum(p.sellingPrice)) from Category c join Product p on c.id=p.id group by c.name")
    List<CategorySaleReportDTO> getCategorySaleReport();
}

