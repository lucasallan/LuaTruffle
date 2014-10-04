package org.jlua.main.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Created by Lucas Allan Amorim on 2014-09-11.
 */
public class LuaObjectConstantNode extends LuaExpressionNode {

    private final Object value;

    public LuaObjectConstantNode(Object value) {
        this.value = value;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return value;
    }

    public String toString(){
        return "Constant: " + value;
    }

}
