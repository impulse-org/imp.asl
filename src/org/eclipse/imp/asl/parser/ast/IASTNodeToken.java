package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by:
 *<b>
 *<ul>
 *<li>identifier
 *<li>tupleLiteral
 *<li>setLiteral
 *<li>mapLiteral
 *<li>relationLiteral
 *<li>mapLookup
 *<li>tupleSlot
 *<li>algebraicTypeValue
 *<li>functionCall
 *<li>caseExpr
 *<li>unionExpr
 *<li>intersectExpr
 *<li>typeName0
 *<li>typeName1
 *<li>typeName2
 *<li>typeName3
 *<li>value0
 *<li>value1
 *<li>value2
 *<li>booleanValue0
 *<li>booleanValue1
 *<li>relationClosure0
 *<li>relationClosure1
 *<li>scalarType0
 *<li>scalarType1
 *<li>scalarType2
 *<li>scalarType3
 *</ul>
 *</b>
 */
public interface IASTNodeToken
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


