import barmob.CoreAppConf;
import barmob.actions.MenuActions;
import barmob.persistance.domain.Menu;
import barmob.persistance.domain.MenuTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CoreAppConf.class, loader=AnnotationConfigContextLoader.class)
public class MenuActionsTest {

    @Autowired
    MenuActions menuActions;

    int testID;

    @Before
    public void setUp() throws Exception {
        System.out.println("Preparing tests");
        Menu menu = menuActions.createMenu("Pasta", 45.0, true, "Cool pasta", MenuTypes.MAIN);
        testID = menu.getId();
        assertEquals("Testing createMenu: ", "Pasta", menu.getName());

    }


    @Test
    public void testMenuActions() {
        assertEquals("Testing getMenuPrice: ", 15.0, menuActions.getMenuPrice(23).getDouble("price")) ;
        System.out.println(menuActions.getMenuType(MenuTypes.MAIN).toString());
        assertEquals("Testing updateMenu: ", true, menuActions.updateMenu(23, "availability", false));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Finalizing tests");
        assertEquals("Testing deleteMenu: ", true, menuActions.deleteMenu(testID));
    }

}
