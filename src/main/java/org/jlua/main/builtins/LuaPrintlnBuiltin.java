package org.jlua.main.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Specialization;

import java.io.PrintStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaPrintlnBuiltin extends LuaBuiltinNode {

    @Specialization
    public long println(long value) {
        doPrint(System.out, value);
        return value;
    }

    @CompilerDirectives.SlowPath
    private static void doPrint(PrintStream out, long value) {
        out.println(value);
    }

    @Specialization
    public boolean println(boolean value) {
        doPrint(System.out, value);
        return value;
    }

    @CompilerDirectives.SlowPath
    private static void doPrint(PrintStream out, boolean value) {
        out.println(value);
    }

    @Specialization
    public String println(String value) {
        doPrint(System.out, value);
        return value;
    }

    @CompilerDirectives.SlowPath
    private static void doPrint(PrintStream out, String value) {
        out.println(value);
    }

    @Specialization
    public Object println(Object value) {
        doPrint(System.out, value);
        return value;
    }

    @CompilerDirectives.SlowPath
    private static void doPrint(PrintStream out, Object value) {
        out.println(value);
    }
}
