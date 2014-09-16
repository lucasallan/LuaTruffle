package org.jlua.main.builtins;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jlua.main.nodes.LuaExpressionNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
@NodeChild(value = "arguments", type = LuaExpressionNode[].class)
public abstract class LuaBuiltinNode extends LuaExpressionNode {

}
