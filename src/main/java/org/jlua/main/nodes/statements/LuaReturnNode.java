package org.jlua.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.statements.controlflow.LuaReturnException;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaReturnNode extends LuaStatementNode {

    @Child
    private LuaNode valueNode;

    public LuaReturnNode(LuaNode valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        Object value = valueNode.execute(frame);
        throw new LuaReturnException(value);
    }


}
