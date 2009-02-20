
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
 *<li>Rule 31:  analysisSpecification ::= analysis identifier constraintsDef estimatesDef satisfyDef end
 *</b>
 */
public class analysisSpecification extends ASTNode implements IanalysisSpecification
{
    private ASTNodeToken _analysis;
    private identifier _identifier;
    private constraintsDef _constraintsDef;
    private estimatesDef _estimatesDef;
    private satisfyDef _satisfyDef;
    private ASTNodeToken _end;

    public ASTNodeToken getanalysis() { return _analysis; }
    public identifier getidentifier() { return _identifier; }
    public constraintsDef getconstraintsDef() { return _constraintsDef; }
    public estimatesDef getestimatesDef() { return _estimatesDef; }
    public satisfyDef getsatisfyDef() { return _satisfyDef; }
    public ASTNodeToken getend() { return _end; }

    public analysisSpecification(IToken leftIToken, IToken rightIToken,
                                 ASTNodeToken _analysis,
                                 identifier _identifier,
                                 constraintsDef _constraintsDef,
                                 estimatesDef _estimatesDef,
                                 satisfyDef _satisfyDef,
                                 ASTNodeToken _end)
    {
        super(leftIToken, rightIToken);

        this._analysis = _analysis;
        ((ASTNode) _analysis).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._constraintsDef = _constraintsDef;
        ((ASTNode) _constraintsDef).setParent(this);
        this._estimatesDef = _estimatesDef;
        ((ASTNode) _estimatesDef).setParent(this);
        this._satisfyDef = _satisfyDef;
        ((ASTNode) _satisfyDef).setParent(this);
        this._end = _end;
        ((ASTNode) _end).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_analysis);
        list.add(_identifier);
        list.add(_constraintsDef);
        list.add(_estimatesDef);
        list.add(_satisfyDef);
        list.add(_end);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof analysisSpecification)) return false;
        if (! super.equals(o)) return false;
        analysisSpecification other = (analysisSpecification) o;
        if (! _analysis.equals(other._analysis)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _constraintsDef.equals(other._constraintsDef)) return false;
        if (! _estimatesDef.equals(other._estimatesDef)) return false;
        if (! _satisfyDef.equals(other._satisfyDef)) return false;
        if (! _end.equals(other._end)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_analysis.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_constraintsDef.hashCode());
        hash = hash * 31 + (_estimatesDef.hashCode());
        hash = hash * 31 + (_satisfyDef.hashCode());
        hash = hash * 31 + (_end.hashCode());
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
            _analysis.accept(v);
            _identifier.accept(v);
            _constraintsDef.accept(v);
            _estimatesDef.accept(v);
            _satisfyDef.accept(v);
            _end.accept(v);
        }
        v.endVisit(this);
    }
}


