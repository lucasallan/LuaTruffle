package org.luatruffle.main.translator;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import junit.framework.TestCase;
import org.luatruffle.main.nodes.LuaRootNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.expressions.LuaFunctionBody;
import org.luatruffle.main.runtime.LuaContext;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.parser.LuaParser;
import org.luatruffle.main.translator.Translator;

import java.io.*;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public abstract class BaseTranslatorTest extends TestCase {

    protected CallTarget createCallTarget(String file)  {
        try {
            Translator translator = new Translator(new LuaContext());
            LuaParser parser = new LuaParser(new FileInputStream(file));
            Chunk chunk = parser.Chunk();

            LuaStatementNode statement = (LuaStatementNode) translator.translate(chunk.block);
            LuaFunctionBody body = new LuaFunctionBody(statement);
            LuaRootNode root = new LuaRootNode(body, translator.getFrameDescriptor());
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

    protected String createTempFile(String content) {
        try {
            File temp = File.createTempFile("tmp_file", ".tmp");
            FileWriter fw = new FileWriter(temp.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            return temp.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
