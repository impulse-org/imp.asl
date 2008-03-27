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

/**
 * is implemented by:
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
 *<li>value0
 *<li>value1
 *<li>value2
 *<li>booleanValue0
 *<li>booleanValue1
 *<li>relationClosure0
 *<li>relationClosure1
 *</ul>
 *</b>
 */
public interface Ivalue extends IASTNodeToken {}


