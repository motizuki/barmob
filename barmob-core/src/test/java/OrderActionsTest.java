import barmob.CoreAppConf;
import barmob.actions.MenuActions;
import barmob.actions.OrderActions;
import barmob.actions.TableActions;
import barmob.persistance.domain.Menu;
import barmob.persistance.domain.MenuTypes;
import barmob.persistance.domain.OrderStatus;
import barmob.persistance.domain.TableStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by gustavokm90 on 3/19/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CoreAppConf.class, loader=AnnotationConfigContextLoader.class)
public class OrderActionsTest extends TestActions{


    @Test
    public void testTableActions() {
        assertTrue("Order progress: ", orderActions.changeOrderState(idTestOrder, OrderStatus.INPREPARATION));
        assertTrue("Show orders by table: ", !orderActions.getOrdersByTable(idTestTable).isEmpty());
        assertTrue("Order progress: ", orderActions.changeOrderState(idTestOrder, OrderStatus.READY));
    }

}
