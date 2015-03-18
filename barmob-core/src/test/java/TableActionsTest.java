import org.junit.Test;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * Created by gustavokm90 on 3/18/15.
 */
public class TableActionsTest extends TestActions {


    @Test
    public void testTableActions() {

        assertTrue("Start Table: ",tableActions.startTable(idTestTable));
        assertTrue("Start Table: ", tableActions.closeTable(idTestTable));
        assertTrue("Start Table: ", tableActions.resetTable(idTestTable));


    }

}
