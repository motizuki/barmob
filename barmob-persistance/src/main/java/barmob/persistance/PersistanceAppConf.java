package barmob.persistance;

import barmob.persistance.repositories.Repository;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses=Repository.class)
public class PersistanceAppConf extends AbstractMongoConfiguration {

    // ---------------------------------------------------- mongodb config

    @Override
    protected String getDatabaseName() {
        return "barmob";
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient("127.0.0.1");
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return "barmob.persistance.domain";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "barmob");
    }

}