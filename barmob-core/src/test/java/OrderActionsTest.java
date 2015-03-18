import barmob.persistance.domain.OrderStatus;
import org.junit.Test;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by gustavokm90 on 3/19/15.
 */
public class OrderActionsTest extends TestActions{


    @Test
    public void testOrderActions() {
        assertTrue("Order progress: ", orderActions.changeOrderState(idTestOrder, OrderStatus.INPREPARATION));
        assertTrue("Show orders by table: ", !orderActions.getOrdersByTable(idTestTable).isEmpty());
        assertTrue("Order progress: ", orderActions.changeOrderState(idTestOrder, OrderStatus.READY));
    }

}
