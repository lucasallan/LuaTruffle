package org.jlua.main.nodes.call;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.jlua.main.runtime.LuaFunction;

/**
 * Created by Lucas Allan Amorim on 2014-09-21.
 */
public abstract class LuaAbstractDispatchNode extends Node {

    protected static final int INLINE_CACHE_SIZE = 4;

    protected abstract Object executeDispatch(VirtualFrame frame, LuaFunction function, Object[] arguments);
}
