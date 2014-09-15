package translator;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public class RelationalOperators extends TranslatorTest {

    @Test
    public void testEqualsNode() {
        assertEquals(createCallTarget(createTempFile("return 1 == 1")).call(), true);
        assertEquals(createCallTarget(createTempFile("return 1 ~= 1")).call(), true);
    }

    @Test
    public void testGreaterOrEqualsNode(){
        assertEquals(createCallTarget(createTempFile("return 1 >= 1")).call(), true);
        assertEquals(createCallTarget(createTempFile("return 2 >= 1")).call(), true);
        assertEquals(createCallTarget(createTempFile("return 1 >= 2")).call(), false);
    }

    @Test
    public void testGreateThanrNode(){
        assertEquals(createCallTarget(createTempFile("return 1 > 1")).call(), false);
        assertEquals(createCallTarget(createTempFile("return 2 > 1")).call(), true);
        assertEquals(createCallTarget(createTempFile("return 1 > 2")).call(), false);
    }

    @Test
    public void testLessThanNode(){
        assertEquals(createCallTarget(createTempFile("return 1 < 1")).call(), false);
        assertEquals(createCallTarget(createTempFile("return 2 < 1")).call(), false);
        assertEquals(createCallTarget(createTempFile("return 1 < 2")).call(), true);
    }

    @Test
    public void testLessOrEqualsNode(){
        assertEquals(createCallTarget(createTempFile("return 1 <= 1")).call(), true);
        assertEquals(createCallTarget(createTempFile("return 2 <= 1")).call(), false);
        assertEquals(createCallTarget(createTempFile("return 1 <= 2")).call(), true);
    }
}
