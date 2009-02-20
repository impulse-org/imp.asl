
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
 *<li>Rule 106:  relationSpecifier ::= relation [$ typeSpecifierList ]$
 *</b>
 */
public class relationSpecifier extends ASTNode implements IrelationSpecifier
{
    private ASTNodeToken _relation;
    private typeSpecifierList _typeSpecifierList;

    public ASTNodeToken getrelation() { return _relation; }
    public typeSpecifierList gettypeSpecifierList() { return _typeSpecifierList; }

    public relationSpecifier(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _relation,
                             typeSpecifierList _typeSpecifierList)
    {
        super(leftIToken, rightIToken);

        this._relation = _relation;
        ((ASTNode) _relation).setParent(this);
        this._typeSpecifierList = _typeSpecifierList;
        ((ASTNode) _typeSpecifierList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_relation);
        list.add(_typeSpecifierList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof relationSpecifier)) return false;
        if (! super.equals(o)) return false;
        relationSpecifier other = (relationSpecifier) o;
        if (! _relation.equals(other._relation)) return false;
        if (! _typeSpecifierList.equals(other._typeSpecifierList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_relation.hashCode());
        hash = hash * 31 + (_typeSpecifierList.hashCode());
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
            _relation.accept(v);
            _typeSpecifierList.accept(v);
        }
        v.endVisit(this);
    }
}


