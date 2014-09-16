package org.jlua.main.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Specialization;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaPrintlnBuiltin extends LuaBuiltinNode {

    @CompilerDirectives.SlowPath
    @Specialization
    public long println(long value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public boolean println(boolean value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public String println(String value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public Object println(Object value) {
        System.out.println(value);
        return value;
    }

}
