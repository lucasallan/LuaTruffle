package org.jlua.main.runtime;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.ExecutionContext;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.instrument.SourceCallback;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jlua.main.builtins.LuaPrintBuiltinFactory;
import org.jlua.main.nodes.LuaNode;
import org.jlua.main.nodes.LuaRootNode;
import org.jlua.main.nodes.LuaStatementNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.nodes.local.LuaReadArgumentNode;
import org.jlua.main.translator.Translator;
import org.luaj.vm2.ast.Chunk;

import java.io.PrintStream;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public class LuaContext extends ExecutionContext {

    private final LuaMethodRegistry luaMethodRegistry;
    private SourceCallback sourceCallback = null;

    public LuaContext() {
        this.luaMethodRegistry = new LuaMethodRegistry();
        installBuiltins();
    }

    public LuaMethod findLuaMethod(String name){
        return luaMethodRegistry.lookup(name);
    }

    public PrintStream getOutput() {
        return System.out;
    }

    private void installBuiltins() {
        installBuiltin(LuaPrintBuiltinFactory.getInstance());
    }

    public void installBuiltin(NodeFactory<? extends LuaNode> factory) {
        int argumentCount = factory.getExecutionSignature().size();
        Object[] argumentNodes = new Object[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            argumentNodes[i] = new LuaReadArgumentNode(i);
        }
        LuaNode builtinBodyNode = factory.createNode(argumentNodes);
        String name = builtinBodyNode.getClass().getAnnotation(NodeInfo.class).shortName();

        LuaRootNode rootNode = new LuaRootNode(builtinBodyNode, null);

        luaMethodRegistry.register(name, rootNode);
    }

    @Override
    public String getLanguageShortName() {
        return "Lua";
    }

    @Override
    protected void setSourceCallback(SourceCallback sourceCallback) {
        this.sourceCallback = sourceCallback;
    }

    public void executeMain(Chunk chunk) {
        final Translator translator = new Translator(this);
        LuaStatementNode statement = (LuaStatementNode) translator.translate(chunk.block);
        LuaFunctionBody body = new LuaFunctionBody(statement);
        LuaRootNode root = new LuaRootNode(body, translator.getFrameDescriptor());
        CallTarget callTarget = Truffle.getRuntime().createCallTarget(root);

        System.out.println(callTarget.call());
    }
}
