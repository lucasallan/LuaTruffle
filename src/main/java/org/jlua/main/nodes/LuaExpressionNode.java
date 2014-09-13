package org.jlua.main.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import org.jlua.main.nodes.LuaTypesGen;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public abstract class LuaExpressionNode extends LuaNode {

	public void executeVoid(VirtualFrame frame) {
		execute(frame);
	}

}
