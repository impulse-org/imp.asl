package org.eclipse.imp.asl.parser;

public class ASLParserprs implements lpg.runtime.ParseTable, ASLParsersym {

    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0,
            0,0,0,0,1,1,0,1,0,0,
            0,0,0,0,0,0,1,0,0,1,
            0,0,0,0,0,1,0,1,1,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            13,25,33,12,34,35,36,37,38,39,
            40,41,42,43,44,3,55,56,57,58,
            59,60,52,22,45,16,18,21,23,24,
            30,31,32,48,50,51,54,61,2,4,
            5,6,7,8,9,10,11,14,15,17,
            19,20,26,27,28,29,46,47,49,53,
            1
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
            0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            2,0,2,1,1,1,1,1,1,3,
            5,0,1,1,3,2,4,1,3,2,
            0,3,1,3,2,4,2,1,2,3,
            6,2,2,11,1,2,1,1,3,6,
            3,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,3,3,3,0,1,1,
            2,3,3,0,1,1,3,4,3,2,
            0,3,1,1,4,0,1,1,3,2,
            1,2,5,0,2,3,3,2,2,1,
            1,1,1,1,1,4,3,4,2,1,
            1,1,1,1,3,1,3,-38,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,-43,0,0,-2,0,-4,0,
            0,-10,0,-5,0,-90,0,-7,0,-8,
            0,0,0,0,0,0,0,0,0,-12,
            0,0,0,0,0,0,0,0,-14,-51,
            0,0,-106,0,0,0,0,0,0,0,
            0,0,0,0,-39,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            -6,0,0,0,0,0,0,0,-15,0,
            -42,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,-88,0,0,0,
            0,-13,0,0,-16,0,-54,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,-17,0,-18,0,-1,-9,0,0,
            -19,0,-64,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,-83,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,-20,0,0,-32,0,-57,
            0,0,-21,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,-33,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,-41,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            -52,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,-55,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,-56,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,-58,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,-77,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            -84,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,-97,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,-110,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,-115,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,-46,0,-68,0,-74,0,
            -25,-22,-23,0,0,-26,-11,0,-96,0,
            0,0,0,0,0,0,0,-29,0,-27,
            0,-28,0,0,0,0,0,0,0,-3,
            0,0,0,0,0,0,0,0,0,0,
            -31,0,0,0,0,0,0,0,0,0,
            0,0,-44,0,0,0,0,0,0,0,
            0,0,0,-48,0,-111,0,-40,0,0,
            0,0,0,0,0,-98,0,-34,0,0,
            0,0,0,0,0,0,-102,0,-89,0,
            -24,0,0,0,0,0,0,0,-107,0,
            -45,0,0,0,0,0,0,0,0,-67,
            -37,-35,0,0,0,-72,0,-82,-99,0,
            0,0,-30,-36,-47,-49,0,0,-50,0,
            0,0,-53,-59,-60,-61,-62,0,-63,-65,
            0,-66,0,-69,-70,0,0,0,-71,-73,
            0,-75,0,-76,-78,-79,-80,-81,-85,-86,
            -87,-91,-92,-93,0,-94,0,-95,-100,0,
            -101,-103,-104,-105,-108,-109,-112,-113,-114,-116,
            0
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            39,39,39,39,40,40,40,40,40,40,
            41,47,48,48,49,49,26,42,50,50,
            27,51,51,52,52,28,43,44,29,29,
            30,45,53,54,55,56,56,31,31,32,
            33,46,24,24,24,24,24,1,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,3,5,6,57,57,
            58,58,34,7,59,59,35,35,8,9,
            2,36,36,10,10,11,25,25,23,23,
            12,60,60,37,37,37,13,14,15,15,
            16,16,16,16,16,16,17,18,19,20,
            21,21,21,21,22,22,38,38,49,612,
            54,48,639,49,50,51,52,53,58,59,
            60,61,62,63,3,591,30,1,255,3,
            303,87,87,637,3,502,89,114,3,506,
            3,520,70,258,100,101,102,103,104,261,
            113,139,499,100,101,102,103,104,261,2,
            5,633,15,30,675,636,263,3,4,5,
            6,7,8,9,295,60,612,54,48,642,
            49,50,51,52,53,58,59,60,61,62,
            63,3,591,501,35,37,38,92,87,6,
            640,60,612,54,48,642,49,50,51,52,
            53,58,59,60,61,62,63,3,616,599,
            27,503,7,42,87,106,645,60,612,54,
            48,642,49,50,51,52,53,58,59,60,
            61,62,63,125,19,16,109,76,3,522,
            87,22,655,78,612,54,48,659,49,50,
            51,52,53,58,59,60,61,62,63,31,
            612,54,48,642,49,50,51,52,53,58,
            59,60,61,62,63,102,137,71,3,115,
            3,79,89,31,612,54,48,624,49,50,
            51,52,53,58,59,60,61,62,63,31,
            612,54,48,629,49,50,51,52,53,58,
            59,60,61,62,63,31,612,54,48,644,
            49,50,51,52,53,58,59,60,61,62,
            63,31,612,54,48,654,49,50,51,52,
            53,58,59,60,61,62,63,31,612,54,
            48,656,49,50,51,52,53,58,59,60,
            61,62,63,31,612,54,48,657,49,50,
            51,52,53,58,59,60,61,62,63,31,
            612,54,48,658,49,50,51,52,53,58,
            59,60,61,62,63,31,612,54,48,662,
            49,50,51,52,53,58,59,60,61,62,
            63,31,612,54,48,664,49,50,51,52,
            53,58,59,60,61,62,63,31,612,54,
            48,669,49,50,51,52,53,58,59,60,
            61,62,63,31,612,54,48,677,49,50,
            51,52,53,58,59,60,61,62,63,31,
            612,54,48,680,49,50,51,52,53,58,
            59,60,61,62,63,104,114,3,33,3,
            16,94,11,257,591,501,127,89,114,3,
            25,499,100,101,102,103,104,261,89,114,
            258,14,110,116,100,101,102,103,104,261,
            89,114,29,503,116,100,101,102,103,104,
            261,89,114,650,652,623,1,100,101,102,
            103,104,261,89,114,568,625,626,100,101,
            102,103,104,261,89,114,3,678,260,26,
            100,101,102,103,104,261,89,114,5,633,
            117,100,101,102,103,104,261,89,114,7,
            42,63,671,100,101,102,103,104,261,34,
            675,3,616,672,100,101,102,103,104,261,
            268,84,259,509,635,91,276,23,280,7,
            42,635,610,4,179,119,261,80,18,186,
            36,37,38,262,263,265,162,266,170,133,
            267,668,143,509,196,269,75,24,80,272,
            277,649,275,77,203,278,215,222,154,165,
            279,283,172,140,229,660,291,20,141,236,
            634,292,293,295,298,58,210,243,300,302,
            250,683,683
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,0,0,0,0,0,0,7,8,7,
            0,7,9,7,14,0,16,2,18,19,
            14,0,16,2,18,19,26,27,28,0,
            0,1,29,0,34,35,7,7,8,39,
            7,41,38,13,44,15,36,17,0,1,
            20,21,22,23,25,7,8,0,25,0,
            1,13,0,15,31,17,7,8,20,21,
            22,23,13,11,15,0,17,0,1,20,
            21,22,23,0,7,8,0,1,0,32,
            13,8,15,0,17,7,8,20,21,22,
            23,0,14,0,16,0,18,19,33,0,
            7,8,0,1,26,27,28,14,0,16,
            11,18,19,30,0,24,0,9,10,26,
            27,28,0,1,2,3,4,5,6,0,
            0,9,0,1,2,3,4,5,6,9,
            24,9,12,0,1,2,3,4,5,6,
            0,0,30,10,0,1,2,3,4,5,
            6,0,1,2,3,4,5,6,0,1,
            2,3,4,5,6,0,1,2,3,4,
            5,6,31,0,30,0,1,2,3,4,
            5,6,0,1,2,3,4,5,6,0,
            1,0,0,42,0,1,2,3,4,5,
            6,0,1,2,3,4,5,6,0,1,
            2,3,4,5,6,0,1,2,3,4,
            5,6,0,1,2,3,4,5,6,0,
            1,2,3,4,5,6,0,0,0,0,
            0,0,0,7,0,0,0,0,0,11,
            10,0,10,9,0,0,0,0,0,0,
            12,24,0,9,25,24,11,8,12,12,
            0,0,0,11,0,29,31,0,8,0,
            9,0,0,0,0,0,12,40,0,10,
            13,0,0,0,43,37,0,0,0,0,
            0,29,0,0,0,0,0,0,0,0,
            0,0,0,32,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            683,45,683,683,94,46,683,730,507,730,
            683,730,564,730,169,42,235,114,253,209,
            728,105,727,298,726,729,232,142,160,683,
            683,611,790,683,144,150,730,730,185,201,
            730,148,351,118,682,578,335,767,68,611,
            766,738,739,740,676,730,185,683,676,86,
            611,118,81,578,717,767,730,185,766,738,
            739,740,118,211,578,2,767,69,611,766,
            738,739,740,74,730,185,683,518,683,463,
            118,185,578,683,767,730,507,766,738,739,
            740,683,796,12,794,44,793,795,530,683,
            730,507,683,541,232,142,160,796,683,794,
            495,793,795,134,43,319,683,564,789,232,
            142,160,88,399,300,367,383,781,782,82,
            683,279,88,399,300,367,383,781,782,619,
            553,279,705,683,399,300,367,383,781,782,
            683,683,431,761,683,399,300,367,383,781,
            782,683,399,300,367,383,781,782,41,399,
            300,367,383,781,782,10,399,300,367,383,
            781,782,750,683,431,32,399,300,367,383,
            781,782,95,399,300,367,383,781,782,683,
            566,683,683,447,97,399,300,367,383,781,
            782,96,399,300,367,383,781,782,72,399,
            300,367,383,781,782,93,399,300,367,383,
            781,782,39,399,300,367,383,781,782,40,
            399,300,367,383,781,782,28,683,81,683,
            683,683,683,730,76,683,683,683,683,237,
            791,17,756,618,13,21,683,683,683,683,
            765,601,683,146,497,415,589,185,694,663,
            683,683,683,666,683,748,749,683,576,683,
            587,683,683,683,683,683,674,661,683,679,
            173,683,683,683,227,714,683,683,683,683,
            683,673,683,683,683,683,683,683,683,683,
            683,683,683,479
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            28,127,132,140,140,140,140,140,140,201,
            132,201,136,140,140,140,140,25,44,63,
            209,76,127,78,172,63,63,93,132,159,
            132,140,209,105,104,121,101,208,1,230,
            209,13,140,132,140,65,60,132,191,121,
            105,209,63,13,209,209,140,209,191,60,
            162,162,164,208,11,174,99,140,224,23,
            143,142,23,140,182,105,209,23,105,105,
            185,157,209,209,166,193,93,140,136,132,
            195,105,203,157,182,140,209,132,136,105,
            61,132,11,23,218,96,95,220,201,209,
            140,222,191,220,209,222
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            8,13,1,7,20,17,15,21,22,23,
            29,0,8,13,1,7,20,17,15,21,
            22,23,12,0,7,2,0,34,35,14,
            16,18,26,27,39,28,19,41,7,8,
            44,33,0,34,35,14,16,18,26,27,
            39,28,19,41,7,8,29,44,2,10,
            9,0,24,0,12,27,8,28,26,18,
            16,19,14,7,0,36,0,34,35,14,
            16,18,26,27,39,28,19,41,7,8,
            30,44,11,0,31,25,7,0,40,0,
            10,8,0,11,15,25,17,20,21,22,
            23,42,9,29,30,10,12,13,31,38,
            1,2,4,3,5,6,44,35,41,39,
            34,27,8,28,26,18,16,14,19,7,
            0,11,34,35,14,16,18,26,27,39,
            28,19,41,7,44,43,8,0,29,9,
            0,31,0,31,9,1,2,4,3,5,
            6,30,0,29,31,1,2,4,3,5,
            6,12,9,0,1,2,4,3,5,6,
            10,0,37,0,2,42,4,3,5,6,
            1,0,2,4,3,5,6,31,8,1,
            7,20,17,15,21,22,23,13,0,32,
            0,7,31,1,2,4,3,5,6,25,
            0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            20,4,9,6,6,17,6,6,6,7,
            9,7,50,7,7,7,7,7,7,7,
            6,28,17,38,7,7,7,7,9,7,
            9,6,6,32,38,7,45,1,6,30,
            6,6,6,9,6,25,7,9,7,7,
            11,6,7,6,6,6,6,6,7,7,
            7,7,7,22,7,7,43,6,7,7,
            7,47,7,6,7,7,6,7,7,7,
            7,45,6,6,7,7,7,6,35,8,
            7,7,7,7,7,6,6,9,49,7,
            7,9,7,7,7,14,40,7,7,6,
            6,7,7,7,6,7
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static byte nasr[] = {0,
            58,1,0,47,40,1,0,26,22,0,
            1,37,0,1,56,0,1,30,0,39,
            0,1,34,0,49,22,0,53,0,54,
            0,1,60,0,24,52,0,36,0,1,
            31,0,55,0,3,0,51,0,28,24,
            0
        };
    };
    public final static byte nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            13,3,31,41,10,11,42,5,2,14,
            15,16,17,23,24,26,29,30,38,39,
            43,44,45,8,28,33,34,37,6,7,
            18,4,20,21,22,25,27,32,35,36,
            40,1,12,47,9,46,48,49,50
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            55,65,71,54,0,0,0,0,0,0,
            0,0,0,0,0,51,0,0,0,0,
            0,76,75,62,0,57,59,61,63,64,
            70,0,0,73,74,0,0,77,0,52,
            0,0,0,0,0,0,53,0,56,58,
            0,60,66,67,68,69,0,72,0,0,
            0
        };
    };
    public final static byte nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
    public final int nonterminalIndex(int index) { return nonterminalIndex[index]; }

    public interface ScopePrefix {
        public final static byte scopePrefix[] = {
            1,12,23,32,16,7,29,36,44,49,
            52,39
        };
    };
    public final static byte scopePrefix[] = ScopePrefix.scopePrefix;
    public final int scopePrefix(int index) { return scopePrefix[index]; }

    public interface ScopeSuffix {
        public final static byte scopeSuffix[] = {
            5,5,27,5,20,10,27,5,47,47,
            10,41
        };
    };
    public final static byte scopeSuffix[] = ScopeSuffix.scopeSuffix;
    public final int scopeSuffix(int index) { return scopeSuffix[index]; }

    public interface ScopeLhs {
        public final static byte scopeLhs[] = {
            19,17,11,8,37,18,36,7,6,5,
            3,34
        };
    };
    public final static byte scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            10,10,12,10,42,29,12,10,31,31,
            29,30
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            28,28,9,9,1,28,4,9,9,9,
            7,25
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static byte scopeRhs[] = {0,
            65,1,28,0,10,0,87,8,0,29,
            0,87,1,27,0,53,24,50,0,42,
            53,0,74,11,50,0,12,0,74,11,
            0,53,1,53,0,108,1,0,53,0,
            30,53,0,106,13,0,31,0,74,13,
            0,74,8,0
        };
    };
    public final static byte scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            170,578,0,612,591,0,618,611,479,463,
            447,431,279,415,399,383,367,237,351,211,
            335,185,319,303,263,118,0,587,576,146,
            564,495,553,541,518,507,530,137,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static byte inSymb[] = {0,
            0,88,33,73,34,39,41,35,65,27,
            8,28,26,14,19,16,18,50,71,96,
            50,50,79,50,51,50,50,50,1,87,
            1,2,24,15,50,53,1,13,8,102,
            36,11,30,24,24,11,87,9,65,53,
            109,38,50,11,3,4,2,1,108,52,
            106,74,53,107,74,53,103,25,53,74,
            99,50,97,65,98,53,24,74,53,53,
            53,9,9,30,53,104,40,43,11,9,
            53,12,53,11,101,73,42,8,9,53,
            65,9,65,29,12,13,105,50,25,32,
            1,53,50,10,32,53
        };
    };
    public final static byte inSymb[] = InSymb.inSymb;
    public final int inSymb(int index) { return inSymb[index]; }

    public interface Name {
        public final static String name[] = {
            "",
            ":",
            ",",
            ".",
            ":=",
            "<",
            ">",
            "=>",
            "=",
            "!=",
            "+",
            "*",
            "|",
            "[",
            "]",
            "(",
            ")",
            "{",
            "}",
            "$empty",
            "typeSpecifierMarker",
            "analysis",
            "atype",
            "boolean",
            "case",
            "constraints",
            "double",
            "end",
            "estimates",
            "false",
            "int",
            "intersect",
            "else",
            "ref",
            "relation",
            "rules",
            "satisfy",
            "set",
            "string",
            "true",
            "type",
            "union",
            "IDENTIFIER",
            "IntegerLiteral",
            "DoubleLiteral",
            "StringLiteral",
            "return",
            "EOF_TOKEN",
            "SINGLE_LINE_COMMENT",
            "SEMICOLON",
            "ERROR_TOKEN",
            "typeSpecifier",
            "topLevelDeclaration",
            "functionHeader",
            "value",
            "identifier",
            "parameterList",
            "declaration",
            "ctorList",
            "ctorDeclaration",
            "formalArgList",
            "formalArg",
            "typeName",
            "rulesList",
            "rule",
            "algebraicTypeValue",
            "constraintsDef",
            "estimatesDef",
            "satisfyDef",
            "localOrUpdateList",
            "localOrUpdate",
            "tupleLiteral",
            "mappingList",
            "mapping",
            "tupleList",
            "valueList",
            "qualifiedIdentifier",
            "typeSpecifierList"
        };
    };
    public final static String name[] = Name.name;
    public final String name(int index) { return name[index]; }

    public final static int
           ERROR_SYMBOL      = 49,
           SCOPE_UBOUND      = 11,
           SCOPE_SIZE        = 12,
           MAX_NAME_LENGTH   = 19;

    public final int getErrorSymbol() { return ERROR_SYMBOL; }
    public final int getScopeUbound() { return SCOPE_UBOUND; }
    public final int getScopeSize() { return SCOPE_SIZE; }
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int
           NUM_STATES        = 116,
           NT_OFFSET         = 49,
           LA_STATE_OFFSET   = 800,
           MAX_LA            = 1,
           NUM_RULES         = 117,
           NUM_NONTERMINALS  = 61,
           NUM_SYMBOLS       = 110,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 257,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 44,
           EOLT_SYMBOL       = 44,
           ACCEPT_ACTION     = 682,
           ERROR_ACTION      = 683;

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

    public final int originalState(int state) {
        return -baseCheck[state];
    }
    public final int asi(int state) {
        return asb[originalState(state)];
    }
    public final int nasi(int state) {
        return nasb[originalState(state)];
    }
    public final int inSymbol(int state) {
        return inSymb[originalState(state)];
    }

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
