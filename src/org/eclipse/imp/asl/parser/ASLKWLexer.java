package org.eclipse.imp.asl.parser;

import lpg.runtime.*;

public class ASLKWLexer extends ASLKWLexerprs implements ASLParsersym
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
                                   ? Char_EOF
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
        tokenKind['$'] = Char_DollarSign;
        tokenKind['_'] = Char__;

        tokenKind['a'] = Char_a;
        tokenKind['b'] = Char_b;
        tokenKind['c'] = Char_c;
        tokenKind['d'] = Char_d;
        tokenKind['e'] = Char_e;
        tokenKind['f'] = Char_f;
        tokenKind['g'] = Char_g;
        tokenKind['h'] = Char_h;
        tokenKind['i'] = Char_i;
        tokenKind['j'] = Char_j;
        tokenKind['k'] = Char_k;
        tokenKind['l'] = Char_l;
        tokenKind['m'] = Char_m;
        tokenKind['n'] = Char_n;
        tokenKind['o'] = Char_o;
        tokenKind['p'] = Char_p;
        tokenKind['q'] = Char_q;
        tokenKind['r'] = Char_r;
        tokenKind['s'] = Char_s;
        tokenKind['t'] = Char_t;
        tokenKind['u'] = Char_u;
        tokenKind['v'] = Char_v;
        tokenKind['w'] = Char_w;
        tokenKind['x'] = Char_x;
        tokenKind['y'] = Char_y;
        tokenKind['z'] = Char_z;

        tokenKind['A'] = Char_A;
        tokenKind['B'] = Char_B;
        tokenKind['C'] = Char_C;
        tokenKind['D'] = Char_D;
        tokenKind['E'] = Char_E;
        tokenKind['F'] = Char_F;
        tokenKind['G'] = Char_G;
        tokenKind['H'] = Char_H;
        tokenKind['I'] = Char_I;
        tokenKind['J'] = Char_J;
        tokenKind['K'] = Char_K;
        tokenKind['L'] = Char_L;
        tokenKind['M'] = Char_M;
        tokenKind['N'] = Char_N;
        tokenKind['O'] = Char_O;
        tokenKind['P'] = Char_P;
        tokenKind['Q'] = Char_Q;
        tokenKind['R'] = Char_R;
        tokenKind['S'] = Char_S;
        tokenKind['T'] = Char_T;
        tokenKind['U'] = Char_U;
        tokenKind['V'] = Char_V;
        tokenKind['W'] = Char_W;
        tokenKind['X'] = Char_X;
        tokenKind['Y'] = Char_Y;
        tokenKind['Z'] = Char_Z;
    };

    final int getKind(char c)
    {
        return (c < 128 ? tokenKind[c] : 0);
    }


    public ASLKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  Keyword ::= a n a l y s i s
        //
        
        keywordKind[1] = (TK_analysis);
      
    
        //
        // Rule 2:  Keyword ::= a t y p e
        //
        
        keywordKind[2] = (TK_atype);
      
    
        //
        // Rule 3:  Keyword ::= b o o l e a n
        //
        
        keywordKind[3] = (TK_boolean);
      
    
        //
        // Rule 4:  Keyword ::= c a s e
        //
        
        keywordKind[4] = (TK_case);
      
    
        //
        // Rule 5:  Keyword ::= c o n s t r a i n t s
        //
        
        keywordKind[5] = (TK_constraints);
      
    
        //
        // Rule 6:  Keyword ::= d o u b l e
        //
        
        keywordKind[6] = (TK_double);
      
    
        //
        // Rule 7:  Keyword ::= e l s e
        //
        
        keywordKind[7] = (TK_else);
      
    
        //
        // Rule 8:  Keyword ::= e n d
        //
        
        keywordKind[8] = (TK_end);
      
    
        //
        // Rule 9:  Keyword ::= e s t i m a t e s
        //
        
        keywordKind[9] = (TK_estimates);
      
    
        //
        // Rule 10:  Keyword ::= f a l s e
        //
        
        keywordKind[10] = (TK_false);
      
    
        //
        // Rule 11:  Keyword ::= i n t
        //
        
        keywordKind[11] = (TK_int);
      
    
        //
        // Rule 12:  Keyword ::= i n t e r s e c t
        //
        
        keywordKind[12] = (TK_intersect);
      
    
        //
        // Rule 13:  Keyword ::= r e f
        //
        
        keywordKind[13] = (TK_ref);
      
    
        //
        // Rule 14:  Keyword ::= r e l a t i o n
        //
        
        keywordKind[14] = (TK_relation);
      
    
        //
        // Rule 15:  Keyword ::= r e t u r n
        //
        
        keywordKind[15] = (TK_return);
      
    
        //
        // Rule 16:  Keyword ::= r u l e s
        //
        
        keywordKind[16] = (TK_rules);
      
    
        //
        // Rule 17:  Keyword ::= s a t i s f y
        //
        
        keywordKind[17] = (TK_satisfy);
      
    
        //
        // Rule 18:  Keyword ::= s e t
        //
        
        keywordKind[18] = (TK_set);
      
    
        //
        // Rule 19:  Keyword ::= s t r i n g
        //
        
        keywordKind[19] = (TK_string);
      
    
        //
        // Rule 20:  Keyword ::= t r u e
        //
        
        keywordKind[20] = (TK_true);
      
    
        //
        // Rule 21:  Keyword ::= t y p e
        //
        
        keywordKind[21] = (TK_type);
      
    
        //
        // Rule 22:  Keyword ::= u n i o n
        //
        
        keywordKind[22] = (TK_union);
      
    
        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

