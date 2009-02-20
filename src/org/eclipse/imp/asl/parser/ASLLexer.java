
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

import lpg.runtime.*;
import java.util.*;
import org.eclipse.imp.parser.ILexer;

public class ASLLexer implements RuleAction, ILexer
{
    private ASLLexerLpgLexStream lexStream;
    
    private static ParseTable prs = new ASLLexerprs();
    public ParseTable getParseTable() { return prs; }

    private LexParser lexParser = new LexParser();
    public LexParser getParser() { return lexParser; }

    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getToken(1); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public void resetKeywordLexer()
    {
        if (kwLexer == null)
              this.kwLexer = new ASLKWLexer(lexStream.getInputChars(), ASLParsersym.TK_IDENTIFIER);
        else this.kwLexer.setInputChars(lexStream.getInputChars());
    }

    public void reset(String filename, int tab) throws java.io.IOException
    {
        lexStream = new ASLLexerLpgLexStream(filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }

    public void reset(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }
    
    public void reset(char[] input_chars, String filename, int tab)
    {
        lexStream = new ASLLexerLpgLexStream(input_chars, filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }
    
    public ASLLexer(String filename, int tab) throws java.io.IOException 
    {
        reset(filename, tab);
    }

    public ASLLexer(char[] input_chars, String filename, int tab)
    {
        reset(input_chars, filename, tab);
    }

    public ASLLexer(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }

    public ASLLexer() {}

    public ILexStream getILexStream() { return lexStream; }

    /**
     * @deprecated replaced by {@link #getILexStream()}
     */
    public ILexStream getLexStream() { return lexStream; }

    private void initializeLexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (lexStream.getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");
        lexStream.setPrsStream(prsStream);
        prsStream.makeToken(start_offset, end_offset, 0); // Token list must start with a bad token
    }

    private void addEOF(IPrsStream prsStream, int end_offset)
    {
        prsStream.makeToken(end_offset, end_offset, ASLParsersym.TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
    }

    public void lexer(IPrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream)
    {
        initializeLexer(prsStream, 0, -1);
        lexParser.parseCharacters(monitor);  // Lex the input characters
        addEOF(prsStream, lexStream.getStreamIndex());
    }

    public void lexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        lexer(null, prsStream, start_offset, end_offset);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (start_offset <= 1)
             initializeLexer(prsStream, 0, -1);
        else initializeLexer(prsStream, start_offset - 1, start_offset - 1);

        lexParser.parseCharacters(monitor, start_offset, end_offset);

        addEOF(prsStream, (end_offset >= lexStream.getStreamIndex() ? lexStream.getStreamIndex() : end_offset + 1));
    }

    /**
     * If a parse stream was not passed to this Lexical analyser then we
     * simply report a lexical error. Otherwise, we produce a bad token.
     */
    public void reportLexicalError(int startLoc, int endLoc) {
        IPrsStream prs_stream = lexStream.getPrsStream();
        if (prs_stream == null)
            lexStream.reportLexicalError(startLoc, endLoc);
        else {
            //
            // Remove any token that may have been processed that fall in the
            // range of the lexical error... then add one error token that spans
            // the error range.
            //
            for (int i = prs_stream.getSize() - 1; i > 0; i--) {
                if (prs_stream.getStartOffset(i) >= startLoc)
                     prs_stream.removeLastToken();
                else break;
            }
            prs_stream.makeToken(startLoc, endLoc, 0); // add an error token to the prsStream
        }        
    }

    //
    // The Lexer contains an array of characters as the input stream to be parsed.
    // There are methods to retrieve and classify characters.
    // The lexparser "token" is implemented simply as the index of the next character in the array.
    // The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
    // method getKind.  The template defines the Lexer class and the lexer() method.
    // A driver creates the action class, "Lexer", passing an Option object to the constructor.
    //
    ASLKWLexer kwLexer;
    boolean printTokens;
    private final static int ECLIPSE_TAB_VALUE = 4;

    public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

    public ASLLexer(String filename) throws java.io.IOException
    {
        this(filename, ECLIPSE_TAB_VALUE);
        this.kwLexer = new ASLKWLexer(lexStream.getInputChars(), ASLParsersym.TK_IDENTIFIER);
    }

    /**
     * @deprecated function replaced by {@link #reset(char [] content, String filename)}
     */
    public void initialize(char [] content, String filename)
    {
        reset(content, filename);
    }
    
    final void makeToken(int left_token, int right_token, int kind)
    {
        lexStream.makeToken(left_token, right_token, kind);
    }
    
    final void makeToken(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    final void makeComment(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
    }

    final void skipToken()
    {
        if (printTokens) printValue(getLeftSpan(), getRightSpan());
    }
    
    final void checkForKeyWord()
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    //
    // This flavor of checkForKeyWord is necessary when the default kind
    // (which is returned when the keyword filter doesn't match) is something
    // other than _IDENTIFIER.
    //
    final void checkForKeyWord(int defaultKind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        if (kwKind == ASLParsersym.TK_IDENTIFIER)
            kwKind = defaultKind;
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    final void printValue(int startOffset, int endOffset)
    {
        String s = new String(lexStream.getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    //
    //
    //
    static class ASLLexerLpgLexStream extends LpgLexStream
    {
    public final static int tokenKind[] =
    {
        ASLLexersym.Char_CtlCharNotWS,    // 000    0x00
        ASLLexersym.Char_CtlCharNotWS,    // 001    0x01
        ASLLexersym.Char_CtlCharNotWS,    // 002    0x02
        ASLLexersym.Char_CtlCharNotWS,    // 003    0x03
        ASLLexersym.Char_CtlCharNotWS,    // 004    0x04
        ASLLexersym.Char_CtlCharNotWS,    // 005    0x05
        ASLLexersym.Char_CtlCharNotWS,    // 006    0x06
        ASLLexersym.Char_CtlCharNotWS,    // 007    0x07
        ASLLexersym.Char_CtlCharNotWS,    // 008    0x08
        ASLLexersym.Char_HT,              // 009    0x09
        ASLLexersym.Char_LF,              // 010    0x0A
        ASLLexersym.Char_CtlCharNotWS,    // 011    0x0B
        ASLLexersym.Char_FF,              // 012    0x0C
        ASLLexersym.Char_CR,              // 013    0x0D
        ASLLexersym.Char_CtlCharNotWS,    // 014    0x0E
        ASLLexersym.Char_CtlCharNotWS,    // 015    0x0F
        ASLLexersym.Char_CtlCharNotWS,    // 016    0x10
        ASLLexersym.Char_CtlCharNotWS,    // 017    0x11
        ASLLexersym.Char_CtlCharNotWS,    // 018    0x12
        ASLLexersym.Char_CtlCharNotWS,    // 019    0x13
        ASLLexersym.Char_CtlCharNotWS,    // 020    0x14
        ASLLexersym.Char_CtlCharNotWS,    // 021    0x15
        ASLLexersym.Char_CtlCharNotWS,    // 022    0x16
        ASLLexersym.Char_CtlCharNotWS,    // 023    0x17
        ASLLexersym.Char_CtlCharNotWS,    // 024    0x18
        ASLLexersym.Char_CtlCharNotWS,    // 025    0x19
        ASLLexersym.Char_CtlCharNotWS,    // 026    0x1A
        ASLLexersym.Char_CtlCharNotWS,    // 027    0x1B
        ASLLexersym.Char_CtlCharNotWS,    // 028    0x1C
        ASLLexersym.Char_CtlCharNotWS,    // 029    0x1D
        ASLLexersym.Char_CtlCharNotWS,    // 030    0x1E
        ASLLexersym.Char_CtlCharNotWS,    // 031    0x1F
        ASLLexersym.Char_Space,           // 032    0x20
        ASLLexersym.Char_Exclamation,     // 033    0x21
        ASLLexersym.Char_DoubleQuote,     // 034    0x22
        ASLLexersym.Char_Sharp,           // 035    0x23
        ASLLexersym.Char_DollarSign,      // 036    0x24
        ASLLexersym.Char_Percent,         // 037    0x25
        ASLLexersym.Char_Ampersand,       // 038    0x26
        ASLLexersym.Char_SingleQuote,     // 039    0x27
        ASLLexersym.Char_LeftParen,       // 040    0x28
        ASLLexersym.Char_RightParen,      // 041    0x29
        ASLLexersym.Char_Star,            // 042    0x2A
        ASLLexersym.Char_Plus,            // 043    0x2B
        ASLLexersym.Char_Comma,           // 044    0x2C
        ASLLexersym.Char_Minus,           // 045    0x2D
        ASLLexersym.Char_Dot,             // 046    0x2E
        ASLLexersym.Char_Slash,           // 047    0x2F
        ASLLexersym.Char_0,               // 048    0x30
        ASLLexersym.Char_1,               // 049    0x31
        ASLLexersym.Char_2,               // 050    0x32
        ASLLexersym.Char_3,               // 051    0x33
        ASLLexersym.Char_4,               // 052    0x34
        ASLLexersym.Char_5,               // 053    0x35
        ASLLexersym.Char_6,               // 054    0x36
        ASLLexersym.Char_7,               // 055    0x37
        ASLLexersym.Char_8,               // 056    0x38
        ASLLexersym.Char_9,               // 057    0x39
        ASLLexersym.Char_Colon,           // 058    0x3A
        ASLLexersym.Char_SemiColon,       // 059    0x3B
        ASLLexersym.Char_LessThan,        // 060    0x3C
        ASLLexersym.Char_Equal,           // 061    0x3D
        ASLLexersym.Char_GreaterThan,     // 062    0x3E
        ASLLexersym.Char_QuestionMark,    // 063    0x3F
        ASLLexersym.Char_AtSign,          // 064    0x40
        ASLLexersym.Char_A,               // 065    0x41
        ASLLexersym.Char_B,               // 066    0x42
        ASLLexersym.Char_C,               // 067    0x43
        ASLLexersym.Char_D,               // 068    0x44
        ASLLexersym.Char_E,               // 069    0x45
        ASLLexersym.Char_F,               // 070    0x46
        ASLLexersym.Char_G,               // 071    0x47
        ASLLexersym.Char_H,               // 072    0x48
        ASLLexersym.Char_I,               // 073    0x49
        ASLLexersym.Char_J,               // 074    0x4A
        ASLLexersym.Char_K,               // 075    0x4B
        ASLLexersym.Char_L,               // 076    0x4C
        ASLLexersym.Char_M,               // 077    0x4D
        ASLLexersym.Char_N,               // 078    0x4E
        ASLLexersym.Char_O,               // 079    0x4F
        ASLLexersym.Char_P,               // 080    0x50
        ASLLexersym.Char_Q,               // 081    0x51
        ASLLexersym.Char_R,               // 082    0x52
        ASLLexersym.Char_S,               // 083    0x53
        ASLLexersym.Char_T,               // 084    0x54
        ASLLexersym.Char_U,               // 085    0x55
        ASLLexersym.Char_V,               // 086    0x56
        ASLLexersym.Char_W,               // 087    0x57
        ASLLexersym.Char_X,               // 088    0x58
        ASLLexersym.Char_Y,               // 089    0x59
        ASLLexersym.Char_Z,               // 090    0x5A
        ASLLexersym.Char_LeftBracket,     // 091    0x5B
        ASLLexersym.Char_BackSlash,       // 092    0x5C
        ASLLexersym.Char_RightBracket,    // 093    0x5D
        ASLLexersym.Char_Caret,           // 094    0x5E
        ASLLexersym.Char__,               // 095    0x5F
        ASLLexersym.Char_BackQuote,       // 096    0x60
        ASLLexersym.Char_a,               // 097    0x61
        ASLLexersym.Char_b,               // 098    0x62
        ASLLexersym.Char_c,               // 099    0x63
        ASLLexersym.Char_d,               // 100    0x64
        ASLLexersym.Char_e,               // 101    0x65
        ASLLexersym.Char_f,               // 102    0x66
        ASLLexersym.Char_g,               // 103    0x67
        ASLLexersym.Char_h,               // 104    0x68
        ASLLexersym.Char_i,               // 105    0x69
        ASLLexersym.Char_j,               // 106    0x6A
        ASLLexersym.Char_k,               // 107    0x6B
        ASLLexersym.Char_l,               // 108    0x6C
        ASLLexersym.Char_m,               // 109    0x6D
        ASLLexersym.Char_n,               // 110    0x6E
        ASLLexersym.Char_o,               // 111    0x6F
        ASLLexersym.Char_p,               // 112    0x70
        ASLLexersym.Char_q,               // 113    0x71
        ASLLexersym.Char_r,               // 114    0x72
        ASLLexersym.Char_s,               // 115    0x73
        ASLLexersym.Char_t,               // 116    0x74
        ASLLexersym.Char_u,               // 117    0x75
        ASLLexersym.Char_v,               // 118    0x76
        ASLLexersym.Char_w,               // 119    0x77
        ASLLexersym.Char_x,               // 120    0x78
        ASLLexersym.Char_y,               // 121    0x79
        ASLLexersym.Char_z,               // 122    0x7A
        ASLLexersym.Char_LeftBrace,       // 123    0x7B
        ASLLexersym.Char_VerticalBar,     // 124    0x7C
        ASLLexersym.Char_RightBrace,      // 125    0x7D
        ASLLexersym.Char_Tilde,           // 126    0x7E

        ASLLexersym.Char_AfterASCII,      // for all chars in range 128..65534
        ASLLexersym.Char_EOF              // for '\uffff' or 65535 
    };
            
    public final int getKind(int i)  // Classify character at ith location
    {
        int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
        return (c < 128 // ASCII Character
                  ? tokenKind[c]
                  : c == '\uffff'
                       ? ASLLexersym.Char_EOF
                       : ASLLexersym.Char_AfterASCII);
    }

    public String[] orderedExportedSymbols() { return ASLParsersym.orderedTerminalSymbols; }

    public ASLLexerLpgLexStream(String filename, int tab) throws java.io.IOException
    {
        super(filename, tab);
    }

    public ASLLexerLpgLexStream(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public ASLLexerLpgLexStream(char[] input_chars, String filename)
    {
        super(input_chars, filename, 1);
    }
    }

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= identifier
            //
            case 1: { 
            
                checkForKeyWord();
                  break;
            }
    
            //
            // Rule 2:  Token ::= IntegerLiteral
            //
            case 2: { 
            
                makeToken(ASLParsersym.TK_IntegerLiteral);
                  break;
            }
    
            //
            // Rule 3:  Token ::= DoubleLiteral
            //
            case 3: { 
            
                makeToken(ASLParsersym.TK_DoubleLiteral);
                  break;
            }
    
            //
            // Rule 4:  Token ::= white
            //
            case 4: { 
            
                skipToken();
                  break;
            }
    
            //
            // Rule 5:  Token ::= slc
            //
            case 5: { 
            
                makeComment(ASLParsersym.TK_SINGLE_LINE_COMMENT);
                  break;
            }
    
            //
            // Rule 6:  Token ::= .
            //
            case 6: { 
            
                makeToken(ASLParsersym.TK_DOT);
                  break;
            }
    
            //
            // Rule 7:  Token ::= ;
            //
            case 7: { 
            
                makeToken(ASLParsersym.TK_SEMICOLON);
                  break;
            }
    
            //
            // Rule 8:  Token ::= :
            //
            case 8: { 
            
                makeToken(ASLParsersym.TK_COLON);
                  break;
            }
    
            //
            // Rule 9:  Token ::= ,
            //
            case 9: { 
            
                makeToken(ASLParsersym.TK_COMMA);
                  break;
            }
    
            //
            // Rule 10:  Token ::= +
            //
            case 10: { 
            
                makeToken(ASLParsersym.TK_PLUS);
                  break;
            }
    
            //
            // Rule 11:  Token ::= *
            //
            case 11: { 
            
                makeToken(ASLParsersym.TK_STAR);
                  break;
            }
    
            //
            // Rule 12:  Token ::= : =
            //
            case 12: { 
            
                makeToken(ASLParsersym.TK_ASSIGN);
                  break;
            }
    
            //
            // Rule 13:  Token ::= (
            //
            case 13: { 
            
                makeToken(ASLParsersym.TK_LEFTPAREN);
                  break;
            }
    
            //
            // Rule 14:  Token ::= )
            //
            case 14: { 
            
                makeToken(ASLParsersym.TK_RIGHTPAREN);
                  break;
            }
    
            //
            // Rule 15:  Token ::= {
            //
            case 15: { 
            
                makeToken(ASLParsersym.TK_LEFTBRACE);
                  break;
            }
    
            //
            // Rule 16:  Token ::= }
            //
            case 16: { 
            
                makeToken(ASLParsersym.TK_RIGHTBRACE);
                  break;
            }
    
            //
            // Rule 17:  Token ::= [
            //
            case 17: { 
            
                makeToken(ASLParsersym.TK_LEFTBRACKET);
                  break;
            }
    
            //
            // Rule 18:  Token ::= ]
            //
            case 18: { 
            
                makeToken(ASLParsersym.TK_RIGHTBRACKET);
                  break;
            }
    
            //
            // Rule 19:  Token ::= >
            //
            case 19: { 
            
                makeToken(ASLParsersym.TK_GREATER);
                  break;
            }
    
            //
            // Rule 20:  Token ::= <
            //
            case 20: { 
            
                makeToken(ASLParsersym.TK_LESS);
                  break;
            }
    
            //
            // Rule 21:  Token ::= = >
            //
            case 21: { 
            
                makeToken(ASLParsersym.TK_ARROW);
                  break;
            }
    
            //
            // Rule 22:  Token ::= =
            //
            case 22: { 
            
                makeToken(ASLParsersym.TK_EQUAL);
                  break;
            }
    
            //
            // Rule 23:  Token ::= ! =
            //
            case 23: { 
            
                makeToken(ASLParsersym.TK_NOTEQUAL);
                  break;
            }
    
            //
            // Rule 24:  Token ::= |
            //
            case 24: { 
            
                makeToken(ASLParsersym.TK_VBAR);
                  break;
            }
     
            //
            // Rule 25:  Token ::= StringLiteral
            //
            case 25: { 
            
                makeToken(ASLParsersym.TK_StringLiteral);
                  break;
            }
     
    
            default:
                break;
        }
        return;
    }
}

