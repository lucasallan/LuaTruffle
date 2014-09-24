package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.runtime.LuaMethod;

/**
 * Created by Lucas Allan Amorim on 2014-09-24.
 */
public final class LuaMethodNode extends LuaExpressionNode {

    private final LuaMethod value;

    public LuaMethodNode(LuaMethod value) {
        this.value = value;
    }

    public LuaMethod executeFunction(VirtualFrame frame) {
        return (LuaMethod) execute(frame);
    }

    public Object executeGeneric(VirtualFrame frame) {
        return execute(frame);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return value;
    }
}
