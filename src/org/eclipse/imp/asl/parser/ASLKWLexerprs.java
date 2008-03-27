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

public class ASLKWLexerprs implements lpg.runtime.ParseTable, ASLKWLexersym {

    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            8,5,7,4,11,6,4,3,9,5,
            3,9,3,8,6,5,7,3,6,4,
            4,5
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,25,25,41,28,17,32,
            43,29,19,40,30,46,5,49,50,51,
            53,56,57,58,24,64,63,68,69,72,
            73,75,76,77,80,81,82,87,92,89,
            94,96,98,99,101,103,106,109,107,111,
            114,116,117,118,122,124,119,127,130,131,
            132,135,137,138,140,146,148,142,144,150,
            152,151,158,154,163,165,166,167,168,170,
            172,177,176,182,183,184,185,190,187,192,
            195,200,197,203,205,117,117
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,0,5,6,3,4,9,
            10,0,12,13,14,15,0,1,0,3,
            2,5,4,0,0,7,3,0,0,0,
            7,0,1,5,5,12,9,8,11,0,
            0,10,0,19,4,0,4,8,0,0,
            0,0,0,8,6,0,0,0,3,3,
            10,9,0,0,7,16,3,0,0,7,
            3,0,0,2,0,0,0,2,4,0,
            0,0,10,15,8,5,0,1,0,8,
            11,0,1,0,6,0,1,0,0,6,
            0,1,0,5,2,0,0,10,0,1,
            0,6,2,0,1,0,0,0,0,13,
            2,0,7,0,7,4,0,4,2,0,
            0,0,16,3,0,1,0,0,9,0,
            9,0,3,0,7,0,1,0,1,0,
            0,0,11,0,18,4,6,0,5,2,
            17,12,0,1,0,0,0,0,2,0,
            5,0,1,9,0,0,0,8,11,3,
            5,0,0,0,0,4,0,4,6,0,
            1,0,6,2,0,0,0,3,14,0,
            4,2,0,0,0,3,2,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            117,32,28,27,117,36,30,54,55,29,
            26,117,31,35,34,33,117,41,117,40,
            47,42,48,117,117,49,62,117,117,117,
            63,117,44,46,52,130,39,51,38,117,
            117,43,117,116,37,117,45,50,117,117,
            117,117,117,53,56,117,117,117,135,60,
            58,59,117,117,61,57,64,117,117,65,
            66,117,117,67,117,117,117,70,69,117,
            117,117,68,125,71,73,117,138,117,74,
            72,117,137,117,75,117,77,117,117,76,
            11,80,117,79,81,117,117,78,117,124,
            117,82,84,117,121,117,117,117,117,83,
            89,117,85,117,87,139,117,88,133,117,
            117,117,86,91,117,127,117,117,90,117,
            92,117,95,117,94,117,96,117,119,117,
            117,117,97,117,93,132,99,117,101,100,
            136,98,117,123,117,117,117,117,104,117,
            103,117,106,102,117,117,117,105,134,107,
            108,117,117,117,117,120,117,131,109,117,
            111,117,112,118,117,117,117,129,110,117,
            113,126,117,117,117,114,122
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 91,
           NT_OFFSET         = 55,
           LA_STATE_OFFSET   = 139,
           MAX_LA            = 1,
           NUM_RULES         = 22,
           NUM_NONTERMINALS  = 2,
           NUM_SYMBOLS       = 57,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 23,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 19,
           EOLT_SYMBOL       = 56,
           ACCEPT_ACTION     = 116,
           ERROR_ACTION      = 117;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
