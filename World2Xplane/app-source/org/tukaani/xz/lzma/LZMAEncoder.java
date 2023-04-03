package org.tukaani.xz.lzma;

import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

public abstract class LZMAEncoder extends LZMACoder {
  public static final int MODE_FAST = 1;
  
  public static final int MODE_NORMAL = 2;
  
  private static final int LZMA2_UNCOMPRESSED_LIMIT = 2096879;
  
  private static final int LZMA2_COMPRESSED_LIMIT = 65510;
  
  private static final int DIST_PRICE_UPDATE_INTERVAL = 128;
  
  private static final int ALIGN_PRICE_UPDATE_INTERVAL = 16;
  
  private final RangeEncoder rc;
  
  final LZEncoder lz;
  
  final LiteralEncoder literalEncoder;
  
  final LengthEncoder matchLenEncoder;
  
  final LengthEncoder repLenEncoder;
  
  final int niceLen;
  
  private int distPriceCount = 0;
  
  private int alignPriceCount = 0;
  
  private final int distSlotPricesSize;
  
  private final int[][] distSlotPrices;
  
  private final int[][] fullDistPrices = new int[4][128];
  
  private final int[] alignPrices = new int[16];
  
  int back = 0;
  
  int readAhead = -1;
  
  private int uncompressedSize = 0;
  
  static final boolean $assertionsDisabled;
  
  public static int getMemoryUsage(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = 80;
    switch (paramInt1) {
      case 1:
        i += LZMAEncoderFast.getMemoryUsage(paramInt2, paramInt3, paramInt4);
        return i;
      case 2:
        i += LZMAEncoderNormal.getMemoryUsage(paramInt2, paramInt3, paramInt4);
        return i;
    } 
    throw new IllegalArgumentException();
  }
  
  public static LZMAEncoder getInstance(RangeEncoder paramRangeEncoder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9) {
    switch (paramInt4) {
      case 1:
        return new LZMAEncoderFast(paramRangeEncoder, paramInt1, paramInt2, paramInt3, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9);
      case 2:
        return new LZMAEncoderNormal(paramRangeEncoder, paramInt1, paramInt2, paramInt3, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9);
    } 
    throw new IllegalArgumentException();
  }
  
  public static int getDistSlot(int paramInt) {
    if (paramInt <= 4)
      return paramInt; 
    int i = paramInt;
    byte b = 31;
    if ((i & 0xFFFF0000) == 0) {
      i <<= 16;
      b = 15;
    } 
    if ((i & 0xFF000000) == 0) {
      i <<= 8;
      b -= 8;
    } 
    if ((i & 0xF0000000) == 0) {
      i <<= 4;
      b -= 4;
    } 
    if ((i & 0xC0000000) == 0) {
      i <<= 2;
      b -= 2;
    } 
    if ((i & Integer.MIN_VALUE) == 0)
      b--; 
    return (b << 1) + (paramInt >>> b - 1 & 0x1);
  }
  
  abstract int getNextSymbol();
  
  LZMAEncoder(RangeEncoder paramRangeEncoder, LZEncoder paramLZEncoder, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    super(paramInt3);
    this.rc = paramRangeEncoder;
    this.lz = paramLZEncoder;
    this.niceLen = paramInt5;
    this.literalEncoder = new LiteralEncoder(paramInt1, paramInt2);
    this.matchLenEncoder = new LengthEncoder(paramInt3, paramInt5);
    this.repLenEncoder = new LengthEncoder(paramInt3, paramInt5);
    this.distSlotPricesSize = getDistSlot(paramInt4 - 1) + 1;
    this.distSlotPrices = new int[4][this.distSlotPricesSize];
    reset();
  }
  
  public LZEncoder getLZEncoder() {
    return this.lz;
  }
  
  public void reset() {
    super.reset();
    this.literalEncoder.reset();
    this.matchLenEncoder.reset();
    this.repLenEncoder.reset();
    this.distPriceCount = 0;
    this.alignPriceCount = 0;
    this.uncompressedSize += this.readAhead + 1;
    this.readAhead = -1;
  }
  
  public int getUncompressedSize() {
    return this.uncompressedSize;
  }
  
  public void resetUncompressedSize() {
    this.uncompressedSize = 0;
  }
  
  public boolean encodeForLZMA2() {
    if (!this.lz.isStarted() && !encodeInit())
      return false; 
    while (this.uncompressedSize <= 2096879 && this.rc.getPendingSize() <= 65510) {
      if (!encodeSymbol())
        return false; 
    } 
    return true;
  }
  
  private boolean encodeInit() {
    assert this.readAhead == -1;
    if (!this.lz.hasEnoughData(0))
      return false; 
    skip(1);
    this.rc.encodeBit(this.isMatch[this.state.get()], 0, 0);
    this.literalEncoder.encodeInit();
    this.readAhead--;
    assert this.readAhead == -1;
    this.uncompressedSize++;
    assert this.uncompressedSize == 1;
    return true;
  }
  
  private boolean encodeSymbol() {
    if (!this.lz.hasEnoughData(this.readAhead + 1))
      return false; 
    int i = getNextSymbol();
    assert this.readAhead >= 0;
    int j = this.lz.getPos() - this.readAhead & this.posMask;
    if (this.back == -1) {
      assert i == 1;
      this.rc.encodeBit(this.isMatch[this.state.get()], j, 0);
      this.literalEncoder.encode();
    } else {
      this.rc.encodeBit(this.isMatch[this.state.get()], j, 1);
      if (this.back < 4) {
        assert this.lz.getMatchLen(-this.readAhead, this.reps[this.back], i) == i;
        this.rc.encodeBit(this.isRep, this.state.get(), 1);
        encodeRepMatch(this.back, i, j);
      } else {
        assert this.lz.getMatchLen(-this.readAhead, this.back - 4, i) == i;
        this.rc.encodeBit(this.isRep, this.state.get(), 0);
        encodeMatch(this.back - 4, i, j);
      } 
    } 
    this.readAhead -= i;
    this.uncompressedSize += i;
    return true;
  }
  
  private void encodeMatch(int paramInt1, int paramInt2, int paramInt3) {
    this.state.updateMatch();
    this.matchLenEncoder.encode(paramInt2, paramInt3);
    int i = getDistSlot(paramInt1);
    this.rc.encodeBitTree(this.distSlots[getDistState(paramInt2)], i);
    if (i >= 4) {
      int j = (i >>> 1) - 1;
      int k = (0x2 | i & 0x1) << j;
      int m = paramInt1 - k;
      if (i < 14) {
        this.rc.encodeReverseBitTree(this.distSpecial[i - 4], m);
      } else {
        this.rc.encodeDirectBits(m >>> 4, j - 4);
        this.rc.encodeReverseBitTree(this.distAlign, m & 0xF);
        this.alignPriceCount--;
      } 
    } 
    this.reps[3] = this.reps[2];
    this.reps[2] = this.reps[1];
    this.reps[1] = this.reps[0];
    this.reps[0] = paramInt1;
    this.distPriceCount--;
  }
  
  private void encodeRepMatch(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 == 0) {
      this.rc.encodeBit(this.isRep0, this.state.get(), 0);
      this.rc.encodeBit(this.isRep0Long[this.state.get()], paramInt3, (paramInt2 == 1) ? 0 : 1);
    } else {
      int i = this.reps[paramInt1];
      this.rc.encodeBit(this.isRep0, this.state.get(), 1);
      if (paramInt1 == 1) {
        this.rc.encodeBit(this.isRep1, this.state.get(), 0);
      } else {
        this.rc.encodeBit(this.isRep1, this.state.get(), 1);
        this.rc.encodeBit(this.isRep2, this.state.get(), paramInt1 - 2);
        if (paramInt1 == 3)
          this.reps[3] = this.reps[2]; 
        this.reps[2] = this.reps[1];
      } 
      this.reps[1] = this.reps[0];
      this.reps[0] = i;
    } 
    if (paramInt2 == 1) {
      this.state.updateShortRep();
    } else {
      this.repLenEncoder.encode(paramInt2, paramInt3);
      this.state.updateLongRep();
    } 
  }
  
  Matches getMatches() {
    this.readAhead++;
    Matches matches = this.lz.getMatches();
    assert this.lz.verifyMatches(matches);
    return matches;
  }
  
  void skip(int paramInt) {
    this.readAhead += paramInt;
    this.lz.skip(paramInt);
  }
  
  int getAnyMatchPrice(State paramState, int paramInt) {
    return RangeEncoder.getBitPrice(this.isMatch[paramState.get()][paramInt], 1);
  }
  
  int getNormalMatchPrice(int paramInt, State paramState) {
    return paramInt + RangeEncoder.getBitPrice(this.isRep[paramState.get()], 0);
  }
  
  int getAnyRepPrice(int paramInt, State paramState) {
    return paramInt + RangeEncoder.getBitPrice(this.isRep[paramState.get()], 1);
  }
  
  int getShortRepPrice(int paramInt1, State paramState, int paramInt2) {
    return paramInt1 + RangeEncoder.getBitPrice(this.isRep0[paramState.get()], 0) + RangeEncoder.getBitPrice(this.isRep0Long[paramState.get()][paramInt2], 0);
  }
  
  int getLongRepPrice(int paramInt1, int paramInt2, State paramState, int paramInt3) {
    int i = paramInt1;
    if (paramInt2 == 0) {
      i += RangeEncoder.getBitPrice(this.isRep0[paramState.get()], 0) + RangeEncoder.getBitPrice(this.isRep0Long[paramState.get()][paramInt3], 1);
    } else {
      i += RangeEncoder.getBitPrice(this.isRep0[paramState.get()], 1);
      if (paramInt2 == 1) {
        i += RangeEncoder.getBitPrice(this.isRep1[paramState.get()], 0);
      } else {
        i += RangeEncoder.getBitPrice(this.isRep1[paramState.get()], 1) + RangeEncoder.getBitPrice(this.isRep2[paramState.get()], paramInt2 - 2);
      } 
    } 
    return i;
  }
  
  int getLongRepAndLenPrice(int paramInt1, int paramInt2, State paramState, int paramInt3) {
    int i = getAnyMatchPrice(paramState, paramInt3);
    int j = getAnyRepPrice(i, paramState);
    int k = getLongRepPrice(j, paramInt1, paramState, paramInt3);
    return k + this.repLenEncoder.getPrice(paramInt2, paramInt3);
  }
  
  int getMatchAndLenPrice(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt1 + this.matchLenEncoder.getPrice(paramInt3, paramInt4);
    int j = getDistState(paramInt3);
    if (paramInt2 < 128) {
      i += this.fullDistPrices[j][paramInt2];
    } else {
      int k = getDistSlot(paramInt2);
      i += this.distSlotPrices[j][k] + this.alignPrices[paramInt2 & 0xF];
    } 
    return i;
  }
  
  private void updateDistPrices() {
    this.distPriceCount = 128;
    byte b1;
    for (b1 = 0; b1 < 4; b1++) {
      byte b;
      for (b = 0; b < this.distSlotPricesSize; b++)
        this.distSlotPrices[b1][b] = RangeEncoder.getBitTreePrice(this.distSlots[b1], b); 
      for (b = 14; b < this.distSlotPricesSize; b++) {
        int i = (b >>> 1) - 1 - 4;
        this.distSlotPrices[b1][b] = this.distSlotPrices[b1][b] + RangeEncoder.getDirectBitsPrice(i);
      } 
      for (b = 0; b < 4; b++)
        this.fullDistPrices[b1][b] = this.distSlotPrices[b1][b]; 
    } 
    b1 = 4;
    for (byte b2 = 4; b2 < 14; b2++) {
      int i = (b2 >>> 1) - 1;
      int j = (0x2 | b2 & 0x1) << i;
      int k = (this.distSpecial[b2 - 4]).length;
      for (byte b = 0; b < k; b++) {
        int m = b1 - j;
        int n = RangeEncoder.getReverseBitTreePrice(this.distSpecial[b2 - 4], m);
        for (byte b3 = 0; b3 < 4; b3++)
          this.fullDistPrices[b3][b1] = this.distSlotPrices[b3][b2] + n; 
        b1++;
      } 
    } 
    assert b1 == '';
  }
  
  private void updateAlignPrices() {
    this.alignPriceCount = 16;
    for (byte b = 0; b < 16; b++)
      this.alignPrices[b] = RangeEncoder.getReverseBitTreePrice(this.distAlign, b); 
  }
  
  void updatePrices() {
    if (this.distPriceCount <= 0)
      updateDistPrices(); 
    if (this.alignPriceCount <= 0)
      updateAlignPrices(); 
    this.matchLenEncoder.updatePrices();
    this.repLenEncoder.updatePrices();
  }
  
  class LengthEncoder extends LZMACoder.LengthCoder {
    private static final int PRICE_UPDATE_INTERVAL = 32;
    
    private final int[] counters;
    
    private final int[][] prices;
    
    private final LZMAEncoder this$0;
    
    LengthEncoder(int param1Int1, int param1Int2) {
      int i = 1 << param1Int1;
      this.counters = new int[i];
      int j = Math.max(param1Int2 - 2 + 1, 16);
      this.prices = new int[i][j];
    }
    
    void reset() {
      super.reset();
      for (byte b = 0; b < this.counters.length; b++)
        this.counters[b] = 0; 
    }
    
    void encode(int param1Int1, int param1Int2) {
      param1Int1 -= 2;
      if (param1Int1 < 8) {
        LZMAEncoder.this.rc.encodeBit(this.choice, 0, 0);
        LZMAEncoder.this.rc.encodeBitTree(this.low[param1Int2], param1Int1);
      } else {
        LZMAEncoder.this.rc.encodeBit(this.choice, 0, 1);
        param1Int1 -= 8;
        if (param1Int1 < 8) {
          LZMAEncoder.this.rc.encodeBit(this.choice, 1, 0);
          LZMAEncoder.this.rc.encodeBitTree(this.mid[param1Int2], param1Int1);
        } else {
          LZMAEncoder.this.rc.encodeBit(this.choice, 1, 1);
          LZMAEncoder.this.rc.encodeBitTree(this.high, param1Int1 - 8);
        } 
      } 
      this.counters[param1Int2] = this.counters[param1Int2] - 1;
    }
    
    int getPrice(int param1Int1, int param1Int2) {
      return this.prices[param1Int2][param1Int1 - 2];
    }
    
    void updatePrices() {
      for (byte b = 0; b < this.counters.length; b++) {
        if (this.counters[b] <= 0) {
          this.counters[b] = 32;
          updatePrices(b);
        } 
      } 
    }
    
    private void updatePrices(int param1Int) {
      int i = RangeEncoder.getBitPrice(this.choice[0], 0);
      byte b;
      for (b = 0; b < 8; b++)
        this.prices[param1Int][b] = i + RangeEncoder.getBitTreePrice(this.low[param1Int], b); 
      i = RangeEncoder.getBitPrice(this.choice[0], 1);
      int j = RangeEncoder.getBitPrice(this.choice[1], 0);
      while (b < 16) {
        this.prices[param1Int][b] = i + j + RangeEncoder.getBitTreePrice(this.mid[param1Int], b - 8);
        b++;
      } 
      j = RangeEncoder.getBitPrice(this.choice[1], 1);
      while (b < (this.prices[param1Int]).length) {
        this.prices[param1Int][b] = i + j + RangeEncoder.getBitTreePrice(this.high, b - 8 - 8);
        b++;
      } 
    }
  }
  
  class LiteralEncoder extends LZMACoder.LiteralCoder {
    LiteralSubencoder[] subencoders;
    
    static final boolean $assertionsDisabled;
    
    private final LZMAEncoder this$0;
    
    LiteralEncoder(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.subencoders = new LiteralSubencoder[1 << param1Int1 + param1Int2];
      for (byte b = 0; b < this.subencoders.length; b++)
        this.subencoders[b] = new LiteralSubencoder(); 
    }
    
    void reset() {
      for (byte b = 0; b < this.subencoders.length; b++)
        this.subencoders[b].reset(); 
    }
    
    void encodeInit() {
      assert LZMAEncoder.this.readAhead >= 0;
      this.subencoders[0].encode();
    }
    
    void encode() {
      assert LZMAEncoder.this.readAhead >= 0;
      int i = getSubcoderIndex(LZMAEncoder.this.lz.getByte(1 + LZMAEncoder.this.readAhead), LZMAEncoder.this.lz.getPos() - LZMAEncoder.this.readAhead);
      this.subencoders[i].encode();
    }
    
    int getPrice(int param1Int1, int param1Int2, int param1Int3, int param1Int4, State param1State) {
      int i = RangeEncoder.getBitPrice(LZMAEncoder.this.isMatch[param1State.get()][param1Int4 & LZMAEncoder.this.posMask], 0);
      int j = getSubcoderIndex(param1Int3, param1Int4);
      i += param1State.isLiteral() ? this.subencoders[j].getNormalPrice(param1Int1) : this.subencoders[j].getMatchedPrice(param1Int1, param1Int2);
      return i;
    }
    
    private class LiteralSubencoder extends LZMACoder.LiteralCoder.LiteralSubcoder {
      private final LZMAEncoder.LiteralEncoder this$1;
      
      private LiteralSubencoder() {}
      
      void encode() {
        int i = (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).lz.getByte((LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).readAhead) | 0x100;
        if ((LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).state.isLiteral()) {
          do {
            int j = i >>> 8;
            int k = i >>> 7 & 0x1;
            (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).rc.encodeBit(this.probs, j, k);
            i <<= 1;
          } while (i < 65536);
        } else {
          int j = (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).lz.getByte((LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).reps[0] + 1 + (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).readAhead);
          int k = 256;
          do {
            j <<= 1;
            int n = j & k;
            int m = k + n + (i >>> 8);
            int i1 = i >>> 7 & 0x1;
            (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).rc.encodeBit(this.probs, m, i1);
            i <<= 1;
            k &= j ^ i ^ 0xFFFFFFFF;
          } while (i < 65536);
        } 
        (LZMAEncoder.LiteralEncoder.access$100(LZMAEncoder.LiteralEncoder.this)).state.updateLiteral();
      }
      
      int getNormalPrice(int param2Int) {
        int i = 0;
        param2Int |= 0x100;
        while (true) {
          int j = param2Int >>> 8;
          int k = param2Int >>> 7 & 0x1;
          i += RangeEncoder.getBitPrice(this.probs[j], k);
          param2Int <<= 1;
          if (param2Int >= 65536)
            return i; 
        } 
      }
      
      int getMatchedPrice(int param2Int1, int param2Int2) {
        int i = 0;
        int j = 256;
        param2Int1 |= 0x100;
        while (true) {
          param2Int2 <<= 1;
          int m = param2Int2 & j;
          int k = j + m + (param2Int1 >>> 8);
          int n = param2Int1 >>> 7 & 0x1;
          i += RangeEncoder.getBitPrice(this.probs[k], n);
          param2Int1 <<= 1;
          j &= param2Int2 ^ param2Int1 ^ 0xFFFFFFFF;
          if (param2Int1 >= 65536)
            return i; 
        } 
      }
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\LZMAEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */