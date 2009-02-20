
////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2007 IBM Corporation.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//    Philippe Charles (pcharles@us.ibm.com) - initial API and implementation

////////////////////////////////////////////////////////////////////////////////

package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 80:  algebraicTypeValue ::= identifier optArgList
 *</b>
 */
public class algebraicTypeValue extends ASTNode implements IalgebraicTypeValue
{
    private identifier _identifier;
    private optArgList _optArgList;

    public identifier getidentifier() { return _identifier; }
    /**
     * The value returned by <b>getoptArgList</b> may be <b>null</b>
     */
    public optArgList getoptArgList() { return _optArgList; }

    public algebraicTypeValue(IToken leftIToken, IToken rightIToken,
                              identifier _identifier,
                              optArgList _optArgList)
    {
        super(leftIToken, rightIToken);

        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._optArgList = _optArgList;
        if (_optArgList != null) ((ASTNode) _optArgList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_identifier);
        list.add(_optArgList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof algebraicTypeValue)) return false;
        if (! super.equals(o)) return false;
        algebraicTypeValue other = (algebraicTypeValue) o;
        if (! _identifier.equals(other._identifier)) return false;
        if (_optArgList == null)
            if (other._optArgList != null) return false;
            else; // continue
        else if (! _optArgList.equals(other._optArgList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_optArgList == null ? 0 : _optArgList.hashCode());
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
            if (_optArgList != null) _optArgList.accept(v);
        }
        v.endVisit(this);
    }
}


