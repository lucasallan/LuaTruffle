package org.jlua.main.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public class LuaRootNode extends RootNode {

    @Child protected LuaNode body;

    public LuaRootNode(LuaNode body, FrameDescriptor frameDescriptor) {
        super(null, frameDescriptor);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.execute(frame);
    }

}
