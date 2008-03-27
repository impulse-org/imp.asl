/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 20:  ctorDeclaration ::= identifier optFormalArgList
 *</b>
 */
public class ctorDeclaration extends ASTNode implements IctorDeclaration
{
    private identifier _identifier;
    private optFormalArgList _optFormalArgList;

    public identifier getidentifier() { return _identifier; }
    /**
     * The value returned by <b>getoptFormalArgList</b> may be <b>null</b>
     */
    public optFormalArgList getoptFormalArgList() { return _optFormalArgList; }

    public ctorDeclaration(IToken leftIToken, IToken rightIToken,
                           identifier _identifier,
                           optFormalArgList _optFormalArgList)
    {
        super(leftIToken, rightIToken);

        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._optFormalArgList = _optFormalArgList;
        if (_optFormalArgList != null) ((ASTNode) _optFormalArgList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_identifier);
        list.add(_optFormalArgList);
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
        if (! (o instanceof ctorDeclaration)) return false;
        ctorDeclaration other = (ctorDeclaration) o;
        if (! _identifier.equals(other._identifier)) return false;
        if (_optFormalArgList == null)
            if (other._optFormalArgList != null) return false;
            else; // continue
        else if (! _optFormalArgList.equals(other._optFormalArgList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_optFormalArgList == null ? 0 : _optFormalArgList.hashCode());
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
            if (_optFormalArgList != null) _optFormalArgList.accept(v);
        }
        v.endVisit(this);
    }
}


