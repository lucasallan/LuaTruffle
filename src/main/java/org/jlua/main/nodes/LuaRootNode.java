package org.jlua.main.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public abstract class LuaRootNode extends Node {

	public abstract void executeVoid(VirtualFrame frame);
}
