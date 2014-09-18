package translator;

import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-18.
 */
public class VariablesTest extends BaseTranslatorTest {

    @Test
    public void testLocalVariables(){
        assertEquals(createCallTarget(createTempFile("local num = 20\nreturn num")).call(), (long) 20);
    }
}
