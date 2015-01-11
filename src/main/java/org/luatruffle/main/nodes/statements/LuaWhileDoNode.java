package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.*;
import org.luatruffle.main.nodes.LuaExpressionNode;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.statements.controlflow.LuaBreakException;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
@NodeInfo(shortName = "while")
public class LuaWhileDoNode extends LuaStatementNode {

    @Child protected LoopNode loopNode;

    public LuaWhileDoNode(LuaExpressionNode conditionNode, LuaNode blockNode) {
        loopNode = Truffle.getRuntime().createLoopNode(new RepeatingWhileNode(conditionNode, blockNode));
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            loopNode.executeLoop(frame);
        } catch (LuaBreakException e) {
        }
    }

    private static class RepeatingWhileNode extends Node implements RepeatingNode {

        @Child private LuaExpressionNode conditionNode;
        @Child private LuaNode blockNode;

        public RepeatingWhileNode(LuaExpressionNode conditionNode, LuaNode blockNode) {
            this.conditionNode = conditionNode;
            this.blockNode = blockNode;
        }

        @Override
        public boolean executeRepeating(VirtualFrame frame) {
            try {
                if (!conditionNode.executeBoolean(frame)) {
                    return false;
                }
            } catch (UnexpectedResultException e) {
                throw new UnsupportedOperationException(e);
            }

            blockNode.executeVoid(frame);

            return true;
        }
    }

}
