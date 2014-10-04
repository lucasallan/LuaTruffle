package org.jlua.main.nodes.call;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.UnsupportedSpecializationException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.runtime.LuaFunction;

/**
 * Created by Lucas Allan Amorim on 2014-09-18.
 */
public final class LuaFunctionCall extends LuaExpressionNode {

    @Child protected LuaExpressionNode functionNode;
    @Children protected final LuaExpressionNode[] argumentNodes;
    @Child protected LuaAbstractDispatchNode dispatchNode;

    public LuaFunctionCall(LuaExpressionNode[] argumentNodes, LuaExpressionNode functionNode, LuaAbstractDispatchNode abstractDispatchNode) {
        this.argumentNodes = argumentNodes;
        this.functionNode = functionNode;
        this.dispatchNode = abstractDispatchNode;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        try {
            LuaFunction method = functionNode.executeLuaMethod(frame);
            CompilerAsserts.compilationConstant(argumentNodes.length);

            Object[] argumentValues = new Object[argumentNodes.length];
            for (int i = 0; i < argumentNodes.length; i++) {
                argumentValues[i] = argumentNodes[i].execute(frame);
            }
            return dispatchNode.executeDispatch(frame, method, argumentValues);
        } catch (UnexpectedResultException ex) {
            throw new UnsupportedSpecializationException(this, new Node[]{functionNode}, ex.getResult());
        }

    }
}
