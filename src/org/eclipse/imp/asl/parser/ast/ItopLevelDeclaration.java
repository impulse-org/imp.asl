
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
 * is implemented by:
 *<b>
 *<ul>
 *<li>functionDeclaration
 *<li>algebraicTypeDeclaration
 *<li>typeDef
 *<li>rulesSpecification
 *<li>analysisSpecification
 *<li>variableDeclaration
 *</ul>
 *</b>
 */
public interface ItopLevelDeclaration
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


