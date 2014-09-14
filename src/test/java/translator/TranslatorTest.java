package translator;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.jlua.main.nodes.LuaRootNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.translator.Translator;
import org.junit.Test;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.ast.Stat;
import org.luaj.vm2.parser.LuaParser;
import org.luaj.vm2.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Lucas Allan Amorim on 2014-09-12.
 */
public class TranslatorTest {

    protected Translator translator = new Translator();

    @Test
    public void testIfThenElse() throws ParseException, FileNotFoundException {
        String file = "src/test/main.lua";
        LuaParser parser = new LuaParser(new FileInputStream(file));
        Chunk chunk = parser.Chunk();

        LuaStatementNode statement = (LuaStatementNode) new Translator().translate(chunk.block);
        LuaFunctionBody body = new LuaFunctionBody(statement);
        LuaRootNode root = new LuaRootNode(body);
        CallTarget callTarget = Truffle.getRuntime().createCallTarget(root);

        org.junit.Assert.assertEquals(callTarget.call(), 10);
    }
}
