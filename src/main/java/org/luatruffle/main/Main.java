package org.luatruffle.main;

import org.luatruffle.main.runtime.LuaContext;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.parser.LuaParser;

import java.io.FileInputStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public class Main {

	public static void main(String args[]) {
		try {
            if (args.length != 1) {
                System.err.println("usage: luatruffle file");
                System.exit(1);
            }

            final String file = args[0];

			LuaParser parser = new LuaParser(new FileInputStream(file));
			Chunk chunk = parser.Chunk();

            new LuaContext().executeMain(chunk);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
