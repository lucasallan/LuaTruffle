package org.jlua.main;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.jlua.main.nodes.LuaRootNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.runtime.LuaContext;
import org.jlua.main.translator.Translator;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.parser.LuaParser;

import java.io.FileInputStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public class Main {

	public static void main(String args[]) {
		try {
		    String file = "src/test/resources/functions.lua";
			LuaParser parser = new LuaParser(new FileInputStream(file));
			Chunk chunk = parser.Chunk();

            new LuaContext().executeMain(chunk);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
