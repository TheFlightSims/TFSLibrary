package org.tukaani.xz.lzma;

import org.tukaani.xz.rangecoder.RangeCoder;

abstract class LZMACoder {
  static final int POS_STATES_MAX = 16;
  
  static final int MATCH_LEN_MIN = 2;
  
  static final int MATCH_LEN_MAX = 273;
  
  static final int DIST_STATES = 4;
  
  static final int DIST_SLOTS = 64;
  
  static final int DIST_MODEL_START = 4;
  
  static final int DIST_MODEL_END = 14;
  
  static final int FULL_DISTANCES = 128;
  
  static final int ALIGN_BITS = 4;
  
  static final int ALIGN_SIZE = 16;
  
  static final int ALIGN_MASK = 15;
  
  static final int REPS = 4;
  
  final int posMask;
  
  final int[] reps = new int[4];
  
  final State state = new State();
  
  final short[][] isMatch = new short[12][16];
  
  final short[] isRep = new short[12];
  
  final short[] isRep0 = new short[12];
  
  final short[] isRep1 = new short[12];
  
  final short[] isRep2 = new short[12];
  
  final short[][] isRep0Long = new short[12][16];
  
  final short[][] distSlots = new short[4][64];
  
  final short[][] distSpecial = new short[][] { new short[2], new short[2], new short[4], new short[4], new short[8], new short[8], new short[16], new short[16], new short[32], new short[32] };
  
  final short[] distAlign = new short[16];
  
  static final int getDistState(int paramInt) {
    return (paramInt < 6) ? (paramInt - 2) : 3;
  }
  
  LZMACoder(int paramInt) {
    this.posMask = (1 << paramInt) - 1;
  }
  
  void reset() {
    this.reps[0] = 0;
    this.reps[1] = 0;
    this.reps[2] = 0;
    this.reps[3] = 0;
    this.state.reset();
    byte b;
    for (b = 0; b < this.isMatch.length; b++)
      RangeCoder.initProbs(this.isMatch[b]); 
    RangeCoder.initProbs(this.isRep);
    RangeCoder.initProbs(this.isRep0);
    RangeCoder.initProbs(this.isRep1);
    RangeCoder.initProbs(this.isRep2);
    for (b = 0; b < this.isRep0Long.length; b++)
      RangeCoder.initProbs(this.isRep0Long[b]); 
    for (b = 0; b < this.distSlots.length; b++)
      RangeCoder.initProbs(this.distSlots[b]); 
    for (b = 0; b < this.distSpecial.length; b++)
      RangeCoder.initProbs(this.distSpecial[b]); 
    RangeCoder.initProbs(this.distAlign);
  }
  
  abstract class LengthCoder {
    static final int LOW_SYMBOLS = 8;
    
    static final int MID_SYMBOLS = 8;
    
    static final int HIGH_SYMBOLS = 256;
    
    final short[] choice = new short[2];
    
    final short[][] low = new short[16][8];
    
    final short[][] mid = new short[16][8];
    
    final short[] high = new short[256];
    
    private final LZMACoder this$0;
    
    void reset() {
      RangeCoder.initProbs(this.choice);
      byte b;
      for (b = 0; b < this.low.length; b++)
        RangeCoder.initProbs(this.low[b]); 
      for (b = 0; b < this.low.length; b++)
        RangeCoder.initProbs(this.mid[b]); 
      RangeCoder.initProbs(this.high);
    }
  }
  
  abstract class LiteralCoder {
    private final int lc;
    
    private final int literalPosMask;
    
    private final LZMACoder this$0;
    
    LiteralCoder(int param1Int1, int param1Int2) {
      this.lc = param1Int1;
      this.literalPosMask = (1 << param1Int2) - 1;
    }
    
    final int getSubcoderIndex(int param1Int1, int param1Int2) {
      int i = param1Int1 >> 8 - this.lc;
      int j = (param1Int2 & this.literalPosMask) << this.lc;
      return i + j;
    }
    
    abstract class LiteralSubcoder {
      final short[] probs = new short[768];
      
      private final LZMACoder.LiteralCoder this$1;
      
      void reset() {
        RangeCoder.initProbs(this.probs);
      }
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\LZMACoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */