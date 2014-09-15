package translator;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import junit.framework.TestCase;
import org.jlua.main.nodes.LuaRootNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.translator.Translator;
import org.junit.Test;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.parser.LuaParser;

import java.io.*;

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
