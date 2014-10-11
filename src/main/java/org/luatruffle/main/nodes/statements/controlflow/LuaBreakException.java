package org.luatruffle.main.nodes.statements.controlflow;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public class LuaBreakException extends ControlFlowException {

    public static final LuaBreakException SINGLETON = new LuaBreakException();

    private LuaBreakException() {}
}
