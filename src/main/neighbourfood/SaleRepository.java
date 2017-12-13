package main.neighbourfood;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Integer> {
    @Query("select r from Response r where r.sale_id = :id")
    public List<Response> findAllResponsesForSale(@Param("id") Sale s);

    @Query("select r from Requirement r where r.sale = :id")
    public List<Requirement> findAllRequirementsForSale(@Param("id") Sale sale_id);

    @Query("select s from Sale s order by create_date desc")
    public List<Sale> findAllSalesByCreateDate();
}
