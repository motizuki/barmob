package test.java;

import barmob.persistance.PersistanceAppConf;
import barmob.persistance.domain.Menu;
import barmob.persistance.repositories.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistanceAppConf.class, loader=AnnotationConfigContextLoader.class)
public class MenuRepositoryTest {

    @Autowired
    MenuRepository repository;

    @Test
    public void readsFirstPageCorrectly() {

        List<Menu> menus = repository.getMenuByType("main");
        System.out.println(menus.toString());
    }
}