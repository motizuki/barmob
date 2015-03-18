package barmob.persistance.repositories;

import barmob.persistance.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@RepositoryDefinition(domainClass=Menu.class, idClass=String.class)
public interface MenuRepository extends MongoRepository<Menu, String>{

    //@Query(value="{ 'type' : ?0 }", fields="{ 'name' : 1, 'price' : 1, 'availability': 1, 'details': 1, 'type':1}")
    public List<Menu> getMenuByType(String type);
    //@Query(value="{ 'id' : ?0 }", fields="{ 'name' : 1, 'price' : 1, 'availability': 1, 'details': 1, 'type':1}")
    public Menu getMenuById(int id);
}
