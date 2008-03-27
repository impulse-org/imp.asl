package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 27:  rulesSpecification ::= rules rulesList
 *</b>
 */
public class rulesSpecification extends ASTNode implements IrulesSpecification
{
    private ASTNodeToken _rules;
    private ruleList _rulesList;

    public ASTNodeToken getrules() { return _rules; }
    public ruleList getrulesList() { return _rulesList; }

    public rulesSpecification(IToken leftIToken, IToken rightIToken,
                              ASTNodeToken _rules,
                              ruleList _rulesList)
    {
        super(leftIToken, rightIToken);

        this._rules = _rules;
        ((ASTNode) _rules).setParent(this);
        this._rulesList = _rulesList;
        ((ASTNode) _rulesList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_rules);
        list.add(_rulesList);
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
        if (! (o instanceof rulesSpecification)) return false;
        rulesSpecification other = (rulesSpecification) o;
        if (! _rules.equals(other._rules)) return false;
        if (! _rulesList.equals(other._rulesList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_rules.hashCode());
        hash = hash * 31 + (_rulesList.hashCode());
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
            _rules.accept(v);
            _rulesList.accept(v);
        }
        v.endVisit(this);
    }
}


