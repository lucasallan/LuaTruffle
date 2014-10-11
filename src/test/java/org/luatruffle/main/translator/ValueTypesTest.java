package org.luatruffle.main.translator;

import org.luatruffle.main.runtime.LuaNull;
import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-16.
 */
public class ValueTypesTest extends BaseTranslatorTest {

    @Test
    public void testNilValue() {
        assertEquals(createCallTarget(createTempFile("return nil")).call(), LuaNull.SINGLETON);
    }

    @Test
    public void testStringValue() {
        assertEquals(createCallTarget(createTempFile("return 'hello'")).call(), "hello");
    }

    @Test
    public void testNumericValue() {
        assertEquals(createCallTarget(createTempFile("return 12")).call(), (long) 12);
    }
}
