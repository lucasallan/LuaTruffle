package org.jlua.main.nodes;

import java.math.BigInteger;

import com.oracle.truffle.api.dsl.*;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.runtime.LuaMethod;
import org.jlua.main.runtime.LuaNull;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
@TypeSystem({ long.class, BigInteger.class, boolean.class, String.class, LuaNull.class, LuaMethod.class})
public abstract class LuaTypes {

    @TypeCheck
    public boolean isLuaNull(Object value) {
        return value == LuaNull.SINGLETON;
    }

    @TypeCast
    public LuaNull asLuaNull(Object value) {
        assert isLuaNull(value);
        return LuaNull.SINGLETON;
    }
}
