import barmob.CoreAppConf;
import barmob.actions.MenuActions;
import barmob.actions.OrderActions;
import barmob.actions.TableActions;
import barmob.resttypes.*;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by gustavokm90 on 3/19/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CoreAppConf.class, loader=AnnotationConfigContextLoader.class)
public abstract class TestActions {

    @Autowired
    protected OrderActions orderActions;
    @Autowired
    protected MenuActions menuActions;
    @Autowired
    protected TableActions tableActions;

    protected int idTestMenu;
    protected int idTestTable;
    protected int idTestOrder;
    protected Menu menu;
    protected Table table;
    protected Order order;

    @Before
    public void setUp() throws Exception {
        System.out.println("Preparing tests");
        menu = menuActions.createMenu("Pasta", 45.0, true, "Cool pasta", MenuTypes.MAIN);
        idTestMenu = menu.getId();

        table = tableActions.createTable(1,"123");
        idTestTable = table.getId();
        tableActions.startTable(idTestTable);

        order = orderActions.makeOrder(idTestMenu,idTestTable,1,"Testing order");
        idTestOrder = order.getId();
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("Finalizing tests");
        orderActions.deleteOrder(idTestOrder);
        menuActions.deleteMenu(idTestMenu);
        tableActions.deleteTable(idTestTable);

    }
}
