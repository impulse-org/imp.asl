%options package=org.eclipse.imp.asl.parser
%options template=LexerTemplateF.gi
%options filter=ASLKWLexer.gi

%Globals
    /.import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$ASLKWLexer./
%End

%Include
    LexerBasicMapF.gi
%End

%Export
    SINGLE_LINE_COMMENT
    IDENTIFIER 
    DoubleLiteral
    IntegerLiteral
    StringLiteral
    COMMA
    COLON
    SEMICOLON
    DOT
    ARROW
    ASSIGN
    GREATER
    LESS
    EQUAL
    NOTEQUAL
    PLUS
    STAR
    VBAR

    LEFTPAREN
    RIGHTPAREN
    LEFTBRACE
    RIGHTBRACE
    LEFTBRACKET
    RIGHTBRACKET
%End

%Terminals
    CtlCharNotWS

    LF   CR   HT   FF

    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    _

    A    B    C    D    E    F    G    H    I    J    K    L    M
    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    0    1    2    3    4    5    6    7    8    9

    AfterASCII   ::= '\u0080..\ufffe'
    Space        ::= ' '
    LF           ::= NewLine
    CR           ::= Return
    HT           ::= HorizontalTab
    FF           ::= FormFeed
    DoubleQuote  ::= '"'
    SingleQuote  ::= "'"
    Percent      ::= '%'
    VerticalBar  ::= '|'
    Exclamation  ::= '!'
    AtSign       ::= '@'
    BackQuote    ::= '`'
    Tilde        ::= '~'
    Sharp        ::= '#'
    DollarSign   ::= '$'
    Ampersand    ::= '&'
    Caret        ::= '^'
    Colon        ::= ':'
    SemiColon    ::= ';'
    BackSlash    ::= '\'
    LeftBrace    ::= '{'
    RightBrace   ::= '}'
    LeftBracket  ::= '['
    RightBracket ::= ']'
    QuestionMark ::= '?'
    Comma        ::= ','
    Dot          ::= '.'
    LessThan     ::= '<'
    GreaterThan  ::= '>'
    Plus         ::= '+'
    Minus        ::= '-'
    Slash        ::= '/'
    Star         ::= '*'
    LeftParen    ::= '('
    RightParen   ::= ')'
    Equal        ::= '='
%End

%Start
    Token
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
    Token ::= identifier
        /.$BeginJava
                    checkForKeyWord();
          $EndJava
        ./
    Token ::= IntegerLiteral
        /.$BeginJava
                    makeToken($_IntegerLiteral);
          $EndJava
        ./
    Token ::= DoubleLiteral
        /.$BeginJava
                    makeToken($_DoubleLiteral);
          $EndJava
        ./
    Token ::= white
        /.$BeginJava
                    skipToken();
          $EndJava
        ./
    Token ::= slc
        /.$BeginJava
                    makeComment($_SINGLE_LINE_COMMENT);
          $EndJava
        ./

    Token ::= '.'
        /.$BeginJava
                    makeToken($_DOT);
          $EndJava
        ./

    Token ::= ';'
        /.$BeginJava
                    makeToken($_SEMICOLON);
          $EndJava
        ./

    Token ::= ':'
        /.$BeginJava
                    makeToken($_COLON);
          $EndJava
        ./

    Token ::= ','
        /.$BeginJava
                    makeToken($_COMMA);
          $EndJava
        ./

    Token ::= '+'
        /.$BeginJava
                    makeToken($_PLUS);
          $EndJava
        ./

    Token ::= '*'
        /.$BeginJava
                    makeToken($_STAR);
          $EndJava
        ./

    Token ::= ':' '='
        /.$BeginJava
                    makeToken($_ASSIGN);
          $EndJava
        ./

    Token ::= '('
        /.$BeginJava
                    makeToken($_LEFTPAREN);
          $EndJava
        ./

    Token ::= ')'
        /.$BeginJava
                    makeToken($_RIGHTPAREN);
          $EndJava
        ./

    Token ::= '{'
        /.$BeginJava
                    makeToken($_LEFTBRACE);
          $EndJava
        ./

    Token ::= '}'
        /.$BeginJava
                    makeToken($_RIGHTBRACE);
          $EndJava
        ./

    Token ::= '['
        /.$BeginJava
                    makeToken($_LEFTBRACKET);
          $EndJava
        ./

    Token ::= ']'
        /.$BeginJava
                    makeToken($_RIGHTBRACKET);
          $EndJava
        ./

    Token ::= '>'
        /.$BeginJava
                    makeToken($_GREATER);
          $EndJava
        ./

    Token ::= '<'
        /.$BeginJava
                    makeToken($_LESS);
          $EndJava
        ./

    Token ::= '=' '>'
        /.$BeginJava
                    makeToken($_ARROW);
          $EndJava
        ./

    Token ::= '='
        /.$BeginJava
                    makeToken($_EQUAL);
          $EndJava
        ./

    Token ::= '!' '='
        /.$BeginJava
                    makeToken($_NOTEQUAL);
          $EndJava
        ./

    Token ::= '|'
        /.$BeginJava
                    makeToken($_VBAR);
          $EndJava
         ./
    Token ::= StringLiteral
        /.$BeginJava
                    makeToken($_StringLiteral);
          $EndJava
         ./

    identifier -> letter
                | identifier letter
                | identifier digit

    IntegerLiteral ::= digit
             | IntegerLiteral digit

    DoubleLiteral ::= Decimal
                    | Decimal Exponent
                    | IntegerLiteral Exponent
                    
    Exponent ::= LetterEe IntegerLiteral
               | LetterEe '-' IntegerLiteral
               | LetterEe '+' IntegerLiteral

    LetterEe ::= 'e'
               | 'E'

    Decimal ::= '.' IntegerLiteral
              | IntegerLiteral '.'
              | IntegerLiteral '.' IntegerLiteral

    StringLiteral ::= '"' stringChars '"'

    stringChars ::= notDoubleQuote
                |   stringChars notDoubleQuote

    white ::= whiteChar
            | white whiteChar

    slc ::= '/' '/'
          | slc notEOL

    digit ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

    aA ::= a | A
    bB ::= b | B
    cC ::= c | C
    dD ::= d | D
    eE ::= e | E
    fF ::= f | F
    gG ::= g | G
    hH ::= h | H
    iI ::= i | I
    jJ ::= j | J
    kK ::= k | K
    lL ::= l | L
    mM ::= m | M
    nN ::= n | N
    oO ::= o | O
    pP ::= p | P
    qQ ::= q | Q
    rR ::= r | R
    sS ::= s | S
    tT ::= t | T
    uU ::= u | U
    vV ::= v | V
    wW ::= w | W
    xX ::= x | X
    yY ::= y | Y
    zZ ::= z | Z

    letter ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ

    -- any ::= letter | digit | special | white

    whiteChar ::= Space | LF | CR | HT | FF

    special ::= '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    specialNoDoubleQuote ::= '+' | '-' | '(' | ')'      | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    notEOL ::= letter | digit | special | Space | HT | FF

    notDoubleQuote ::= letter | digit | specialNoDoubleQuote | Space | HT
%End
