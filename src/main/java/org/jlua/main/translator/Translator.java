package org.jlua.main.translator;

import org.jlua.main.nodes.LuaNode;
import org.luaj.vm2.ast.*;
import org.luaj.vm2.ast.Stat.LocalAssign;

import java.util.List;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */

public class Translator extends Visitor {

    @Override
    public void visit(Stat.Assign assign) {
        visitLuaNode(assign);
    }

    @Override
    public void visit(Stat.Break aBreak) {
        visitLuaNode(aBreak);
    }

    @Override
    public void visit(Stat.FuncCallStat funcCallStat) {
        visitLuaNode(funcCallStat);
    }

    @Override
    public void visit(Stat.FuncDef funcDef) {
        visitLuaNode(funcDef);
    }

    @Override
    public void visit(Stat.GenericFor genericFor) {
        visitLuaNode(genericFor);
    }

    @Override
    public void visit(Stat.IfThenElse ifThenElse) {
        visitLuaNode(ifThenElse);
    }

    @Override
    public void visit(LocalAssign localAssign) {
        visitLuaNode(localAssign);
    }

    @Override
    public void visit(Stat.LocalFuncDef localFuncDef) {
        visitLuaNode(localFuncDef);
    }

    @Override
    public void visit(Stat.NumericFor numericFor) {
        visitLuaNode(numericFor);
    }

    @Override
    public void visit(Stat.RepeatUntil repeatUntil) {
        System.err.println("Visitor: " +repeatUntil.getClass().getName());
        visitLuaNode(repeatUntil);
    }

    @Override
    public void visit(Stat.Return aReturn) {
        visitLuaNode(aReturn);
    }

    @Override
    public void visit(Stat.WhileDo whileDo) {
        visitLuaNode(whileDo);
    }

    @Override
    public void visit(FuncBody funcBody) {
        visitLuaNode(funcBody);
    }

    @Override
    public void visit(FuncArgs funcArgs) {
        visitLuaNode(funcArgs);
    }

    @Override
    public void visit(TableField tableField) {
        visitLuaNode(tableField);
    }

    @Override
    public void visit(Exp.AnonFuncDef anonFuncDef) {
        visitLuaNode(anonFuncDef);
    }

    @Override
    public void visit(Exp.BinopExp binopExp) {
        visitLuaNode(binopExp);
    }

    @Override
    public void visit(Exp.Constant constant) {
        visitLuaNode(constant);
    }

    @Override
    public void visit(Exp.FieldExp fieldExp) {
        visitLuaNode(fieldExp);
    }

    @Override
    public void visit(Exp.FuncCall funcCall) {
        visitLuaNode(funcCall);
    }

    @Override
    public void visit(Exp.IndexExp indexExp) {
        visitLuaNode(indexExp);
    }

    @Override
    public void visit(Exp.MethodCall methodCall) {
        visitLuaNode(methodCall);
    }

    @Override
    public void visit(Exp.NameExp nameExp) {
        visitLuaNode(nameExp);
    }

    @Override
    public void visit(Exp.ParensExp parensExp) {
        visitLuaNode(parensExp);
    }

    @Override
    public void visit(Exp.UnopExp unopExp) {
        visitLuaNode(unopExp);
    }

    @Override
    public void visit(Exp.VarargsExp varargsExp) {
        visitLuaNode(varargsExp);
    }

    @Override
    public void visit(ParList parList) {
        visitLuaNode(parList);
    }

    @Override
    public void visit(TableConstructor tableConstructor) {
        visitLuaNode(tableConstructor);
    }

    @Override
    public void visitVars(List list) {
        visitLuaNode(list);
    }

    @Override
    public void visitExps(List list) {
        visitLuaNode(list);
    }

    @Override
    public void visitNames(List list) {
        visitLuaNode(list);
    }

    @Override
    public void visit(Name name) {
        visitLuaNode(name);
    }

    @Override
    public void visit(String s) {
        visitLuaNode(s);
    }

    @Override
    public void visit(NameScope nameScope) {
        visitLuaNode(nameScope);
    }

    @Override
    public void visit(Stat.Goto aGoto) {
        visitLuaNode(aGoto);
    }

    @Override
    public void visit(Stat.Label label) {
        visitLuaNode(label);
    }

    private void visitLuaNode(Stat node) {
        System.err.println("Visitor: " +node.getClass().getName() + " Line: " + node.beginLine);
    }

    private void visitLuaNode(NameScope node){
        if (node != null) {
            System.err.println("Visitor: " + node.getClass().getName());
        }
    }

    private void visitLuaNode(Chunk node) {
        node.accept(this);
    }
    private void visitLuaNode(Object object) {
        System.err.println("Visitor: " +object.getClass().getName());
    }

}
