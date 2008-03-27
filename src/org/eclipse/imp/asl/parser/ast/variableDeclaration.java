package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 41:  variableDeclaration ::= typeName identifier value
 *</b>
 */
public class variableDeclaration extends ASTNode implements IvariableDeclaration
{
    private ItypeName _typeName;
    private identifier _identifier;
    private Ivalue _value;

    public ItypeName gettypeName() { return _typeName; }
    public identifier getidentifier() { return _identifier; }
    public Ivalue getvalue() { return _value; }

    public variableDeclaration(IToken leftIToken, IToken rightIToken,
                               ItypeName _typeName,
                               identifier _identifier,
                               Ivalue _value)
    {
        super(leftIToken, rightIToken);

        this._typeName = _typeName;
        ((ASTNode) _typeName).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
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
        list.add(_typeName);
        list.add(_identifier);
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
        if (! (o instanceof variableDeclaration)) return false;
        variableDeclaration other = (variableDeclaration) o;
        if (! _typeName.equals(other._typeName)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _value.equals(other._value)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_typeName.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
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
            _typeName.accept(v);
            _identifier.accept(v);
            _value.accept(v);
        }
        v.endVisit(this);
    }
}


