
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
 *<li>Rule 32:  constraintsDef ::= constraints value
 *</b>
 */
public class constraintsDef extends ASTNode implements IconstraintsDef
{
    private ASTNodeToken _constraints;
    private Ivalue _value;

    public ASTNodeToken getconstraints() { return _constraints; }
    public Ivalue getvalue() { return _value; }

    public constraintsDef(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _constraints,
                          Ivalue _value)
    {
        super(leftIToken, rightIToken);

        this._constraints = _constraints;
        ((ASTNode) _constraints).setParent(this);
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
        list.add(_constraints);
        list.add(_value);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof constraintsDef)) return false;
        if (! super.equals(o)) return false;
        constraintsDef other = (constraintsDef) o;
        if (! _constraints.equals(other._constraints)) return false;
        if (! _value.equals(other._value)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_constraints.hashCode());
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
            _constraints.accept(v);
            _value.accept(v);
        }
        v.endVisit(this);
    }
}


