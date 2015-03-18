package barmob.actions;

import barmob.persistance.PersistanceAppConf;
import barmob.persistance.repositories.MenuRepository;
import barmob.persistance.repositories.OrderRepository;
import barmob.persistance.repositories.TableRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gustavokm90 on 3/18/15.
 */
public class BillActions {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PersistanceAppConf.class);
    private MenuRepository menuRepository = ctx.getBean(MenuRepository.class);
    private TableRepository tableRepository = ctx.getBean(TableRepository.class);
    private OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
}
