
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
 *<li>Rule 79:  tupleSlot ::= value .$ identifier
 *</b>
 */
public class tupleSlot extends ASTNode implements ItupleSlot
{
    private Ivalue _value;
    private identifier _identifier;

    public Ivalue getvalue() { return _value; }
    public identifier getidentifier() { return _identifier; }

    public tupleSlot(IToken leftIToken, IToken rightIToken,
                     Ivalue _value,
                     identifier _identifier)
    {
        super(leftIToken, rightIToken);

        this._value = _value;
        ((ASTNode) _value).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_value);
        list.add(_identifier);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof tupleSlot)) return false;
        if (! super.equals(o)) return false;
        tupleSlot other = (tupleSlot) o;
        if (! _value.equals(other._value)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_value.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
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
            _value.accept(v);
            _identifier.accept(v);
        }
        v.endVisit(this);
    }
}


