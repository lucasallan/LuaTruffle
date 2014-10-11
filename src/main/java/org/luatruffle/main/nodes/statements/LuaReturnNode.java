package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.statements.controlflow.LuaReturnException;

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
