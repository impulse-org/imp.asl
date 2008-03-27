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
 *<li>Rule 40:  update ::= estimates [$ identifier ]$ :=$ value
 *</b>
 */
public class update extends ASTNode implements Iupdate
{
    private ASTNodeToken _estimates;
    private identifier _identifier;
    private Ivalue _value;

    public ASTNodeToken getestimates() { return _estimates; }
    public identifier getidentifier() { return _identifier; }
    public Ivalue getvalue() { return _value; }

    public update(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _estimates,
                  identifier _identifier,
                  Ivalue _value)
    {
        super(leftIToken, rightIToken);

        this._estimates = _estimates;
        ((ASTNode) _estimates).setParent(this);
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
        list.add(_estimates);
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
        if (! (o instanceof update)) return false;
        update other = (update) o;
        if (! _estimates.equals(other._estimates)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _value.equals(other._value)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_estimates.hashCode());
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
            _estimates.accept(v);
            _identifier.accept(v);
            _value.accept(v);
        }
        v.endVisit(this);
    }
}


