package org.jlua.main.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.statements.controlflow.LuaReturnException;
import org.jlua.main.runtime.LuaNull;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaFunctionBodyNode extends LuaExpressionNode {

    @Child private LuaStatementNode statementNode;

    public LuaFunctionBodyNode(LuaStatementNode statementNode) {
        super();
        this.statementNode = statementNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        try {
            statementNode.executeVoid(frame);
        } catch (LuaReturnException ex) {
            return ex.result;
        }
        return LuaNull.SINGLETON;
    }

}
