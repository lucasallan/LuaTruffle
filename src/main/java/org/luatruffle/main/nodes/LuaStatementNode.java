package org.luatruffle.main.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public abstract class LuaStatementNode extends LuaNode {

    @Override
    public Object execute(VirtualFrame frame) {
        final String message = "Statements never return results";
        CompilerAsserts.neverPartOfCompilation(message);
        throw new UnsupportedOperationException(message);
    }

}
