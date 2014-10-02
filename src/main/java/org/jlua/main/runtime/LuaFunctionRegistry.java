package org.jlua.main.runtime;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import org.jlua.main.nodes.LuaRootNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public final class LuaFunctionRegistry {

    private final Map<String, LuaFunction> methods = new HashMap<>();

    public LuaFunction lookup(String name) {
        CompilerAsserts.neverPartOfCompilation();

        LuaFunction result = methods.get(name);
        if (result == null) {
            result = new LuaFunction(name);
            methods.put(name, result);
        }
        return result;
    }

    public void register(String name, LuaRootNode rootNode, String[] params) {
        CompilerAsserts.neverPartOfCompilation();

        LuaFunction method = lookup(name);
        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);
        method.setCallTarget(callTarget);
    }

    public void register(String name, LuaRootNode rootNode) {
        CompilerAsserts.neverPartOfCompilation();

        LuaFunction method = lookup(name);
        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);
        method.setCallTarget(callTarget);
    }
}
