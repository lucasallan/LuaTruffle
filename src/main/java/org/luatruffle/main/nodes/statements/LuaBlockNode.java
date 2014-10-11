package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaStatementNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaBlockNode extends LuaStatementNode {

    @Children private final LuaNode[] bodyNodes;

    public LuaBlockNode(LuaNode[] nodes) {
        this.bodyNodes = nodes;
    }

    @ExplodeLoop
    @Override
    public void executeVoid(VirtualFrame frame) {
        for (LuaNode node : bodyNodes) {
            if (node != null){
                node.executeVoid(frame);
            }
        }
    }

}

