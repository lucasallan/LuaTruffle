package translator;

import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-25.
 */
public class MethodDefinitionTest extends BaseTranslatorTest {
    @Test
    public void testMethodDefinitionAndCallTest(){
        assertEquals(createCallTarget(createTempFile(
                "function newFunction()\n" +
                    "return 10\n" +
                "end\n" +
                "return newFunction()")
        ).call(), (long) 10);
    }
}
