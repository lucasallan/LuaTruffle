package org.jlua.main.translator;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import org.jlua.main.nodes.*;
import org.jlua.main.nodes.call.LuaFunctionCall;
import org.jlua.main.nodes.call.LuaUninitializedDispatchNode;
import org.jlua.main.nodes.expressions.LuaFunctionBody;
import org.jlua.main.nodes.expressions.LuaFunctionNode;
import org.jlua.main.nodes.local.LuaReadArgumentNode;
import org.jlua.main.nodes.local.LuaReadLocalVariableNodeFactory;
import org.jlua.main.nodes.local.LuaWriteLocalVariableNode;
import org.jlua.main.nodes.local.LuaWriteLocalVariableNodeFactory;
import org.jlua.main.nodes.operations.arithmetic.*;
import org.jlua.main.nodes.operations.relational.*;
import org.jlua.main.nodes.statements.*;
import org.jlua.main.runtime.LuaContext;
import org.jlua.main.runtime.LuaFunction;
import org.jlua.main.runtime.LuaNull;
import org.luaj.vm2.ast.*;
import org.luaj.vm2.ast.Stat.LocalAssign;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */

public class Translator extends Visitor {

    private FrameDescriptor frameDescriptor;
    private LuaContext context;
    private Object rootNode;

    public Translator(LuaContext context) {
        frameDescriptor = new FrameDescriptor();
        this.context = context;
    }

    // TODO needs a good cleaup
    public Object translate(Object object) {
        if (object instanceof Chunk) {
            return visitChunk((Chunk) object);
        } else if (object instanceof Block) {
            return visitBlock((Block) object);
        } else if (object instanceof Stat.IfThenElse) {
            return visitIfThenElse((Stat.IfThenElse) object);
        } else if (object instanceof Exp.BinopExp) {
            return visitBinoExp((Exp.BinopExp) object);
        } else if (object instanceof Stat.Return) {
            return visitReturn((Stat.Return) object);
        } else if (object instanceof Exp.Constant) {
            return visitConstant((Exp.Constant) object);
        } else if (object instanceof Stat.FuncDef) {
            return visitFuncDef((Stat.FuncDef) object);
        } else if (object instanceof Stat.FuncCallStat) {
            return visitFuncCallStat((Stat.FuncCallStat) object);
        } else if (object instanceof Exp.ParensExp) {
            return visitParensExp((Exp.ParensExp) object);
        } else if (object instanceof Stat.WhileDo) {
            return visitWhileDo((Stat.WhileDo) object);
        } else if (object instanceof Stat.LocalAssign) {
            return visitLocalAssign((Stat.LocalAssign) object);
        } else if (object instanceof Exp.NameExp) {
            return visitLocalNameExp((Exp.NameExp) object);
        } else if (object instanceof Exp.FuncCall) {
            return visitFuncCall((Exp.FuncCall) object);
        } else {
            if (object != null) {
                System.err.println("Needs be handled: " + object.getClass().getName());
            }
            return rootNode;
        }

    }

    private Object visitLocalNameExp(Exp.NameExp nameExp) {
        return visitName(nameExp.name);
    }

    private Object visitName(Name name) {
        final FrameSlot frameSlot = frameDescriptor.findFrameSlot(name.name);

        if (frameSlot == null) {
            throw new RuntimeException(String.format("Name '%s' not found in translator", name.name));
        }

        return LuaReadLocalVariableNodeFactory.create(frameSlot);
    }

    private Object visitWhileDo(Stat.WhileDo whileDo) {
        LuaExpressionNode expression = (LuaExpressionNode) translate(whileDo.exp);
        LuaBlockNode whileBlock = (LuaBlockNode) translate(whileDo.block);
        return new LuaWhileDoNode(expression, whileBlock);
    }

    public Object visitIfThenElse(Stat.IfThenElse ifThenElse) {

        LuaExpressionNode expression = (LuaExpressionNode) translate(ifThenElse.ifexp);
        LuaNode ifBlock;
        LuaNode elseBlock;

        if (ifThenElse.ifblock != null) {
            LuaNode[] ifBlocks = new LuaNode[ifThenElse.ifblock.stats.size()];
            for (int i = 0; i < ifThenElse.ifblock.stats.size(); i++) {
                ifBlocks[i] = (LuaNode) translate(ifThenElse.ifblock.stats.get(i));
            }
            ifBlock = new LuaBlockNode(ifBlocks);
        } else {
            ifBlock = new LuaNopNode();
        }

        if (ifThenElse.elseblock != null) {
            LuaNode[] elseBlocks = new LuaNode[ifThenElse.elseblock.stats.size()];
            for (int i = 0; i < ifThenElse.elseblock.stats.size(); i++) {
                elseBlocks[i] = (LuaNode) translate(ifThenElse.elseblock.stats.get(i));
            }
            elseBlock = new LuaBlockNode(elseBlocks);
        } else {
            elseBlock = new LuaNopNode();
        }

        // TODO Handle elseIfs
        return new LuaIfNode(expression, ifBlock, elseBlock);
    }

    public LuaNode visitChunk(Chunk chunk) {
        return visitBlock(chunk.block);
    }

    public LuaNode visitBlock(Block block){
        //visit(block.scope);
        LuaNode blockNode;

        if ( block.stats != null ) {
            LuaNode blocks[] = new LuaNode[block.stats.size()];

            for (int i = 0, n = block.stats.size(); i < n; i++) {
                Object returnedBlock = translate(block.stats.get(i));
                if (returnedBlock instanceof LuaNode) {
                    blocks[i] = (LuaNode) returnedBlock;
                }
            }

            blockNode = new LuaBlockNode(blocks);
        } else {
            blockNode = new LuaNopNode();
        }

        setRootBlock(blockNode);
        return blockNode;
    }

    public Object visitFuncDef(Stat.FuncDef funcDef) {
        final LuaWriteLocalVariableNode[] paramsIntoLocals = new LuaWriteLocalVariableNode[funcDef.body.parlist.names.size()];
        for(int i = 0; i < funcDef.body.parlist.names.size(); i++) {
            Name paramName = (Name) funcDef.body.parlist.names.get(i);
            paramsIntoLocals[i] = addFormalParameter(paramName.name, i);
        }
        LuaBlockNode prelude = new LuaBlockNode(paramsIntoLocals);
        LuaStatementNode body = (LuaStatementNode) translate(funcDef.body.block);
        LuaBlockNode preludeAndBody = new LuaBlockNode(new LuaNode[]{prelude, body});
        LuaFunctionBody methodBody = new LuaFunctionBody(preludeAndBody);
        LuaRootNode root = new LuaRootNode(methodBody, getFrameDescriptor());
        String name = funcDef.name.name.name;
        context.addLuaMethod(name, root);
        return root;
    }

    public LuaWriteLocalVariableNode addFormalParameter(String nameToken, int parameterCount) {
        final LuaReadArgumentNode readArg = new LuaReadArgumentNode(parameterCount);
        return LuaWriteLocalVariableNodeFactory.create(readArg, getFrameDescriptor().findOrAddFrameSlot(nameToken));
    }

    public Object visitFuncCallStat(Stat.FuncCallStat funcCallStat) {
        return visitFuncCall(funcCallStat.funccall);
    }

    public Object visitFuncCall(Exp.FuncCall funcCall) {
        if (funcCall.isfunccall()) {
            // Calling a function
            Exp.NameExp nameExp = (Exp.NameExp) funcCall.lhs;
            List<LuaExpressionNode> arguments = visitFuncArgs(funcCall.args);
            LuaFunction method = context.findLuaMethod(nameExp.name.name);
            return new LuaFunctionCall(arguments.toArray(new LuaExpressionNode[arguments.size()]), new LuaFunctionNode(method), new LuaUninitializedDispatchNode());
        }
        throw new UnsupportedOperationException(String.valueOf("FuncCallStat"));
    }

    public List<LuaExpressionNode> visitFuncArgs(FuncArgs funcArgs) {
        List<LuaExpressionNode> params = new ArrayList<LuaExpressionNode>();
        if (funcArgs.exps != null) {
            for (Object object : funcArgs.exps) {
                //LuaReadArgumentNode
                LuaExpressionNode node = (LuaExpressionNode) translate(object);
                params.add(node);
            }
        }
        return params;
    }

    public Object visitLocalAssign(LocalAssign localAssign) {

        // For now, returns only the first assignment
        for(int i = 0; i< localAssign.values.size(); i++) {
            LuaExpressionNode luaExpressionNode = (LuaExpressionNode) translate(localAssign.values.get(i));
            return LuaWriteLocalVariableNodeFactory.create(luaExpressionNode, frameDescriptor.findOrAddFrameSlot(((Name) localAssign.names.get(i)).name));
        }

        throw new UnsupportedOperationException(String.valueOf(localAssign));
    }

    public LuaReturnNode visitReturn(Stat.Return aReturn) {
        //System.out.println(aReturn.nreturns()); //might have more than one return
        Object objectReturned = translate(aReturn.values.get(0));
        return new LuaReturnNode((LuaNode) objectReturned);
    }

    @Override
    public void visit(FuncBody funcBody) {
        funcBody.block.accept(this);
        funcBody.parlist.accept(this);
     }

    public Object visitBinoExp(Exp.BinopExp binopExp) {
        LuaExpressionNode left = (LuaExpressionNode) translate( binopExp.lhs);
        LuaExpressionNode right = (LuaExpressionNode) translate(binopExp.rhs);

        switch (binopExp.op) {
            case 13:
                return LuaAddNodeFactory.create(left, right);
            case 24:
               return LuaEqualsNodeFactory.create(left, right);
            case 61:
                return LuaEqualsNodeFactory.create(left, right);
            case 63:
                return LuaGreaterThanNodeFactory.create(left, right);
            case 62:
                return LuaGreaterOrEqualsNodeFactory.create(left, right);
            case 26:
                return LuaLessOrEqualsNodeFactory.create(left, right);
            case 25:
                return LuaLessThanNodeFactory.create(left, right);
            case 15:
                return LuaMultiplicationNodeFactory.create(left, right);
            case 16:
                return LuaDivisionOperationFactory.create(left, right);
            case 14:
                return LuaSubtractionNodeFactory.create(left, right);
            case 18:
                return LuaExponentiationNodeFactory.create(left, right);
        }
        throw new UnsupportedOperationException(String.valueOf(binopExp.op));
    }

    public LuaNode visitConstant(Exp.Constant constant){
        if (constant.value.typename().equals("number")){
            return new LuaLongConstantNode(constant.value.checklong());
        } else if (constant.value.typename().equals("nil")) {
            return new LuaObjectConstantNode(LuaNull.SINGLETON);
        } else if (constant.value.typename().equals("string")){
            return new LuaObjectConstantNode(constant.value.toString());
        }
        // needs to handle others lua types
        throw  new UnsupportedOperationException(constant.value.typename());
    }

    public Object visitParensExp(Exp.ParensExp parensExp) {
        return translate(parensExp.exp);
    }

    public void setRootBlock(Object object) {
        if (rootNode == null) {
            this.rootNode = object;
        }
    }

    public FrameDescriptor getFrameDescriptor() {
        return frameDescriptor;
    }
}
