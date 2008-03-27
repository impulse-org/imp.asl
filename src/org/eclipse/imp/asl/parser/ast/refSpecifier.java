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
 *<li>Rule 109:  refSpecifier ::= ref typeName
 *</b>
 */
public class refSpecifier extends ASTNode implements IrefSpecifier
{
    private ASTNodeToken _ref;
    private ItypeName _typeName;

    public ASTNodeToken getref() { return _ref; }
    public ItypeName gettypeName() { return _typeName; }

    public refSpecifier(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _ref,
                        ItypeName _typeName)
    {
        super(leftIToken, rightIToken);

        this._ref = _ref;
        ((ASTNode) _ref).setParent(this);
        this._typeName = _typeName;
        ((ASTNode) _typeName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ref);
        list.add(_typeName);
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
        if (! (o instanceof refSpecifier)) return false;
        refSpecifier other = (refSpecifier) o;
        if (! _ref.equals(other._ref)) return false;
        if (! _typeName.equals(other._typeName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_ref.hashCode());
        hash = hash * 31 + (_typeName.hashCode());
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
            _ref.accept(v);
            _typeName.accept(v);
        }
        v.endVisit(this);
    }
}


