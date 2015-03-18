import barmob.CoreAppConf;
import barmob.actions.MenuActions;
import barmob.actions.TableActions;
import barmob.persistance.domain.Menu;
import barmob.persistance.domain.MenuTypes;
import barmob.persistance.domain.TableStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CoreAppConf.class, loader=AnnotationConfigContextLoader.class)
public class TableActionsTest {

    @Autowired
    TableActions tableActions;

    int idTest;

    @Before
    public void setUp() throws Exception {
        System.out.println("Preparing tests");
        idTest = tableActions.createTable(1,"123",TableStatus.FREE).getId();

    }


    @Test
    public void testTableActions() {

        assertTrue("Start Table: ",tableActions.startTable(idTest));
        assertTrue("Start Table: ", tableActions.closeTable(idTest));
        assertTrue("Start Table: ", tableActions.resetTable(idTest));


    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Finalizing tests");
        tableActions.deleteTable(idTest);
    }

}
