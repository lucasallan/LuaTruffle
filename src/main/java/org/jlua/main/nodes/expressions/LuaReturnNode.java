package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.jlua.main.nodes.LuaExpressionNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaReturnNode extends LuaExpressionNode {

    @Child
    private LuaExpressionNode valueNode;

    public LuaReturnNode(LuaExpressionNode valueNode) {
        super();
        this.valueNode = valueNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return valueNode.executeGeneric(frame);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        Object value = executeGeneric(frame);
        throw new LuaReturnException(value);
    }


}
