package org.luatruffle.main.nodes.statements.controlflow;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaReturnException extends ControlFlowException{
    public final Object result;

    public LuaReturnException(Object result) {
        super();
        this.result = result;
    }
}
