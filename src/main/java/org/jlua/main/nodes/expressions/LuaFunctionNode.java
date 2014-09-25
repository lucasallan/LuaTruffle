package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.runtime.LuaFunction;

/**
 * Created by Lucas Allan Amorim on 2014-09-24.
 */
public final class LuaFunctionNode extends LuaExpressionNode {

    private final LuaFunction value;

    public LuaFunctionNode(LuaFunction value) {
        this.value = value;
    }

    public LuaFunction executeFunction(VirtualFrame frame) {
        return (LuaFunction) execute(frame);
    }

    public Object executeGeneric(VirtualFrame frame) {
        return execute(frame);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return value;
    }
}
