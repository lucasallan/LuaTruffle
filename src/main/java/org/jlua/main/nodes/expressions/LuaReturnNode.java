package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaStatementNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaReturnNode extends LuaStatementNode {

    @Child
    private LuaNode valueNode;

    public LuaReturnNode(LuaNode valueNode) {
        super();
        this.valueNode = valueNode;
    }

    public Object executeGeneric(VirtualFrame frame) {
        return valueNode.execute(frame);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        Object value = executeGeneric(frame);
        throw new LuaReturnException(value);
    }


}
