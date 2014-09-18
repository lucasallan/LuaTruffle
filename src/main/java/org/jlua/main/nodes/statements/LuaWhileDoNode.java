package org.jlua.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.statements.controlflow.LuaBreakException;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
@NodeInfo(shortName = "while")
public class LuaWhileDoNode extends LuaStatementNode {

    @Child
    private LuaExpressionNode conditionNode;
    @Child
    private LuaNode blockNode;

    public LuaWhileDoNode(LuaExpressionNode conditionNode, LuaNode blockNode) {
        this.conditionNode = conditionNode;
        this.blockNode = blockNode;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            while (conditionNode.executeBoolean(frame)) {
                blockNode.executeVoid(frame);
            }
        }
        catch (LuaBreakException ex) {
            // TODO do I need to do something here?
        } catch (UnexpectedResultException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
