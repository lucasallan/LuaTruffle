package org.luatruffle.main.runtime;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.luatruffle.main.builtins.LuaPrintBuiltinFactory;
import org.luatruffle.main.builtins.LuaOSClockBuiltinFactory;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaRootNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.expressions.LuaFunctionBody;
import org.luatruffle.main.nodes.local.LuaReadArgumentNode;
import org.luatruffle.main.translator.Translator;
import org.luaj.vm2.ast.Chunk;

import java.io.PrintStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public class LuaContext extends ExecutionContext {

    private final LuaFunctionRegistry luaFunctionRegistry;
    private final LuaFunctionRegistry luaGlobalVariableRegistry;

    public LuaContext() {
        this.luaFunctionRegistry = new LuaFunctionRegistry();
        this.luaGlobalVariableRegistry = new LuaFunctionRegistry();
        installBuiltins();
    }

    public LuaFunction findLuaMethod(String name){
        return luaFunctionRegistry.lookup(name);
    }
    public LuaFunction findVariable(String name){
        return luaGlobalVariableRegistry.lookup(name);
    }

    public void addLuaFunction(String name, LuaRootNode node) {
        luaFunctionRegistry.register(name, node);
    }

    public void addGlobalVariable(String name, LuaRootNode node) {
        luaGlobalVariableRegistry.register(name, node);
    }

    public PrintStream getOutput() {
        return System.out;
    }

    private void installBuiltins() {
        installBuiltin(LuaPrintBuiltinFactory.getInstance());
        installBuiltin(LuaOSClockBuiltinFactory.getInstance());
    }

    public void installBuiltin(NodeFactory<? extends LuaNode> factory) {
        int argumentCount = factory.getExecutionSignature().size();
        Object[] argumentNodes = new Object[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            argumentNodes[i] = new LuaReadArgumentNode(i);
        }
        for (int i = 0; i < argumentCount; i++) {
            argumentNodes[i] = new LuaReadArgumentNode(i);
        }

        LuaNode builtinBodyNode = factory.createNode(argumentNodes);
        String name = builtinBodyNode.getClass().getAnnotation(NodeInfo.class).shortName();

        LuaRootNode rootNode = new LuaRootNode(builtinBodyNode, null);

        luaFunctionRegistry.register(name, rootNode);
    }

    @Override
    public String getLanguageShortName() {
        return "Lua";
    }

    public void executeMain(Chunk chunk) {
        final Translator translator = new Translator(this);
        LuaStatementNode statement = (LuaStatementNode) translator.translate(chunk.block);
        LuaFunctionBody body = new LuaFunctionBody(statement);
        LuaRootNode root = new LuaRootNode(body, translator.getFrameDescriptor());
        CallTarget callTarget = Truffle.getRuntime().createCallTarget(root);

        callTarget.call();
    }
}
