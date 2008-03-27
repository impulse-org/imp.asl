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

public class ASLLexerprs implements lpg.runtime.ParseTable, ASLLexersym {

    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
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
            10,4,9,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,36,37,38,39,40,41,42,43,12,
            13,15,16,45,2,3,5,6,7,8,
            11,14,17,44,1
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
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,1,1,1,1,1,1,1,1,1,
            1,2,1,1,1,1,1,1,1,1,
            2,1,2,1,1,1,2,2,1,2,
            1,2,2,2,3,3,1,1,2,2,
            3,3,1,2,1,2,2,2,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            35,35,35,35,35,35,35,35,35,35,
            35,35,35,35,35,35,35,35,35,35,
            35,35,35,35,35,35,36,36,36,2,
            2,37,37,37,30,30,30,31,31,41,
            41,41,40,42,42,38,38,39,39,1,
            1,1,1,1,1,1,1,1,1,4,
            4,5,5,6,6,7,7,8,8,9,
            9,10,10,11,11,12,12,13,13,14,
            14,15,15,16,16,17,17,18,18,19,
            19,20,20,21,21,22,22,23,23,24,
            24,25,25,26,26,27,27,28,28,29,
            29,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,33,33,33,
            33,33,44,44,44,44,44,44,44,44,
            44,44,44,44,44,44,44,44,44,44,
            44,44,44,44,44,44,44,44,44,44,
            44,44,44,44,34,34,34,34,34,34,
            34,34,34,34,34,34,34,34,34,34,
            34,34,34,34,34,34,34,34,34,34,
            34,34,34,34,34,43,43,43,43,43,
            43,32,32,32,32,32,295,29,408,26,
            111,112,113,114,115,116,117,118,119,120,
            121,122,123,124,125,126,127,128,129,130,
            131,132,133,134,135,136,382,29,288,45,
            183,250,378,3,340,298,25,380,197,212,
            577,211,111,112,113,114,115,116,117,118,
            119,120,121,122,123,124,125,126,127,128,
            129,130,131,132,133,134,135,136,484,30,
            43,517,213,473,29,296,527,30,1,206,
            343,205,111,112,113,114,115,116,117,118,
            119,120,121,122,123,124,125,126,127,128,
            129,130,131,132,133,134,135,136,495,29,
            337,506,29,412,506,29,414,542,30,578,
            524,48,207,99,212,523,211,111,112,113,
            114,115,116,117,118,119,120,121,122,123,
            124,125,126,127,128,129,130,131,132,133,
            134,135,136,46,442,44,442,213,395,28,
            576,27,111,112,113,114,115,116,117,118,
            119,120,121,122,123,124,125,126,127,128,
            129,130,131,132,133,134,135,136,460,30,
            32,293,554,30,565,30,442,442,442,442,
            442,442,442,442,442,442,442,442,442,442,
            442,442,442,442,442,442,442,442,33,293,
            442,442
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,67,68,69,70,71,
            72,73,74,75,76,77,78,79,80,81,
            82,83,0,85,86,87,88,89,90,91,
            92,93,94,95,96,97,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            0,85,100,87,88,89,90,91,92,93,
            94,95,96,97,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,73,74,75,
            76,77,78,79,0,81,82,83,84,0,
            86,0,1,2,3,4,5,6,7,8,
            9,10,98,99,0,1,2,3,4,5,
            6,7,8,9,10,11,12,0,0,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,0,1,2,3,4,5,6,7,
            8,9,10,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,9,10,0,1,2,3,4,
            5,6,7,8,9,10,0,0,0,0,
            0,0,0,0,0,66,0,1,2,3,
            4,5,6,7,8,9,10,14,0,0,
            68,0,1,2,3,4,5,6,7,8,
            9,10,80,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,9,10,0,0,0,0,0,
            0,0,0,67,0,0,11,12,65,13,
            13,69,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,84,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,98,99,0,0,0,0,0,0,0,
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
            5,491,492,493,494,495,496,497,498,499,
            500,509,510,610,651,501,503,505,507,511,
            513,515,517,519,521,523,525,527,529,531,
            533,535,537,539,541,543,545,547,549,551,
            502,504,506,508,512,514,516,518,520,522,
            524,526,528,530,532,534,536,538,540,542,
            544,546,548,550,552,650,593,609,584,614,
            601,589,597,598,602,603,604,605,607,608,
            585,612,586,587,652,613,588,599,594,590,
            591,592,611,615,595,596,600,606,442,491,
            492,493,494,495,496,497,498,499,500,509,
            510,641,657,501,503,505,507,511,513,515,
            517,519,521,523,525,527,529,531,533,535,
            537,539,541,543,545,547,549,551,502,504,
            506,508,512,514,516,518,520,522,524,526,
            528,530,532,534,536,538,540,542,544,546,
            548,550,552,656,624,640,616,645,632,620,
            628,629,633,634,635,636,638,639,617,643,
            618,619,442,644,484,630,625,621,622,623,
            642,646,626,627,631,637,442,491,492,493,
            494,495,496,497,498,499,500,509,510,641,
            657,501,503,505,507,511,513,515,517,519,
            521,523,525,527,529,531,533,535,537,539,
            541,543,545,547,549,551,502,504,506,508,
            512,514,516,518,520,522,524,526,528,530,
            532,534,536,538,540,542,544,546,548,550,
            552,656,624,640,616,645,632,620,628,629,
            633,634,635,636,638,639,617,643,618,619,
            442,644,441,630,625,621,622,623,642,646,
            626,627,631,637,442,491,492,493,494,495,
            496,497,498,499,500,509,510,291,582,501,
            503,505,507,511,513,515,517,519,521,523,
            525,527,529,531,533,535,537,539,541,543,
            545,547,549,551,502,504,506,508,512,514,
            516,518,520,522,524,526,528,530,532,534,
            536,538,540,542,544,546,548,550,552,579,
            246,461,452,345,466,260,339,449,457,458,
            459,460,451,462,442,453,455,456,583,442,
            258,6,491,492,493,494,495,496,497,498,
            499,500,580,581,1,491,492,493,494,495,
            496,497,498,499,500,509,510,442,442,501,
            503,505,507,511,513,515,517,519,521,523,
            525,527,529,531,533,535,537,539,541,543,
            545,547,549,551,502,504,506,508,512,514,
            516,518,520,522,524,526,528,530,532,534,
            536,538,540,542,544,546,548,550,552,2,
            491,492,493,494,495,496,497,498,499,500,
            479,480,442,491,492,493,494,495,496,497,
            498,499,500,39,491,492,493,494,495,496,
            497,498,499,500,40,491,492,493,494,495,
            496,497,498,499,500,442,491,492,493,494,
            495,496,497,498,499,500,22,442,442,442,
            442,442,442,4,442,328,34,491,492,493,
            494,495,496,497,498,499,500,582,442,442,
            331,41,491,492,493,494,495,496,497,498,
            499,500,334,36,491,492,493,494,495,496,
            497,498,499,500,35,491,492,493,494,495,
            496,497,498,499,500,31,442,8,442,442,
            442,442,442,463,442,442,479,480,579,465,
            454,489,442,442,442,442,442,442,442,442,
            442,442,442,442,442,442,442,583,442,442,
            442,442,442,442,442,442,442,442,442,442,
            442,580,581
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
           NUM_STATES        = 23,
           NT_OFFSET         = 102,
           LA_STATE_OFFSET   = 657,
           MAX_LA            = 1,
           NUM_RULES         = 215,
           NUM_NONTERMINALS  = 45,
           NUM_SYMBOLS       = 147,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 216,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 100,
           EOLT_SYMBOL       = 103,
           ACCEPT_ACTION     = 441,
           ERROR_ACTION      = 442;

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
