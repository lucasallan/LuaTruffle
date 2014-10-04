package translator;

import com.oracle.truffle.api.CallTarget;
import org.jlua.main.runtime.LuaNull;
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
}
