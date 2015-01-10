package org.luatruffle.main.translator;

import com.oracle.truffle.api.CallTarget;
import org.junit.Test;
/**
 * Created by Lucas Allan Amorim on 2014-09-12.
 */
public class TranslatorTest extends BaseTranslatorTest {

    @Test
    public void testIfThenElse() {
        CallTarget callTarget = createCallTarget(getLuaFile("ifthenelse.lua"));
        long expectResult = 10;
        assertEquals(callTarget.call(), expectResult);
    }

    @Test
    public void testNumericFor() {
        CallTarget callTarget = createCallTarget(getLuaFile("numeric_for.lua"));
        long expectResult = 10;
        assertEquals(callTarget.call(), expectResult);
    }

    @Test
    public void testNumericForWithParam() {
        CallTarget callTarget = createCallTarget(getLuaFile("numeric_for2.lua"));
        long expectResult = 0;
        assertEquals(callTarget.call(), expectResult);
    }
}
