package barmob.actions;


import barmob.persistance.PersistanceAppConf;
import barmob.persistance.repositories.CountersRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by gustavokm90 on 3/18/15.
 */
public abstract class Action{

    protected AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersistanceAppConf.class);
    protected CountersRepository sequence = ctx.getBean(CountersRepository.class);
    protected MongoTemplate mongoTemplate = (MongoTemplate)ctx.getBean("mongoTemplate");



}
