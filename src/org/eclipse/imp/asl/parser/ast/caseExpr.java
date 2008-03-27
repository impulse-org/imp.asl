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
 *<li>Rule 90:  caseExpr ::= case caseList
 *</b>
 */
public class caseExpr extends ASTNode implements IcaseExpr
{
    private ASTNodeToken _case;
    private caseSpecList _caseList;

    public ASTNodeToken getcase() { return _case; }
    public caseSpecList getcaseList() { return _caseList; }

    public caseExpr(IToken leftIToken, IToken rightIToken,
                    ASTNodeToken _case,
                    caseSpecList _caseList)
    {
        super(leftIToken, rightIToken);

        this._case = _case;
        ((ASTNode) _case).setParent(this);
        this._caseList = _caseList;
        ((ASTNode) _caseList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_case);
        list.add(_caseList);
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
        if (! (o instanceof caseExpr)) return false;
        caseExpr other = (caseExpr) o;
        if (! _case.equals(other._case)) return false;
        if (! _caseList.equals(other._caseList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_case.hashCode());
        hash = hash * 31 + (_caseList.hashCode());
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
            _case.accept(v);
            _caseList.accept(v);
        }
        v.endVisit(this);
    }
}


