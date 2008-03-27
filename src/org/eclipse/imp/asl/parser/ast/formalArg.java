package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 25:  formalArg ::= typeName$type identifier$name
 *</b>
 */
public class formalArg extends ASTNode implements IformalArg
{
    private ItypeName _type;
    private identifier _name;

    public ItypeName gettype() { return _type; }
    public identifier getname() { return _name; }

    public formalArg(IToken leftIToken, IToken rightIToken,
                     ItypeName _type,
                     identifier _name)
    {
        super(leftIToken, rightIToken);

        this._type = _type;
        ((ASTNode) _type).setParent(this);
        this._name = _name;
        ((ASTNode) _name).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_type);
        list.add(_name);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof formalArg)) return false;
        formalArg other = (formalArg) o;
        if (! _type.equals(other._type)) return false;
        if (! _name.equals(other._name)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_type.hashCode());
        hash = hash * 31 + (_name.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
        {
            _type.accept(v);
            _name.accept(v);
        }
        v.endVisit(this);
    }
}


