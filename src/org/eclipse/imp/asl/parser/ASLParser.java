
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

package org.eclipse.imp.asl.parser;

import org.eclipse.imp.asl.parser.ast.*;
import lpg.runtime.*;
import org.eclipse.imp.parser.IParser;

public class ASLParser implements RuleAction, IParser
{
    private PrsStream prsStream = null;
    
    private boolean unimplementedSymbolsWarning = false;

    private static ParseTable prsTable = new ASLParserprs();
    public ParseTable getParseTable() { return prsTable; }

    private DeterministicParser dtParser = null;
    public DeterministicParser getParser() { return dtParser; }

    private void setResult(Object object) { dtParser.setSym1(object); }
    public Object getRhsSym(int i) { return dtParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return dtParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return dtParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return dtParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return dtParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return dtParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        dtParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + ASLParsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 ASLParsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        }
    }
    
    public ASLParser()
    {
        try
        {
            dtParser = new DeterministicParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotDeterministicParseTableException e)
        {
            throw new Error(new NotDeterministicParseTableException
                                ("Regenerate ASLParserprs.java with -NOBACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- ASLParsersym.java. Regenerate ASLParserprs.java"));
        }
    }

    public ASLParser(ILexStream lexStream)
    {
        this();
        reset(lexStream);
    }

    public int numTokenKinds() { return ASLParsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return ASLParsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return ASLParsersym.orderedTerminalSymbols[kind]; }            
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public Object parser()
    {
        return parser(null, 0);
    }
        
    public Object parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
        
    public Object parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }
        
    public Object parser(Monitor monitor, int error_repair_count)
    {
        dtParser.setMonitor(monitor);

        try
        {
            return (Object) dtParser.parse();
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    
    public Object parsetypeSpecifier()
    {
        return parsetypeSpecifier(null, 0);
    }
        
    public Object parsetypeSpecifier(Monitor monitor)
    {
        return parsetypeSpecifier(monitor, 0);
    }
        
    public Object parsetypeSpecifier(int error_repair_count)
    {
        return parsetypeSpecifier(null, error_repair_count);
    }
        
    public void resetParsetypeSpecifier()
    {
        dtParser.resetParserEntry(ASLParsersym.TK_typeSpecifierMarker);
    }
    
    public Object parsetypeSpecifier(Monitor monitor, int error_repair_count)
    {
        dtParser.setMonitor(monitor);
        
        try
        {
            return (Object) dtParser.parseEntry(ASLParsersym.TK_typeSpecifierMarker);
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnoseEntry(ASLParsersym.TK_typeSpecifierMarker, e.error_token);
        }

        return null;
    }


    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 2:  compilationUnit ::= $Empty
            //
            case 2: {
                setResult(
                    new topLevelDeclarationList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 3:  compilationUnit ::= compilationUnit topLevelDeclaration
            //
            case 3: {
                setResult(
                    new topLevelDeclarationList((ItopLevelDeclaration)getRhsSym(2), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 4:  topLevelDeclaration ::= functionDeclaration
            //
            case 4:
                break;
            //
            // Rule 5:  topLevelDeclaration ::= algebraicTypeDeclaration
            //
            case 5:
                break;
            //
            // Rule 6:  topLevelDeclaration ::= typeDef
            //
            case 6:
                break;
            //
            // Rule 7:  topLevelDeclaration ::= rulesSpecification
            //
            case 7:
                break;
            //
            // Rule 8:  topLevelDeclaration ::= analysisSpecification
            //
            case 8:
                break;
            //
            // Rule 9:  topLevelDeclaration ::= variableDeclaration
            //
            case 9:
                break;
            //
            // Rule 10:  functionDeclaration ::= functionHeader =$ value
            //
            case 10: {
                setResult(
                    new functionDeclaration(getLeftIToken(), getRightIToken(),
                                            (functionHeader)getRhsSym(1),
                                            (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 11:  functionHeader ::= typeSpecifier identifier ($ parameters )$
            //
            case 11: {
                setResult(
                    new functionHeader(getLeftIToken(), getRightIToken(),
                                       (ItypeSpecifier)getRhsSym(1),
                                       (identifier)getRhsSym(2),
                                       (declarationList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 12:  parameters ::= $Empty
            //
            case 12: {
                setResult(
                    new declarationList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 13:  parameters ::= parameterList
            //
            case 13:
                break;
            //
            // Rule 14:  parameterList ::= declaration
            //
            case 14: {
                setResult(
                    new declarationList((declaration)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 15:  parameterList ::= parameterList ,$ declaration
            //
            case 15: {
                ((declarationList)getRhsSym(1)).add((declaration)getRhsSym(3));
                break;
            }
            //
            // Rule 16:  declaration ::= typeSpecifier identifier
            //
            case 16: {
                setResult(
                    new declaration(getLeftIToken(), getRightIToken(),
                                    (ItypeSpecifier)getRhsSym(1),
                                    (identifier)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 17:  algebraicTypeDeclaration ::= atype$ identifier =$ ctorList
            //
            case 17: {
                setResult(
                    new algebraicTypeDeclaration(getLeftIToken(), getRightIToken(),
                                                 (identifier)getRhsSym(2),
                                                 (ctorDeclarationList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 18:  ctorList ::= ctorDeclaration
            //
            case 18: {
                setResult(
                    new ctorDeclarationList((ctorDeclaration)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 19:  ctorList ::= ctorList |$ ctorDeclaration
            //
            case 19: {
                ((ctorDeclarationList)getRhsSym(1)).add((ctorDeclaration)getRhsSym(3));
                break;
            }
            //
            // Rule 20:  ctorDeclaration ::= identifier optFormalArgList
            //
            case 20: {
                setResult(
                    new ctorDeclaration(getLeftIToken(), getRightIToken(),
                                        (identifier)getRhsSym(1),
                                        (optFormalArgList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 21:  optFormalArgList ::= $Empty
            //
            case 21: {
                setResult(null);
                break;
            }
            //
            // Rule 22:  optFormalArgList ::= ($ formalArgList )$
            //
            case 22: {
                setResult(
                    new optFormalArgList(getLeftIToken(), getRightIToken(),
                                         (formalArgList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 23:  formalArgList ::= formalArg
            //
            case 23: {
                setResult(
                    new formalArgList((formalArg)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 24:  formalArgList ::= formalArgList ,$ formalArg
            //
            case 24: {
                ((formalArgList)getRhsSym(1)).add((formalArg)getRhsSym(3));
                break;
            }
            //
            // Rule 25:  formalArg ::= typeName$type identifier$name
            //
            case 25: {
                setResult(
                    new formalArg(getLeftIToken(), getRightIToken(),
                                  (ItypeName)getRhsSym(1),
                                  (identifier)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 26:  typeDef ::= type identifier =$ typeSpecifier
            //
            case 26: {
                setResult(
                    new typeDef(getLeftIToken(), getRightIToken(),
                                new ASTNodeToken(getRhsIToken(1)),
                                (identifier)getRhsSym(2),
                                (ItypeSpecifier)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 27:  rulesSpecification ::= rules rulesList
            //
            case 27: {
                setResult(
                    new rulesSpecification(getLeftIToken(), getRightIToken(),
                                           new ASTNodeToken(getRhsIToken(1)),
                                           (ruleList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 28:  rulesList ::= rule
            //
            case 28: {
                setResult(
                    new ruleList((rule)getRhsSym(1), false /* not left recursive */)
                );
                break;
            }
            //
            // Rule 29:  rulesList ::= rule rulesList
            //
            case 29: {
                ((ruleList)getRhsSym(2)).add((rule)getRhsSym(1));
                setResult(getRhsSym(2));
                break;
            }
            //
            // Rule 30:  rule ::= algebraicTypeValue =>$ algebraicTypeValue
            //
            case 30: {
                setResult(
                    new rule(getLeftIToken(), getRightIToken(),
                             (algebraicTypeValue)getRhsSym(1),
                             (algebraicTypeValue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 31:  analysisSpecification ::= analysis identifier constraintsDef estimatesDef satisfyDef end
            //
            case 31: {
                setResult(
                    new analysisSpecification(getLeftIToken(), getRightIToken(),
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (identifier)getRhsSym(2),
                                              (constraintsDef)getRhsSym(3),
                                              (estimatesDef)getRhsSym(4),
                                              (satisfyDef)getRhsSym(5),
                                              new ASTNodeToken(getRhsIToken(6)))
                );
                break;
            }
            //
            // Rule 32:  constraintsDef ::= constraints value
            //
            case 32: {
                setResult(
                    new constraintsDef(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       (Ivalue)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 33:  estimatesDef ::= estimates identifier
            //
            case 33: {
                setResult(
                    new estimatesDef(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     (identifier)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 34:  satisfyDef ::= satisfy ($ <$ typeSpecifier ,$ typeSpecifier >$ )$ {$ localOrUpdateList }$
            //
            case 34: {
                setResult(
                    new satisfyDef(getLeftIToken(), getRightIToken(),
                                   new ASTNodeToken(getRhsIToken(1)),
                                   (ItypeSpecifier)getRhsSym(4),
                                   (ItypeSpecifier)getRhsSym(6),
                                   (IlocalOrUpdateList)getRhsSym(10))
                );
                break;
            }
            //
            // Rule 35:  localOrUpdateList ::= localOrUpdate
            //
            case 35:
                break;
            //
            // Rule 36:  localOrUpdateList ::= localOrUpdateList localOrUpdate
            //
            case 36: {
                setResult(
                    new localOrUpdateList(getLeftIToken(), getRightIToken(),
                                          (IlocalOrUpdateList)getRhsSym(1),
                                          (IlocalOrUpdate)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 37:  localOrUpdate ::= local
            //
            case 37:
                break;
            //
            // Rule 38:  localOrUpdate ::= update
            //
            case 38:
                break;
            //
            // Rule 39:  local ::= identifier :=$ value
            //
            case 39: {
                setResult(
                    new local(getLeftIToken(), getRightIToken(),
                              (identifier)getRhsSym(1),
                              (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 40:  update ::= estimates [$ identifier ]$ :=$ value
            //
            case 40: {
                setResult(
                    new update(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               (identifier)getRhsSym(3),
                               (Ivalue)getRhsSym(6))
                );
                break;
            }
            //
            // Rule 41:  variableDeclaration ::= typeName identifier value
            //
            case 41: {
                setResult(
                    new variableDeclaration(getLeftIToken(), getRightIToken(),
                                            (ItypeName)getRhsSym(1),
                                            (identifier)getRhsSym(2),
                                            (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 42:  typeName ::= identifier
            //
            case 42:
                break;
            //
            // Rule 43:  typeName ::= int
            //
            case 43: {
                setResult(
                    new typeName0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 44:  typeName ::= double
            //
            case 44: {
                setResult(
                    new typeName1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  typeName ::= boolean
            //
            case 45: {
                setResult(
                    new typeName2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 46:  typeName ::= string
            //
            case 46: {
                setResult(
                    new typeName3(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 47:  identifier ::= IDENTIFIER
            //
            case 47: {
                setResult(
                    new identifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 48:  value ::= tupleLiteral
            //
            case 48:
                break;
            //
            // Rule 49:  value ::= setLiteral
            //
            case 49:
                break;
            //
            // Rule 50:  value ::= mapLiteral
            //
            case 50:
                break;
            //
            // Rule 51:  value ::= relationLiteral
            //
            case 51:
                break;
            //
            // Rule 52:  value ::= mapLookup
            //
            case 52:
                break;
            //
            // Rule 53:  value ::= tupleSlot
            //
            case 53:
                break;
            //
            // Rule 54:  value ::= algebraicTypeValue
            //
            case 54:
                break;
            //
            // Rule 55:  value ::= IntegerLiteral
            //
            case 55: {
                setResult(
                    new value0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 56:  value ::= DoubleLiteral
            //
            case 56: {
                setResult(
                    new value1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 57:  value ::= StringLiteral
            //
            case 57: {
                setResult(
                    new value2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 58:  value ::= booleanValue
            //
            case 58:
                break;
            //
            // Rule 59:  value ::= functionCall
            //
            case 59:
                break;
            //
            // Rule 60:  value ::= caseExpr
            //
            case 60:
                break;
            //
            // Rule 61:  value ::= unionExpr
            //
            case 61:
                break;
            //
            // Rule 62:  value ::= intersectExpr
            //
            case 62:
                break;
            //
            // Rule 63:  value ::= relationClosure
            //
            case 63:
                break;
            //
            // Rule 64:  value ::= identifier
            //
            case 64:
                break;
            //
            // Rule 65:  tupleLiteral ::= <$ values >$
            //
            case 65: {
                setResult(
                    new tupleLiteral(getLeftIToken(), getRightIToken(),
                                     (valueList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 66:  setLiteral ::= {$ values }$
            //
            case 66: {
                setResult(
                    new setLiteral(getLeftIToken(), getRightIToken(),
                                   (valueList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 67:  mapLiteral ::= {$ mappings }$
            //
            case 67: {
                setResult(
                    new mapLiteral(getLeftIToken(), getRightIToken(),
                                   (mappingList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 68:  mappings ::= $Empty
            //
            case 68: {
                setResult(null);
                break;
            }
            //
            // Rule 69:  mappings ::= mappingList
            //
            case 69:
                break;
            //
            // Rule 70:  mappingList ::= mapping
            //
            case 70: {
                setResult(
                    new mappingList((mapping)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 71:  mappingList ::= mappingList mapping
            //
            case 71: {
                ((mappingList)getRhsSym(1)).add((mapping)getRhsSym(2));
                break;
            }
            //
            // Rule 72:  mapping ::= value =>$ value
            //
            case 72: {
                setResult(
                    new mapping(getLeftIToken(), getRightIToken(),
                                (Ivalue)getRhsSym(1),
                                (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 73:  relationLiteral ::= [$ tuples ]$
            //
            case 73: {
                setResult(
                    new relationLiteral(getLeftIToken(), getRightIToken(),
                                        (tupleLiteralList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 74:  tuples ::= $Empty
            //
            case 74: {
                setResult(null);
                break;
            }
            //
            // Rule 75:  tuples ::= tupleList
            //
            case 75:
                break;
            //
            // Rule 76:  tupleList ::= tupleLiteral
            //
            case 76: {
                setResult(
                    new tupleLiteralList((tupleLiteral)getRhsSym(1), false /* not left recursive */)
                );
                break;
            }
            //
            // Rule 77:  tupleList ::= tupleLiteral ,$ tupleList
            //
            case 77: {
                ((tupleLiteralList)getRhsSym(3)).add((tupleLiteral)getRhsSym(1));
                setResult(getRhsSym(3));
                break;
            }
            //
            // Rule 78:  mapLookup ::= value [$ value ]$
            //
            case 78: {
                setResult(
                    new mapLookup(getLeftIToken(), getRightIToken(),
                                  (Ivalue)getRhsSym(1),
                                  (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 79:  tupleSlot ::= value .$ identifier
            //
            case 79: {
                setResult(
                    new tupleSlot(getLeftIToken(), getRightIToken(),
                                  (Ivalue)getRhsSym(1),
                                  (identifier)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 80:  algebraicTypeValue ::= identifier optArgList
            //
            case 80: {
                setResult(
                    new algebraicTypeValue(getLeftIToken(), getRightIToken(),
                                           (identifier)getRhsSym(1),
                                           (optArgList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 81:  optArgList ::= $Empty
            //
            case 81: {
                setResult(null);
                break;
            }
            //
            // Rule 82:  optArgList ::= ($ values )$
            //
            case 82: {
                setResult(
                    new optArgList(getLeftIToken(), getRightIToken(),
                                   (valueList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 83:  booleanValue ::= true
            //
            case 83: {
                setResult(
                    new booleanValue0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 84:  booleanValue ::= false
            //
            case 84: {
                setResult(
                    new booleanValue1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 85:  functionCall ::= identifier ($ values )$
            //
            case 85: {
                setResult(
                    new functionCall(getLeftIToken(), getRightIToken(),
                                     (identifier)getRhsSym(1),
                                     (valueList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 86:  values ::= $Empty
            //
            case 86: {
                setResult(null);
                break;
            }
            //
            // Rule 87:  values ::= valueList
            //
            case 87:
                break;
            //
            // Rule 88:  valueList ::= value
            //
            case 88: {
                setResult(
                    new valueList((Ivalue)getRhsSym(1), false /* not left recursive */)
                );
                break;
            }
            //
            // Rule 89:  valueList ::= value ,$ valueList
            //
            case 89: {
                ((valueList)getRhsSym(3)).add((Ivalue)getRhsSym(1));
                setResult(getRhsSym(3));
                break;
            }
            //
            // Rule 90:  caseExpr ::= case caseList
            //
            case 90: {
                setResult(
                    new caseExpr(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(1)),
                                 (caseSpecList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 91:  caseList ::= caseSpec
            //
            case 91: {
                setResult(
                    new caseSpecList((IcaseSpec)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 92:  caseList ::= caseList caseSpec
            //
            case 92: {
                ((caseSpecList)getRhsSym(1)).add((IcaseSpec)getRhsSym(2));
                break;
            }
            //
            // Rule 93:  caseSpec ::= identifier =$ value :$ value
            //
            case 93: {
                setResult(
                    new caseSpec0(getLeftIToken(), getRightIToken(),
                                  (identifier)getRhsSym(1),
                                  (Ivalue)getRhsSym(3),
                                  (Ivalue)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 94:  caseSpec ::=
            //
            case 94: {
                setResult(null);
                break;
            }
            //
            // Rule 95:  caseSpec ::= else value
            //
            case 95: {
                setResult(
                    new caseSpec1(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  (Ivalue)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 96:  unionExpr ::= value union value
            //
            case 96: {
                setResult(
                    new unionExpr(getLeftIToken(), getRightIToken(),
                                  (Ivalue)getRhsSym(1),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 97:  intersectExpr ::= value intersect value
            //
            case 97: {
                setResult(
                    new intersectExpr(getLeftIToken(), getRightIToken(),
                                      (Ivalue)getRhsSym(1),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      (Ivalue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 98:  relationClosure ::= value +
            //
            case 98: {
                setResult(
                    new relationClosure0(getLeftIToken(), getRightIToken(),
                                         (Ivalue)getRhsSym(1),
                                         new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 99:  relationClosure ::= value *
            //
            case 99: {
                setResult(
                    new relationClosure1(getLeftIToken(), getRightIToken(),
                                         (Ivalue)getRhsSym(1),
                                         new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 100:  typeSpecifier ::= relationSpecifier
            //
            case 100:
                break;
            //
            // Rule 101:  typeSpecifier ::= tupleSpecifier
            //
            case 101:
                break;
            //
            // Rule 102:  typeSpecifier ::= setSpecifier
            //
            case 102:
                break;
            //
            // Rule 103:  typeSpecifier ::= refSpecifier
            //
            case 103:
                break;
            //
            // Rule 104:  typeSpecifier ::= scalarType
            //
            case 104:
                break;
            //
            // Rule 105:  typeSpecifier ::= qualifiedIdentifier
            //
            case 105:
                break;
            //
            // Rule 106:  relationSpecifier ::= relation [$ typeSpecifierList ]$
            //
            case 106: {
                setResult(
                    new relationSpecifier(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (typeSpecifierList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 107:  tupleSpecifier ::= <$ typeSpecifierList >$
            //
            case 107: {
                setResult(
                    new tupleSpecifier(getLeftIToken(), getRightIToken(),
                                       (typeSpecifierList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 108:  setSpecifier ::= set [$ typeSpecifier ]$
            //
            case 108: {
                setResult(
                    new setSpecifier(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     (ItypeSpecifier)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 109:  refSpecifier ::= ref typeName
            //
            case 109: {
                setResult(
                    new refSpecifier(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     (ItypeName)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 110:  scalarType ::= int
            //
            case 110: {
                setResult(
                    new scalarType0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 111:  scalarType ::= double
            //
            case 111: {
                setResult(
                    new scalarType1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 112:  scalarType ::= string
            //
            case 112: {
                setResult(
                    new scalarType2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 113:  scalarType ::= boolean
            //
            case 113: {
                setResult(
                    new scalarType3(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 114:  qualifiedIdentifier ::= identifier
            //
            case 114:
                break;
            //
            // Rule 115:  qualifiedIdentifier ::= qualifiedIdentifier .$ identifier
            //
            case 115: {
                setResult(
                    new qualifiedIdentifier(getLeftIToken(), getRightIToken(),
                                            (IqualifiedIdentifier)getRhsSym(1),
                                            (identifier)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 116:  typeSpecifierList ::= typeSpecifier
            //
            case 116: {
                setResult(
                    new typeSpecifierList((ItypeSpecifier)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 117:  typeSpecifierList ::= typeSpecifierList ,$ typeSpecifier
            //
            case 117: {
                ((typeSpecifierList)getRhsSym(1)).add((ItypeSpecifier)getRhsSym(3));
                break;
            }
    
            default:
                break;
        }
        return;
    }
}

