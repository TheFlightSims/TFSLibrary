package org.tukaani.xz.rangecoder;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.CorruptedInputException;

public final class RangeDecoder extends RangeCoder {
  private static final int INIT_SIZE = 5;
  
  private final byte[] buf;
  
  private int pos = 0;
  
  private int end = 0;
  
  private int range = 0;
  
  private int code = 0;
  
  public RangeDecoder(int paramInt) {
    this.buf = new byte[paramInt - 5];
  }
  
  public void prepareInputBuffer(DataInputStream paramDataInputStream, int paramInt) throws IOException {
    if (paramInt < 5)
      throw new CorruptedInputException(); 
    if (paramDataInputStream.readUnsignedByte() != 0)
      throw new CorruptedInputException(); 
    this.code = paramDataInputStream.readInt();
    this.range = -1;
    this.pos = 0;
    this.end = paramInt - 5;
    paramDataInputStream.readFully(this.buf, 0, this.end);
  }
  
  public boolean isInBufferOK() {
    return (this.pos <= this.end);
  }
  
  public boolean isFinished() {
    return (this.pos == this.end && this.code == 0);
  }
  
  public void normalize() throws IOException {
    if ((this.range & 0xFF000000) == 0)
      try {
        this.code = this.code << 8 | this.buf[this.pos++] & 0xFF;
        this.range <<= 8;
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        throw new CorruptedInputException();
      }  
  }
  
  public int decodeBit(short[] paramArrayOfshort, int paramInt) throws IOException {
    boolean bool;
    normalize();
    short s = paramArrayOfshort[paramInt];
    int i = (this.range >>> 11) * s;
    if ((this.code ^ Integer.MIN_VALUE) < (i ^ Integer.MIN_VALUE)) {
      this.range = i;
      paramArrayOfshort[paramInt] = (short)(s + (2048 - s >>> 5));
      bool = false;
    } else {
      this.range -= i;
      this.code -= i;
      paramArrayOfshort[paramInt] = (short)(s - (s >>> 5));
      bool = true;
    } 
    return bool;
  }
  
  public int decodeBitTree(short[] paramArrayOfshort) throws IOException {
    int i = 1;
    while (true) {
      i = i << 1 | decodeBit(paramArrayOfshort, i);
      if (i >= paramArrayOfshort.length)
        return i - paramArrayOfshort.length; 
    } 
  }
  
  public int decodeReverseBitTree(short[] paramArrayOfshort) throws IOException {
    int i = 1;
    byte b = 0;
    int j = 0;
    while (true) {
      int k = decodeBit(paramArrayOfshort, i);
      i = i << 1 | k;
      j |= k << b++;
      if (i >= paramArrayOfshort.length)
        return j; 
    } 
  }
  
  public int decodeDirectBits(int paramInt) throws IOException {
    int i = 0;
    while (true) {
      normalize();
      this.range >>>= 1;
      int j = this.code - this.range >>> 31;
      this.code -= this.range & j - 1;
      i = i << 1 | 1 - j;
      if (--paramInt == 0)
        return i; 
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\rangecoder\RangeDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */