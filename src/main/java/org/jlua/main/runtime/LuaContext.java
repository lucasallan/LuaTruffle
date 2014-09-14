package org.jlua.main.runtime;

import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.instrument.SourceCallback;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public class LuaContext extends ExecutionContext {
    @Override
    public String getLanguageShortName() {
        return "Lua";
    }

    @Override
    protected void setSourceCallback(SourceCallback sourceCallback) {

    }
}
