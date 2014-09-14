package org.jlua.main.runtime;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import org.jlua.main.nodes.LuaRootNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public final class LuaMethodRegistry {

    private final Map<String, LuaMethod> methods = new HashMap<>();

    public LuaMethod lookup(String name) {
        LuaMethod result = methods.get(name);
        if (result == null) {
            result = new LuaMethod(name);
            methods.put(name, result);
        }
        return result;
    }

    public void register(String name, LuaRootNode rootNode) {
        LuaMethod method = lookup(name);
        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);
        method.setCallTarget(callTarget);
    }
}
