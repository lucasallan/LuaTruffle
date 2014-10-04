package org.jlua.main.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Created by Lucas Allan Amorim on 2014-09-11.
 */
public class LuaBooleanConstantNode extends LuaExpressionNode {

    private final boolean value;

    public LuaBooleanConstantNode(boolean value) {
        this.value = value;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeBoolean(frame);
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame) {
        return value;
    }

    public String toString(){
        return "Constant: " + value;
    }

}
