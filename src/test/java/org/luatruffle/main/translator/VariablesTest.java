package org.luatruffle.main.translator;

import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-18.
 */
public class VariablesTest extends BaseTranslatorTest {

    @Test
    public void testLocalVariables(){
        assertEquals(createCallTarget(createTempFile("local num = 20\nreturn num")).call(), (long) 20);
    }

    @Test
    public void testMultipleAssignment(){
        assertEquals(createCallTarget(createTempFile("local a, b = 14, 2\nreturn a + b")).call(), (long) 14 + 2);
    }

}
