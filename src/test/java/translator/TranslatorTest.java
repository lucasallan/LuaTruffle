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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Lucas Allan Amorim on 2014-09-12.
 */
public class TranslatorTest extends TestCase {

    protected Translator translator = new Translator();

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

//    @Test
//    public void testLuaFunctions() {
//        CallTarget callTarget = createCallTarget(getLuaFile("functions.lua"));
//        assertEquals(callTarget.call(), 20);
//
//    }

    protected CallTarget createCallTarget(String file)  {
        try {
            LuaParser parser = new LuaParser(new FileInputStream(file));
            Chunk chunk = parser.Chunk();

            LuaStatementNode statement = (LuaStatementNode) new Translator().translate(chunk.block);
            LuaFunctionBody body = new LuaFunctionBody(statement);
            LuaRootNode root = new LuaRootNode(body);
            CallTarget callTarget = Truffle.getRuntime().createCallTarget(root);

            return callTarget;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected String getLuaFile(String file) {
        return getClass().getResource("/" + file).getFile();
    }
}
