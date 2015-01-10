package org.luatruffle.main.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;

/**
 * Created by lucas on 2014-09-14.
 */
@NodeChildren({@NodeChild("childNode")})
public abstract class LuaUnaryNode extends LuaExpressionNode {

}
