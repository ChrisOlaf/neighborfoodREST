package main.neighbourfood;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, Integer> {
    @Query("select r from Response r where r.order_id = :id")
    public List<Response> findAllResponsesForOrder(@Param("id")Orders order_id);

    @Query("select r from Requirement r where r.orders = :id")
    public List<Requirement> findAllRequirementsForOrder(@Param("id") Orders order_id);
}
