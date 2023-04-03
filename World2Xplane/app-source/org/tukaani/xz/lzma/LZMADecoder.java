package org.tukaani.xz.lzma;

import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.lz.LZDecoder;
import org.tukaani.xz.rangecoder.RangeDecoder;

public final class LZMADecoder extends LZMACoder {
  private final LZDecoder lz;
  
  private final RangeDecoder rc;
  
  private final LiteralDecoder literalDecoder;
  
  private final LengthDecoder matchLenDecoder = new LengthDecoder();
  
  private final LengthDecoder repLenDecoder = new LengthDecoder();
  
  public LZMADecoder(LZDecoder paramLZDecoder, RangeDecoder paramRangeDecoder, int paramInt1, int paramInt2, int paramInt3) {
    super(paramInt3);
    this.lz = paramLZDecoder;
    this.rc = paramRangeDecoder;
    this.literalDecoder = new LiteralDecoder(paramInt1, paramInt2);
    reset();
  }
  
  public void reset() {
    super.reset();
    this.literalDecoder.reset();
    this.matchLenDecoder.reset();
    this.repLenDecoder.reset();
  }
  
  public void decode() throws IOException {
    this.lz.repeatPending();
    while (this.lz.hasSpace()) {
      int i = this.lz.getPos() & this.posMask;
      if (this.rc.decodeBit(this.isMatch[this.state.get()], i) == 0) {
        this.literalDecoder.decode();
        continue;
      } 
      int j = (this.rc.decodeBit(this.isRep, this.state.get()) == 0) ? decodeMatch(i) : decodeRepMatch(i);
      this.lz.repeat(this.reps[0], j);
    } 
    this.rc.normalize();
    if (!this.rc.isInBufferOK())
      throw new CorruptedInputException(); 
  }
  
  private int decodeMatch(int paramInt) throws IOException {
    this.state.updateMatch();
    this.reps[3] = this.reps[2];
    this.reps[2] = this.reps[1];
    this.reps[1] = this.reps[0];
    int i = this.matchLenDecoder.decode(paramInt);
    int j = this.rc.decodeBitTree(this.distSlots[getDistState(i)]);
    if (j < 4) {
      this.reps[0] = j;
    } else {
      int k = (j >> 1) - 1;
      this.reps[0] = (0x2 | j & 0x1) << k;
      if (j < 14) {
        this.reps[0] = this.reps[0] | this.rc.decodeReverseBitTree(this.distSpecial[j - 4]);
      } else {
        this.reps[0] = this.reps[0] | this.rc.decodeDirectBits(k - 4) << 4;
        this.reps[0] = this.reps[0] | this.rc.decodeReverseBitTree(this.distAlign);
      } 
    } 
    return i;
  }
  
  private int decodeRepMatch(int paramInt) throws IOException {
    if (this.rc.decodeBit(this.isRep0, this.state.get()) == 0) {
      if (this.rc.decodeBit(this.isRep0Long[this.state.get()], paramInt) == 0) {
        this.state.updateShortRep();
        return 1;
      } 
    } else {
      int i;
      if (this.rc.decodeBit(this.isRep1, this.state.get()) == 0) {
        i = this.reps[1];
      } else {
        if (this.rc.decodeBit(this.isRep2, this.state.get()) == 0) {
          i = this.reps[2];
        } else {
          i = this.reps[3];
          this.reps[3] = this.reps[2];
        } 
        this.reps[2] = this.reps[1];
      } 
      this.reps[1] = this.reps[0];
      this.reps[0] = i;
    } 
    this.state.updateLongRep();
    return this.repLenDecoder.decode(paramInt);
  }
  
  private class LengthDecoder extends LZMACoder.LengthCoder {
    private final LZMADecoder this$0;
    
    private LengthDecoder() {}
    
    int decode(int param1Int) throws IOException {
      return (LZMADecoder.this.rc.decodeBit(this.choice, 0) == 0) ? (LZMADecoder.this.rc.decodeBitTree(this.low[param1Int]) + 2) : ((LZMADecoder.this.rc.decodeBit(this.choice, 1) == 0) ? (LZMADecoder.this.rc.decodeBitTree(this.mid[param1Int]) + 2 + 8) : (LZMADecoder.this.rc.decodeBitTree(this.high) + 2 + 8 + 8));
    }
  }
  
  private class LiteralDecoder extends LZMACoder.LiteralCoder {
    LiteralSubdecoder[] subdecoders;
    
    private final LZMADecoder this$0;
    
    LiteralDecoder(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.subdecoders = new LiteralSubdecoder[1 << param1Int1 + param1Int2];
      for (byte b = 0; b < this.subdecoders.length; b++)
        this.subdecoders[b] = new LiteralSubdecoder(); 
    }
    
    void reset() {
      for (byte b = 0; b < this.subdecoders.length; b++)
        this.subdecoders[b].reset(); 
    }
    
    void decode() throws IOException {
      int i = getSubcoderIndex(LZMADecoder.this.lz.getByte(0), LZMADecoder.this.lz.getPos());
      this.subdecoders[i].decode();
    }
    
    private class LiteralSubdecoder extends LZMACoder.LiteralCoder.LiteralSubcoder {
      private final LZMADecoder.LiteralDecoder this$1;
      
      private LiteralSubdecoder() {}
      
      void decode() throws IOException {
        int i = 1;
        if ((LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).state.isLiteral()) {
          do {
            i = i << 1 | (LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).rc.decodeBit(this.probs, i);
          } while (i < 256);
        } else {
          int j = (LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).lz.getByte((LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).reps[0]);
          int k = 256;
          do {
            j <<= 1;
            int m = j & k;
            int n = (LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).rc.decodeBit(this.probs, k + m + i);
            i = i << 1 | n;
            k &= 0 - n ^ m ^ 0xFFFFFFFF;
          } while (i < 256);
        } 
        (LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).lz.putByte((byte)i);
        (LZMADecoder.LiteralDecoder.access$300(LZMADecoder.LiteralDecoder.this)).state.updateLiteral();
      }
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lzma\LZMADecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */