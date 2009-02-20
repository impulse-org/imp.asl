
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

public class ASLKWLexer extends ASLKWLexerprs
{
    private char[] inputChars;
    private final int keywordKind[] = new int[22 + 1];

    public int[] getKeywordKinds() { return keywordKind; }

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? ASLKWLexersym.Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }

    public void setInputChars(char[] inputChars) { this.inputChars = inputChars; }


    final static int tokenKind[] = new int[128];
    static
    {
        tokenKind['$'] = ASLKWLexersym.Char_DollarSign;
        tokenKind['%'] = ASLKWLexersym.Char_Percent;
        tokenKind['_'] = ASLKWLexersym.Char__;

        tokenKind['a'] = ASLKWLexersym.Char_a;
        tokenKind['b'] = ASLKWLexersym.Char_b;
        tokenKind['c'] = ASLKWLexersym.Char_c;
        tokenKind['d'] = ASLKWLexersym.Char_d;
        tokenKind['e'] = ASLKWLexersym.Char_e;
        tokenKind['f'] = ASLKWLexersym.Char_f;
        tokenKind['g'] = ASLKWLexersym.Char_g;
        tokenKind['h'] = ASLKWLexersym.Char_h;
        tokenKind['i'] = ASLKWLexersym.Char_i;
        tokenKind['j'] = ASLKWLexersym.Char_j;
        tokenKind['k'] = ASLKWLexersym.Char_k;
        tokenKind['l'] = ASLKWLexersym.Char_l;
        tokenKind['m'] = ASLKWLexersym.Char_m;
        tokenKind['n'] = ASLKWLexersym.Char_n;
        tokenKind['o'] = ASLKWLexersym.Char_o;
        tokenKind['p'] = ASLKWLexersym.Char_p;
        tokenKind['q'] = ASLKWLexersym.Char_q;
        tokenKind['r'] = ASLKWLexersym.Char_r;
        tokenKind['s'] = ASLKWLexersym.Char_s;
        tokenKind['t'] = ASLKWLexersym.Char_t;
        tokenKind['u'] = ASLKWLexersym.Char_u;
        tokenKind['v'] = ASLKWLexersym.Char_v;
        tokenKind['w'] = ASLKWLexersym.Char_w;
        tokenKind['x'] = ASLKWLexersym.Char_x;
        tokenKind['y'] = ASLKWLexersym.Char_y;
        tokenKind['z'] = ASLKWLexersym.Char_z;

        tokenKind['A'] = ASLKWLexersym.Char_A;
        tokenKind['B'] = ASLKWLexersym.Char_B;
        tokenKind['C'] = ASLKWLexersym.Char_C;
        tokenKind['D'] = ASLKWLexersym.Char_D;
        tokenKind['E'] = ASLKWLexersym.Char_E;
        tokenKind['F'] = ASLKWLexersym.Char_F;
        tokenKind['G'] = ASLKWLexersym.Char_G;
        tokenKind['H'] = ASLKWLexersym.Char_H;
        tokenKind['I'] = ASLKWLexersym.Char_I;
        tokenKind['J'] = ASLKWLexersym.Char_J;
        tokenKind['K'] = ASLKWLexersym.Char_K;
        tokenKind['L'] = ASLKWLexersym.Char_L;
        tokenKind['M'] = ASLKWLexersym.Char_M;
        tokenKind['N'] = ASLKWLexersym.Char_N;
        tokenKind['O'] = ASLKWLexersym.Char_O;
        tokenKind['P'] = ASLKWLexersym.Char_P;
        tokenKind['Q'] = ASLKWLexersym.Char_Q;
        tokenKind['R'] = ASLKWLexersym.Char_R;
        tokenKind['S'] = ASLKWLexersym.Char_S;
        tokenKind['T'] = ASLKWLexersym.Char_T;
        tokenKind['U'] = ASLKWLexersym.Char_U;
        tokenKind['V'] = ASLKWLexersym.Char_V;
        tokenKind['W'] = ASLKWLexersym.Char_W;
        tokenKind['X'] = ASLKWLexersym.Char_X;
        tokenKind['Y'] = ASLKWLexersym.Char_Y;
        tokenKind['Z'] = ASLKWLexersym.Char_Z;
    };

    final int getKind(char c)
    {
        return (((c & 0xFFFFFF80) == 0) /* 0 <= c < 128? */ ? tokenKind[c] : 0);
    }


    public ASLKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  Keyword ::= a n a l y s i s
        //
        
        keywordKind[1] = (ASLParsersym.TK_analysis);
      
    
        //
        // Rule 2:  Keyword ::= a t y p e
        //
        
        keywordKind[2] = (ASLParsersym.TK_atype);
      
    
        //
        // Rule 3:  Keyword ::= b o o l e a n
        //
        
        keywordKind[3] = (ASLParsersym.TK_boolean);
      
    
        //
        // Rule 4:  Keyword ::= c a s e
        //
        
        keywordKind[4] = (ASLParsersym.TK_case);
      
    
        //
        // Rule 5:  Keyword ::= c o n s t r a i n t s
        //
        
        keywordKind[5] = (ASLParsersym.TK_constraints);
      
    
        //
        // Rule 6:  Keyword ::= d o u b l e
        //
        
        keywordKind[6] = (ASLParsersym.TK_double);
      
    
        //
        // Rule 7:  Keyword ::= e l s e
        //
        
        keywordKind[7] = (ASLParsersym.TK_else);
      
    
        //
        // Rule 8:  Keyword ::= e n d
        //
        
        keywordKind[8] = (ASLParsersym.TK_end);
      
    
        //
        // Rule 9:  Keyword ::= e s t i m a t e s
        //
        
        keywordKind[9] = (ASLParsersym.TK_estimates);
      
    
        //
        // Rule 10:  Keyword ::= f a l s e
        //
        
        keywordKind[10] = (ASLParsersym.TK_false);
      
    
        //
        // Rule 11:  Keyword ::= i n t
        //
        
        keywordKind[11] = (ASLParsersym.TK_int);
      
    
        //
        // Rule 12:  Keyword ::= i n t e r s e c t
        //
        
        keywordKind[12] = (ASLParsersym.TK_intersect);
      
    
        //
        // Rule 13:  Keyword ::= r e f
        //
        
        keywordKind[13] = (ASLParsersym.TK_ref);
      
    
        //
        // Rule 14:  Keyword ::= r e l a t i o n
        //
        
        keywordKind[14] = (ASLParsersym.TK_relation);
      
    
        //
        // Rule 15:  Keyword ::= r e t u r n
        //
        
        keywordKind[15] = (ASLParsersym.TK_return);
      
    
        //
        // Rule 16:  Keyword ::= r u l e s
        //
        
        keywordKind[16] = (ASLParsersym.TK_rules);
      
    
        //
        // Rule 17:  Keyword ::= s a t i s f y
        //
        
        keywordKind[17] = (ASLParsersym.TK_satisfy);
      
    
        //
        // Rule 18:  Keyword ::= s e t
        //
        
        keywordKind[18] = (ASLParsersym.TK_set);
      
    
        //
        // Rule 19:  Keyword ::= s t r i n g
        //
        
        keywordKind[19] = (ASLParsersym.TK_string);
      
    
        //
        // Rule 20:  Keyword ::= t r u e
        //
        
        keywordKind[20] = (ASLParsersym.TK_true);
      
    
        //
        // Rule 21:  Keyword ::= t y p e
        //
        
        keywordKind[21] = (ASLParsersym.TK_type);
      
    
        //
        // Rule 22:  Keyword ::= u n i o n
        //
        
        keywordKind[22] = (ASLParsersym.TK_union);
      
    
        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

