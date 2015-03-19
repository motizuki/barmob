package barmob.persistance.repositories;

import barmob.persistance.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


/**
 * Created by gustavokm90 on 3/17/15.
 */
@RepositoryDefinition(domainClass=Order.class, idClass=String.class)
public interface OrderRepository extends MongoRepository<Order, String>{

    public Order getOrderById(int id);
    @Query(value="{ 'table.$id' : ?0 }", fields="{ 'id' : 1, 'menu' : 1, 'table': 1, 'amount': 1, 'observation': 1, 'status':1}")
    public List<Order> getOrdersByTable(int id);
    @Query(value="{ 'menu.$id' : ?0 }", fields="{ 'id' : 1, 'menu' : 1, 'table': 1, 'amount': 1, 'observation': 1, 'status':1}")
    public List<Order> getOrderByMenu(int id);


}
