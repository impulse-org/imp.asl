
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
        if (! (o instanceof relationLiteral)) return false;
        if (! super.equals(o)) return false;
        relationLiteral other = (relationLiteral) o;
        if (_tuples == null)
            if (other._tuples != null) return false;
            else; // continue
        else if (! _tuples.equals(other._tuples)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
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


