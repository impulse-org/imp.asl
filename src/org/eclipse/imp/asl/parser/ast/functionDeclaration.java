package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 10:  functionDeclaration ::= functionHeader =$ value
 *</b>
 */
public class functionDeclaration extends ASTNode implements IfunctionDeclaration
{
    private functionHeader _functionHeader;
    private Ivalue _value;

    public functionHeader getfunctionHeader() { return _functionHeader; }
    public Ivalue getvalue() { return _value; }

    public functionDeclaration(IToken leftIToken, IToken rightIToken,
                               functionHeader _functionHeader,
                               Ivalue _value)
    {
        super(leftIToken, rightIToken);

        this._functionHeader = _functionHeader;
        ((ASTNode) _functionHeader).setParent(this);
        this._value = _value;
        ((ASTNode) _value).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_functionHeader);
        list.add(_value);
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
        if (! (o instanceof functionDeclaration)) return false;
        functionDeclaration other = (functionDeclaration) o;
        if (! _functionHeader.equals(other._functionHeader)) return false;
        if (! _value.equals(other._value)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_functionHeader.hashCode());
        hash = hash * 31 + (_value.hashCode());
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
            _functionHeader.accept(v);
            _value.accept(v);
        }
        v.endVisit(this);
    }
}


