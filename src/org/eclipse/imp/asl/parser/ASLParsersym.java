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

package org.eclipse.imp.asl.parser;

public interface ASLParsersym {
    public final static int
      TK_typeSpecifierMarker = 33,
      TK_analysis = 34,
      TK_atype = 35,
      TK_boolean = 14,
      TK_case = 15,
      TK_constraints = 36,
      TK_double = 16,
      TK_end = 37,
      TK_estimates = 25,
      TK_false = 17,
      TK_int = 18,
      TK_intersect = 3,
      TK_else = 38,
      TK_ref = 26,
      TK_relation = 27,
      TK_rules = 39,
      TK_satisfy = 40,
      TK_set = 28,
      TK_string = 19,
      TK_true = 20,
      TK_type = 41,
      TK_union = 4,
      TK_IDENTIFIER = 7,
      TK_IntegerLiteral = 21,
      TK_DoubleLiteral = 22,
      TK_StringLiteral = 23,
      TK_COLON = 42,
      TK_COMMA = 9,
      TK_DOT = 2,
      TK_ASSIGN = 32,
      TK_LESS = 8,
      TK_GREATER = 29,
      TK_ARROW = 30,
      TK_EQUAL = 24,
      TK_NOTEQUAL = 45,
      TK_PLUS = 5,
      TK_STAR = 6,
      TK_VBAR = 43,
      TK_LEFTBRACKET = 1,
      TK_RIGHTBRACKET = 10,
      TK_LEFTPAREN = 11,
      TK_RIGHTPAREN = 12,
      TK_LEFTBRACE = 13,
      TK_RIGHTBRACE = 31,
      TK_return = 46,
      TK_EOF_TOKEN = 44,
      TK_SINGLE_LINE_COMMENT = 47,
      TK_SEMICOLON = 48,
      TK_ERROR_TOKEN = 49;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LEFTBRACKET",
                 "DOT",
                 "intersect",
                 "union",
                 "PLUS",
                 "STAR",
                 "IDENTIFIER",
                 "LESS",
                 "COMMA",
                 "RIGHTBRACKET",
                 "LEFTPAREN",
                 "RIGHTPAREN",
                 "LEFTBRACE",
                 "boolean",
                 "case",
                 "double",
                 "false",
                 "int",
                 "string",
                 "true",
                 "IntegerLiteral",
                 "DoubleLiteral",
                 "StringLiteral",
                 "EQUAL",
                 "estimates",
                 "ref",
                 "relation",
                 "set",
                 "GREATER",
                 "ARROW",
                 "RIGHTBRACE",
                 "ASSIGN",
                 "typeSpecifierMarker",
                 "analysis",
                 "atype",
                 "constraints",
                 "end",
                 "else",
                 "rules",
                 "satisfy",
                 "type",
                 "COLON",
                 "VBAR",
                 "EOF_TOKEN",
                 "NOTEQUAL",
                 "return",
                 "SINGLE_LINE_COMMENT",
                 "SEMICOLON",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
