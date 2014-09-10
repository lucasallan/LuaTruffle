package org.jlua.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.source.SourceSection;
import org.jlua.main.nodes.LuaStatementNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaBlockNode extends LuaStatementNode{

    @Children private final LuaStatementNode[] bodyNodes;

    public LuaBlockNode(LuaStatementNode[] nodes) {
        this.bodyNodes = nodes;
    }

    @ExplodeLoop
    @Override
    public void executeVoid(VirtualFrame frame) {
        for (LuaStatementNode node : bodyNodes) {
            node.executeVoid(frame);
        }

    }

}

