package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 17:  algebraicTypeDeclaration ::= atype$ identifier =$ ctorList
 *</b>
 */
public class algebraicTypeDeclaration extends ASTNode implements IalgebraicTypeDeclaration
{
    private identifier _identifier;
    private ctorDeclarationList _ctorList;

    public identifier getidentifier() { return _identifier; }
    public ctorDeclarationList getctorList() { return _ctorList; }

    public algebraicTypeDeclaration(IToken leftIToken, IToken rightIToken,
                                    identifier _identifier,
                                    ctorDeclarationList _ctorList)
    {
        super(leftIToken, rightIToken);

        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._ctorList = _ctorList;
        ((ASTNode) _ctorList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_identifier);
        list.add(_ctorList);
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
        if (! (o instanceof algebraicTypeDeclaration)) return false;
        algebraicTypeDeclaration other = (algebraicTypeDeclaration) o;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _ctorList.equals(other._ctorList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_ctorList.hashCode());
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
            _ctorList.accept(v);
        }
        v.endVisit(this);
    }
}


