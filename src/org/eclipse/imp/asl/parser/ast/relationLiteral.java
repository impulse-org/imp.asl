package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 73:  relationLiteral ::= [$ tuples ]$
 *</b>
 */
public class relationLiteral extends ASTNode implements IrelationLiteral
{
    private tupleLiteralList _tuples;

    /**
     * The value returned by <b>gettuples</b> may be <b>null</b>
     */
    public tupleLiteralList gettuples() { return _tuples; }

    public relationLiteral(IToken leftIToken, IToken rightIToken,
                           tupleLiteralList _tuples)
    {
        super(leftIToken, rightIToken);

        this._tuples = _tuples;
        if (_tuples != null) ((ASTNode) _tuples).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_tuples);
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
        if (! (o instanceof relationLiteral)) return false;
        relationLiteral other = (relationLiteral) o;
        if (_tuples == null)
            if (other._tuples != null) return false;
            else; // continue
        else if (! _tuples.equals(other._tuples)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_tuples == null ? 0 : _tuples.hashCode());
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
            if (_tuples != null) _tuples.accept(v);
        v.endVisit(this);
    }
}


