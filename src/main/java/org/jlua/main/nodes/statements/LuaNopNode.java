package org.jlua.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaStatementNode;

/**
 * Created by chrisseaton on 18/09/2014.
 */
public class LuaNopNode extends LuaStatementNode {

    @Override
    public void executeVoid(VirtualFrame frame) {

    }

}
