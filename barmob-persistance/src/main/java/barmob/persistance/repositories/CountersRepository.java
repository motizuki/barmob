package barmob.persistance.repositories;

import barmob.resttypes.Counters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@RepositoryDefinition(domainClass=Counters.class, idClass=String.class)
public interface CountersRepository extends MongoRepository<Counters, String> {

    @Query(value="{ 'id' : ?0 }", fields="{ 'id' : 1, 'seq' : 1}")
    public Counters getSeq(String type);
}
