package translator;

import com.oracle.truffle.api.CallTarget;
import org.junit.Test;
/**
 * Created by Lucas Allan Amorim on 2014-09-12.
 */
public class TranslatorTest extends BaseTranslatorTest {

    @Test
    public void testIfThenElse() {
        CallTarget callTarget = createCallTarget(getLuaFile("ifthenelse.lua"));
        long expectResult = 0;
        assertEquals(callTarget.call(), expectResult);
    }

    @Test
    public void testArithmeticOperations() {
        CallTarget callTarget = createCallTarget(getLuaFile("arithmetic_operations.lua"));
        long expectResult = 1;
        assertEquals(callTarget.call(), expectResult);
    }

    @Test
    public void testLuaFunctions() {
       // CallTarget callTarget = createCallTarget(getLuaFile("functions.lua"));
       // assertEquals(callTarget.call(), 20);
    }

}
