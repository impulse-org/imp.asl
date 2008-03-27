%options package=org.eclipse.imp.asl.parser
%options template=KeywordTemplate.gi

%Include
    KWLexerMap.gi
%End

%Export
    analysis
    atype
    boolean
    case
    constraints
    double
    else
    end
    estimates
    false
    int
    intersect
    ref
    relation
    return
    rules
    satisfy
    set
    string
    true
    type
    union
%End

%Terminals
    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
%End

%Start
    Keyword
%End

%Notice
/.
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
./
%End

%Rules
    Keyword ::= a n a l y s i s
        /.$BeginAction
            $setResult($_analysis);
          $EndAction
        ./

    Keyword ::= a t y p e
        /.$BeginAction
            $setResult($_atype);
          $EndAction
        ./

    Keyword ::= b o o l e a n
        /.$BeginAction
            $setResult($_boolean);
          $EndAction
        ./

    Keyword ::= c a s e
        /.$BeginAction
            $setResult($_case);
          $EndAction
        ./

    Keyword ::= c o n s t r a i n t s
        /.$BeginAction
            $setResult($_constraints);
          $EndAction
        ./

    Keyword ::= d o u b l e
        /.$BeginAction
            $setResult($_double);
          $EndAction
        ./
        
    Keyword ::= e l s e
        /.$BeginAction
            $setResult($_else);
          $EndAction
        ./

    Keyword ::= e n d
        /.$BeginAction
            $setResult($_end);
          $EndAction
        ./

    Keyword ::= e s t i m a t e s
        /.$BeginAction
            $setResult($_estimates);
          $EndAction
        ./

    Keyword ::= f a l s e
        /.$BeginAction
            $setResult($_false);
          $EndAction
        ./

    Keyword ::= i n t
        /.$BeginAction
            $setResult($_int);
          $EndAction
        ./

    Keyword ::= i n t e r s e c t
        /.$BeginAction
            $setResult($_intersect);
          $EndAction
        ./

    Keyword ::= r e f
        /.$BeginAction
            $setResult($_ref);
          $EndAction
        ./

    Keyword ::= r e l a t i o n
        /.$BeginAction
            $setResult($_relation);
          $EndAction
        ./

    Keyword ::= r e t u r n
        /.$BeginAction
            $setResult($_return);
          $EndAction
        ./

    Keyword ::= r u l e s
        /.$BeginAction
            $setResult($_rules);
          $EndAction
        ./

    Keyword ::= s a t i s f y
        /.$BeginAction
            $setResult($_satisfy);
          $EndAction
        ./

    Keyword ::= s e t
        /.$BeginAction
            $setResult($_set);
          $EndAction
        ./

    Keyword ::= s t r i n g
        /.$BeginAction
            $setResult($_string);
          $EndAction
        ./

    Keyword ::= t r u e
        /.$BeginAction
            $setResult($_true);
          $EndAction
        ./

    Keyword ::= t y p e
        /.$BeginAction
            $setResult($_type);
          $EndAction
        ./

    Keyword ::= u n i o n
        /.$BeginAction
            $setResult($_union);
          $EndAction
        ./
%End
