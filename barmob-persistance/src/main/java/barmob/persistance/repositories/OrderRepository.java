package barmob.persistance.repositories;

import barmob.persistance.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * Created by gustavokm90 on 3/17/15.
 */
@RepositoryDefinition(domainClass=Order.class, idClass=String.class)
public interface OrderRepository extends MongoRepository<Order, String>{

    public Order getOrderById(int id);

}
