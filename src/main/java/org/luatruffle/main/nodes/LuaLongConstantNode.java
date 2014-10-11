package org.luatruffle.main.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Created by Lucas Allan Amorim on 2014-09-11.
 */
public class LuaLongConstantNode extends LuaExpressionNode {

    private final long value;

    public LuaLongConstantNode(long value) {
        this.value = value;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeLong(frame);
    }

    @Override
    public long executeLong(VirtualFrame frame) {
        return value;
    }

    public String toString(){
        return "Constant: " + value;
    }

}
