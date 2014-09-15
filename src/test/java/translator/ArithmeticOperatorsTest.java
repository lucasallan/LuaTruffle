package translator;

import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public class ArithmeticOperatorsTest extends BaseTranslatorTest {

    @Test
    public void testAddOperatorsWithNumbers(){
        long expectedResult = 4;
        assertEquals(createCallTarget(createTempFile("return 2 + 2")).call(), expectedResult);
    }

    @Test
    public void testAddOperatorsWithString(){
        assertEquals(createCallTarget(createTempFile("return 'Hello' + ' world'")).call(), "Hello world");
    }
}
