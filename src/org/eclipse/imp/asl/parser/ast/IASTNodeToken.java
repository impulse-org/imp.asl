
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


