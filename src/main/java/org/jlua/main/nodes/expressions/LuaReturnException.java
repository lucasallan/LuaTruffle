package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public class LuaReturnException extends ControlFlowException{
    public Object result;

    public LuaReturnException(Object result) {
        super();
        this.result = result;
    }
}
