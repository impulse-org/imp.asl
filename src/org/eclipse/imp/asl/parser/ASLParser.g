%options package=org.eclipse.imp.asl.parser
%options template=dtParserTemplateF.gi
%options import_terminals=ASLLexer.gi
%options parent_saved,automatic_ast=toplevel,visitor=preorder,ast_directory=./ast,ast_type=ASTNode

%Globals
    /.import org.eclipse.imp.parser.IParser;
    ./
%End

%Define
    $ast_class /.Object./
    $additional_interfaces /., IParser./
%End

%Terminals
    analysis
    atype
    boolean
    case
    constraints
    double
    end
    estimates
    false
    int
    intersect
    else
    ref
    relation
    rules
    satisfy
    set
    string
    true
    type
    union

    IDENTIFIER
    IntegerLiteral
    DoubleLiteral
    StringLiteral
    COLON ::= ':'
    COMMA ::= ','
    DOT   ::= '.'
    ASSIGN ::= ':='
    LESS ::= '<'
    GREATER ::= '>'
    ARROW ::= '=>'
    EQUAL ::= '='
    NOTEQUAL ::= '!='
    PLUS ::= '+'
    STAR ::= '*'
    VBAR ::= '|'
    
    LEFTBRACKET  ::= '['
    RIGHTBRACKET ::= ']'
    LEFTPAREN  ::= '('
    RIGHTPAREN ::= ')'
    LEFTBRACE  ::= '{'
    RIGHTBRACE ::= '}'
%End

%Start
    compilationUnit
    typeSpecifier
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
    compilationUnit$$topLevelDeclaration ::= %empty
                                           | compilationUnit topLevelDeclaration

    topLevelDeclaration ::= functionDeclaration
                        |   algebraicTypeDeclaration
                        |   typeDef
                        |   rulesSpecification
                        |   analysisSpecification
                        |   variableDeclaration

    functionDeclaration ::= functionHeader '='$ value

    functionHeader ::= typeSpecifier identifier '('$ parameters ')'$

    parameters$$declaration ::= %empty
                              | parameterList

    parameterList$$declaration ::= declaration
                                 | parameterList ','$ declaration

    declaration ::= typeSpecifier identifier

    algebraicTypeDeclaration ::= atype$ identifier '='$ ctorList

    ctorList$$ctorDeclaration ::= ctorDeclaration | ctorList '|'$ ctorDeclaration

    ctorDeclaration ::= identifier optFormalArgList

    optFormalArgList ::= %empty | '('$ formalArgList ')'$

    formalArgList$$formalArg ::= formalArg | formalArgList ','$ formalArg

    formalArg ::= typeName$type identifier$name

    typeDef ::= type identifier '='$ typeSpecifier

    rulesSpecification ::= rules rulesList

    rulesList$$rule ::= rule | rule rulesList

    rule ::= algebraicTypeValue '=>'$ algebraicTypeValue

    --------------
    -- Analysis --
    --------------

    analysisSpecification ::= analysis identifier constraintsDef estimatesDef satisfyDef end

    constraintsDef ::= constraints value

    estimatesDef ::= estimates identifier

    satisfyDef ::= satisfy '('$ '<'$ typeSpecifier ','$ typeSpecifier '>'$ ')'$ '{'$ localOrUpdateList '}'$

    localOrUpdateList ::= localOrUpdate | localOrUpdateList localOrUpdate

    localOrUpdate ::= local | update
    local  ::= identifier ':='$ value
    update ::= estimates '['$ identifier ']'$ ':='$ value

    variableDeclaration ::= typeName identifier value

    typeName ::= identifier | int | double | boolean | string

    identifier ::= IDENTIFIER

    ------------
    -- Values --
    ------------

    value ::= tupleLiteral
          |   setLiteral
          |   mapLiteral
          |   relationLiteral
          |   mapLookup
          |   tupleSlot
          |   algebraicTypeValue
          |   IntegerLiteral
          |   DoubleLiteral
          |   StringLiteral
          |   booleanValue
          |   functionCall
          |   caseExpr
          |   unionExpr
          |   intersectExpr
          |   relationClosure
          |   identifier

    tupleLiteral ::= '<'$ values '>'$
    setLiteral   ::= '{'$ values '}'$

    mapLiteral ::= '{'$ mappings '}'$
    mappings ::= %empty | mappingList
    mappingList$$mapping ::= mapping | mappingList mapping
    mapping ::= value '=>'$ value

    relationLiteral ::= '['$ tuples ']'$

    tuples ::= %empty | tupleList
    tupleList$$tupleLiteral ::= tupleLiteral | tupleLiteral ','$ tupleList

    mapLookup ::= value '['$ value ']'$
    tupleSlot ::= value '.'$ identifier

    algebraicTypeValue ::= identifier optArgList
    optArgList ::= %empty | '('$ values ')'$

    booleanValue ::= true | false

    functionCall ::= identifier '('$ values ')'$

    values ::= %empty | valueList
    valueList$$value ::= value | value ','$ valueList

    caseExpr ::= case caseList
    caseList$$caseSpec ::= caseSpec | caseList caseSpec
    caseSpec ::= identifier '='$ value ':'$ value |
             |   else value

    unionExpr       ::= value union value
    intersectExpr   ::= value intersect value
    relationClosure ::= value '+' | value '*'

    -----------
    -- Types --
    -----------

    typeSpecifier ::= relationSpecifier
                  |   tupleSpecifier
                  |   setSpecifier
                  |   refSpecifier
                  |   scalarType
                  |   qualifiedIdentifier

    relationSpecifier ::= relation '['$ typeSpecifierList ']'$

    tupleSpecifier ::= '<'$ typeSpecifierList '>'$

    setSpecifier ::= set '['$ typeSpecifier ']'$

    refSpecifier ::= ref typeName

    scalarType ::= int | double | string | boolean

    qualifiedIdentifier ::= identifier
                        |   qualifiedIdentifier '.'$ identifier

    typeSpecifierList$$typeSpecifier ::= typeSpecifier | typeSpecifierList ','$ typeSpecifier
%End
