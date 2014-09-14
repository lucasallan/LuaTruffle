package org.jlua.main;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaRootNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.translator.Translator;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.parser.LuaParser;

import java.io.FileInputStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public class Main {

	public static void main(String args[]) {
		try {
		    String file = "src/test/main.lua";
			LuaParser parser = new LuaParser(new FileInputStream(file));
			Chunk chunk = parser.Chunk();

            LuaStatementNode statement = (LuaStatementNode) new Translator().translate(chunk.block);
            LuaFunctionBody body = new LuaFunctionBody(statement);
            LuaRootNode root = new LuaRootNode(body);
            CallTarget callTarget = Truffle.getRuntime().createCallTarget(root);

            System.out.println(callTarget.call());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
