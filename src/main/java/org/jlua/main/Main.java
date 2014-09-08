package org.jlua.main;

import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.ast.Exp.AnonFuncDef;
import org.luaj.vm2.ast.Stat.FuncDef;
import org.luaj.vm2.ast.Stat.LocalFuncDef;
import org.luaj.vm2.ast.Exp.VarExp;
import org.luaj.vm2.ast.Visitor;
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
			chunk.accept(new Visitor() {
				public void visit(AnonFuncDef exp) {
					System.out.println("Anonymous function definition at "
							+ exp.beginLine + "." + exp.beginColumn + ","
							+ exp.endLine + "." + exp.endColumn);
				}

				public void visit(FuncDef stat) {
					System.out.println("Function definition '"
							+ stat.name.name.name + "' at " + stat.beginLine
							+ "." + stat.beginColumn + "," + stat.endLine + "."
							+ stat.endColumn);

					System.out.println("\tName location " + stat.name.beginLine
							+ "." + stat.name.beginColumn + ","
							+ stat.name.endLine + "." + stat.name.endColumn);
				}

				public void visit(LocalFuncDef stat) {
					System.out.println("Local function definition '"
							+ stat.name.name + "' at " + stat.beginLine + "."
							+ stat.beginColumn + "," + stat.endLine + "."
							+ stat.endColumn);
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
