package org.luatruffle.main.nodes.call;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import org.luatruffle.main.runtime.LuaFunction;

/**
 * Created by Lucas Allan Amorim on 2014-09-24.
 */
public class LuaGenericDispatchNode extends LuaAbstractDispatchNode {

    @Child private IndirectCallNode callNode = Truffle.getRuntime().createIndirectCallNode();

    @Override
    protected Object executeDispatch(VirtualFrame frame, LuaFunction function, Object[] arguments) {
        return callNode.call(frame, function.getCallTarget(), arguments);
    }
}
