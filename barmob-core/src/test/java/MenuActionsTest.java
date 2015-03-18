import barmob.persistance.domain.MenuTypes;
import org.junit.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * Created by gustavokm90 on 3/18/15.
 */
public class MenuActionsTest extends TestActions{

    @Test
    public void testMenuActions() {
        assertEquals("Testing getMenuPrice: ", 15.0, menuActions.getMenuPrice(23).getDouble("price")) ;
        System.out.println(menuActions.getMenuType(MenuTypes.MAIN).toString());
        assertEquals("Testing updateMenu: ", true, menuActions.updateMenu(23, "availability", false));
    }


}
