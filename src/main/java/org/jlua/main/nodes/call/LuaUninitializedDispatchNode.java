package org.jlua.main.nodes.call;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.jlua.main.runtime.LuaMethod;

/**
 * Created by Lucas Allan Amorim on 2014-09-24.
 */
public final class LuaUninitializedDispatchNode extends LuaAbstractDispatchNode {
    @Override
    protected Object executeDispatch(VirtualFrame frame, LuaMethod function, Object[] arguments) {
        CompilerDirectives.transferToInterpreterAndInvalidate();

        Node cur = this;
        int depth = 0;
        while (cur.getParent() instanceof LuaAbstractDispatchNode) {
            cur = cur.getParent();
            depth++;
        }
        LuaFunctionCall methodNode = (LuaFunctionCall) cur.getParent();

        LuaAbstractDispatchNode replacement;
        if (function.getCallTarget() == null) {
            throw new UnsupportedOperationException("Call of undefined function: " + function.getName());

        } else if (depth < INLINE_CACHE_SIZE) {
            LuaAbstractDispatchNode next = new LuaUninitializedDispatchNode();
            replacement = new LuaDirectDispatchNode(next, function);
            replace(replacement);

        } else {
            replacement = new LuaGenericDispatchNode();
            methodNode.dispatchNode.replace(replacement);
        }

        return replacement.executeDispatch(frame, function, arguments);
    }
}
