package barmob.persistance;

import barmob.persistance.domain.Table;
import barmob.persistance.domain.TableStatus;
import barmob.persistance.repositories.MenuRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@Component
public class TestClassDummy {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersistanceAppConf.class);

        MenuRepository repo = ctx.getBean(MenuRepository.class);
        System.out.println(repo.getMenuByType("main").toString());
        System.out.println(repo.getMenuById(4));



        MongoOperations mongoOps = (MongoTemplate) ctx.getBean("mongoTemplate");

        System.out.println((mongoOps.findOne(new Query(where("tableNumber").is(1)), Table.class)));

    }
}
