package org.luatruffle.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.luatruffle.main.nodes.LuaExpressionNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.statements.controlflow.LuaReturnException;
import org.luatruffle.main.runtime.LuaNull;

/**
 * Created by Lucas Allan Amorim on 2014-09-12.
 */
public class LuaFunctionBody extends LuaExpressionNode{

    @Child private LuaStatementNode bodyNode;

    public LuaFunctionBody(LuaStatementNode bodyNode) {
        this.bodyNode = bodyNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        try {
            bodyNode.executeVoid(frame);
        } catch (LuaReturnException ex) {
            return ex.result;
        }

        return LuaNull.SINGLETON;
    }

}
