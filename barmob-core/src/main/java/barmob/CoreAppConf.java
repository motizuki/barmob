package barmob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import barmob.persistance.PersistanceAppConf;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@Configuration
@ComponentScan
@Import({PersistanceAppConf.class})
public class CoreAppConf {

    private static final Logger logger = LoggerFactory.getLogger(CoreAppConf.class);

}
