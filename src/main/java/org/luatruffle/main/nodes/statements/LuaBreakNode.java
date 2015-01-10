package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.statements.controlflow.LuaBreakException;

/**
 * Created by Lucas Allan Amorim on 15-01-10.
 */
public class LuaBreakNode extends LuaStatementNode {

    @Override
    public void executeVoid(VirtualFrame frame) {
        throw LuaBreakException.SINGLETON;
    }
}