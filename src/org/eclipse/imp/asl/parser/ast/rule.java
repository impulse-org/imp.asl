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
 *<li>Rule 30:  rule ::= algebraicTypeValue =>$ algebraicTypeValue
 *</b>
 */
public class rule extends ASTNode implements Irule
{
    private algebraicTypeValue _algebraicTypeValue;
    private algebraicTypeValue _algebraicTypeValue3;

    public algebraicTypeValue getalgebraicTypeValue() { return _algebraicTypeValue; }
    public algebraicTypeValue getalgebraicTypeValue3() { return _algebraicTypeValue3; }

    public rule(IToken leftIToken, IToken rightIToken,
                algebraicTypeValue _algebraicTypeValue,
                algebraicTypeValue _algebraicTypeValue3)
    {
        super(leftIToken, rightIToken);

        this._algebraicTypeValue = _algebraicTypeValue;
        ((ASTNode) _algebraicTypeValue).setParent(this);
        this._algebraicTypeValue3 = _algebraicTypeValue3;
        ((ASTNode) _algebraicTypeValue3).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_algebraicTypeValue);
        list.add(_algebraicTypeValue3);
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
        if (! (o instanceof rule)) return false;
        rule other = (rule) o;
        if (! _algebraicTypeValue.equals(other._algebraicTypeValue)) return false;
        if (! _algebraicTypeValue3.equals(other._algebraicTypeValue3)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_algebraicTypeValue.hashCode());
        hash = hash * 31 + (_algebraicTypeValue3.hashCode());
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
            _algebraicTypeValue.accept(v);
            _algebraicTypeValue3.accept(v);
        }
        v.endVisit(this);
    }
}


