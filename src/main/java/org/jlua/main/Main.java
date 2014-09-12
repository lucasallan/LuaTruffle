package org.jlua.main;

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
		    String file = "src/test/main.lua";
			LuaParser parser = new LuaParser(new FileInputStream(file));
			Chunk chunk = parser.Chunk();
            Object ob = new Translator().translate(chunk.block);

            System.out.println(ob.getClass().getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
