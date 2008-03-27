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
 *<li>Rule 34:  satisfyDef ::= satisfy ($ <$ typeSpecifier ,$ typeSpecifier >$ )$ {$ localOrUpdateList }$
 *</b>
 */
public class satisfyDef extends ASTNode implements IsatisfyDef
{
    private ASTNodeToken _satisfy;
    private ItypeSpecifier _typeSpecifier;
    private ItypeSpecifier _typeSpecifier6;
    private IlocalOrUpdateList _localOrUpdateList;

    public ASTNodeToken getsatisfy() { return _satisfy; }
    public ItypeSpecifier gettypeSpecifier() { return _typeSpecifier; }
    public ItypeSpecifier gettypeSpecifier6() { return _typeSpecifier6; }
    public IlocalOrUpdateList getlocalOrUpdateList() { return _localOrUpdateList; }

    public satisfyDef(IToken leftIToken, IToken rightIToken,
                      ASTNodeToken _satisfy,
                      ItypeSpecifier _typeSpecifier,
                      ItypeSpecifier _typeSpecifier6,
                      IlocalOrUpdateList _localOrUpdateList)
    {
        super(leftIToken, rightIToken);

        this._satisfy = _satisfy;
        ((ASTNode) _satisfy).setParent(this);
        this._typeSpecifier = _typeSpecifier;
        ((ASTNode) _typeSpecifier).setParent(this);
        this._typeSpecifier6 = _typeSpecifier6;
        ((ASTNode) _typeSpecifier6).setParent(this);
        this._localOrUpdateList = _localOrUpdateList;
        ((ASTNode) _localOrUpdateList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_satisfy);
        list.add(_typeSpecifier);
        list.add(_typeSpecifier6);
        list.add(_localOrUpdateList);
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
        if (! (o instanceof satisfyDef)) return false;
        satisfyDef other = (satisfyDef) o;
        if (! _satisfy.equals(other._satisfy)) return false;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        if (! _typeSpecifier6.equals(other._typeSpecifier6)) return false;
        if (! _localOrUpdateList.equals(other._localOrUpdateList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_satisfy.hashCode());
        hash = hash * 31 + (_typeSpecifier.hashCode());
        hash = hash * 31 + (_typeSpecifier6.hashCode());
        hash = hash * 31 + (_localOrUpdateList.hashCode());
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
            _satisfy.accept(v);
            _typeSpecifier.accept(v);
            _typeSpecifier6.accept(v);
            _localOrUpdateList.accept(v);
        }
        v.endVisit(this);
    }
}


