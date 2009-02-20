
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
 *<li>Rule 93:  caseSpec ::= identifier =$ value :$ value
 *</b>
 */
public class caseSpec0 extends ASTNode implements IcaseSpec
{
    private identifier _identifier;
    private Ivalue _value;
    private Ivalue _value5;

    public identifier getidentifier() { return _identifier; }
    public Ivalue getvalue() { return _value; }
    public Ivalue getvalue5() { return _value5; }

    public caseSpec0(IToken leftIToken, IToken rightIToken,
                     identifier _identifier,
                     Ivalue _value,
                     Ivalue _value5)
    {
        super(leftIToken, rightIToken);

        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._value = _value;
        ((ASTNode) _value).setParent(this);
        this._value5 = _value5;
        ((ASTNode) _value5).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_identifier);
        list.add(_value);
        list.add(_value5);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof caseSpec0)) return false;
        if (! super.equals(o)) return false;
        caseSpec0 other = (caseSpec0) o;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _value.equals(other._value)) return false;
        if (! _value5.equals(other._value5)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_value.hashCode());
        hash = hash * 31 + (_value5.hashCode());
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
            _value.accept(v);
            _value5.accept(v);
        }
        v.endVisit(this);
    }
}


