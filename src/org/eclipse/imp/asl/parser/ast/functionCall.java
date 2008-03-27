package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 85:  functionCall ::= identifier ($ values )$
 *</b>
 */
public class functionCall extends ASTNode implements IfunctionCall
{
    private identifier _identifier;
    private valueList _values;

    public identifier getidentifier() { return _identifier; }
    /**
     * The value returned by <b>getvalues</b> may be <b>null</b>
     */
    public valueList getvalues() { return _values; }

    public functionCall(IToken leftIToken, IToken rightIToken,
                        identifier _identifier,
                        valueList _values)
    {
        super(leftIToken, rightIToken);

        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._values = _values;
        if (_values != null) ((ASTNode) _values).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_identifier);
        list.add(_values);
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
        if (! (o instanceof functionCall)) return false;
        functionCall other = (functionCall) o;
        if (! _identifier.equals(other._identifier)) return false;
        if (_values == null)
            if (other._values != null) return false;
            else; // continue
        else if (! _values.equals(other._values)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_values == null ? 0 : _values.hashCode());
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
            _identifier.accept(v);
            if (_values != null) _values.accept(v);
        }
        v.endVisit(this);
    }
}


