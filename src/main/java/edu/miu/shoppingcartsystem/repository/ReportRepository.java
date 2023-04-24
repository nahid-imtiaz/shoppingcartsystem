package edu.miu.shoppingcartsystem.repository;

import edu.miu.shoppingcartsystem.payload.request.CategorySaleReportDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository{

    @Query(value="SELECT c.name, sum(p.quantity) as total_quantity, sum(p.sellingPrice) as total_price from Category c join Product p on c.id=p.id group by c.name order by total_price desc")
    List<CategorySaleReportDTO> getCategorySaleReport();

}
