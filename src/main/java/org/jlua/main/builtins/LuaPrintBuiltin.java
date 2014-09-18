package org.jlua.main.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jlua.main.nodes.LuaNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
@NodeInfo(shortName = "print")
@NodeChild(value = "value", type = LuaNode.class)
public abstract class LuaPrintBuiltin extends LuaNode {

    @CompilerDirectives.SlowPath
    @Specialization
    public long print(long value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public boolean print(boolean value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public String print(String value) {
        System.out.println(value);
        return value;
    }

    @CompilerDirectives.SlowPath
    @Specialization
    public Object print(Object value) {
        System.out.println(value);
        return value;
    }

}
