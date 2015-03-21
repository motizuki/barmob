package barmob.persistance.repositories;

import barmob.resttypes.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * Created by gustavokm90 on 3/17/15.
 */
@RepositoryDefinition(domainClass=Table.class, idClass=String.class)
public interface TableRepository extends MongoRepository<Table, String>{

    public Table getTableById(int id);

}
