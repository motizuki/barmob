import barmob.resttypes.MenuTypes;
import org.junit.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by gustavokm90 on 3/18/15.
 */
public class MenuActionsTest extends TestActions{

    @Test
    public void testMenuActions() {
        assertEquals("Testing getMenuPrice: ", 45.0, menuActions.getMenu(idTestMenu).getPrice()) ;
        System.out.println(menuActions.getMenuType(MenuTypes.MAIN).toString());
        assertEquals("Testing updateMenu: ", true, menuActions.updateMenu(idTestMenu, "availability", false));
    }


}
