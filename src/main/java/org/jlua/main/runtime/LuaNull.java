package org.jlua.main.runtime;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public final class LuaNull {
    public static final LuaNull SINGLETON = new LuaNull();

    private LuaNull() {}

    @Override
    public String toString() {
        return "nil";
    }
}
