package org.tukaani.xz.lz;

import java.io.IOException;
import java.io.OutputStream;

public abstract class LZEncoder {
  public static final int MF_HC4 = 4;
  
  public static final int MF_BT4 = 20;
  
  private final int keepSizeBefore;
  
  private final int keepSizeAfter;
  
  final int matchLenMax;
  
  final int niceLen;
  
  final byte[] buf;
  
  int readPos = -1;
  
  private int readLimit = -1;
  
  private boolean finishing = false;
  
  private int writePos = 0;
  
  private int pendingSize = 0;
  
  static final boolean $assertionsDisabled;
  
  static void normalize(int[] paramArrayOfint, int paramInt) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b] <= paramInt) {
        paramArrayOfint[b] = 0;
      } else {
        paramArrayOfint[b] = paramArrayOfint[b] - paramInt;
      } 
    } 
  }
  
  private static int getBufSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt2 + paramInt1;
    int j = paramInt3 + paramInt4;
    int k = Math.min(paramInt1 / 2 + 262144, 536870912);
    return i + j + k;
  }
  
  public static int getMemoryUsage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int i = getBufSize(paramInt1, paramInt2, paramInt3, paramInt4) / 1024 + 10;
    switch (paramInt5) {
      case 4:
        i += HC4.getMemoryUsage(paramInt1);
        return i;
      case 20:
        i += BT4.getMemoryUsage(paramInt1);
        return i;
    } 
    throw new IllegalArgumentException();
  }
  
  public static LZEncoder getInstance(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
    switch (paramInt6) {
      case 4:
        return new HC4(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt7);
      case 20:
        return new BT4(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt7);
    } 
    throw new IllegalArgumentException();
  }
  
  LZEncoder(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.buf = new byte[getBufSize(paramInt1, paramInt2, paramInt3, paramInt5)];
    this.keepSizeBefore = paramInt2 + paramInt1;
    this.keepSizeAfter = paramInt3 + paramInt5;
    this.matchLenMax = paramInt5;
    this.niceLen = paramInt4;
  }
  
  public void setPresetDict(int paramInt, byte[] paramArrayOfbyte) {
    assert !isStarted();
    if (paramArrayOfbyte != null) {
      int i = Math.min(paramArrayOfbyte.length, paramInt);
      int j = paramArrayOfbyte.length - i;
      System.arraycopy(paramArrayOfbyte, j, this.buf, 0, i);
      skip(i);
    } 
  }
  
  private void moveWindow() {
    int i = this.readPos + 1 - this.keepSizeBefore & 0xFFFFFFF0;
    int j = this.writePos - i;
    System.arraycopy(this.buf, i, this.buf, 0, j);
    this.readPos -= i;
    this.readLimit -= i;
    this.writePos -= i;
  }
  
  public int fillWindow(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    assert !this.finishing;
    if (this.readPos >= this.buf.length - this.keepSizeAfter)
      moveWindow(); 
    if (paramInt2 > this.buf.length - this.writePos)
      paramInt2 = this.buf.length - this.writePos; 
    System.arraycopy(paramArrayOfbyte, paramInt1, this.buf, this.writePos, paramInt2);
    this.writePos += paramInt2;
    if (this.writePos >= this.keepSizeAfter)
      this.readLimit = this.writePos - this.keepSizeAfter; 
    if (this.pendingSize > 0 && this.readPos < this.readLimit) {
      this.readPos -= this.pendingSize;
      int i = this.pendingSize;
      this.pendingSize = 0;
      skip(i);
      assert this.pendingSize < i;
    } 
    return paramInt2;
  }
  
  public boolean isStarted() {
    return (this.readPos != -1);
  }
  
  public void setFlushing() {
    this.readLimit = this.writePos - 1;
  }
  
  public void setFinishing() {
    this.readLimit = this.writePos - 1;
    this.finishing = true;
  }
  
  public boolean hasEnoughData(int paramInt) {
    return (this.readPos - paramInt < this.readLimit);
  }
  
  public void copyUncompressed(OutputStream paramOutputStream, int paramInt1, int paramInt2) throws IOException {
    paramOutputStream.write(this.buf, this.readPos + 1 - paramInt1, paramInt2);
  }
  
  public int getAvail() {
    assert isStarted();
    return this.writePos - this.readPos;
  }
  
  public int getPos() {
    return this.readPos;
  }
  
  public int getByte(int paramInt) {
    return this.buf[this.readPos - paramInt] & 0xFF;
  }
  
  public int getByte(int paramInt1, int paramInt2) {
    return this.buf[this.readPos + paramInt1 - paramInt2] & 0xFF;
  }
  
  public int getMatchLen(int paramInt1, int paramInt2) {
    int i = this.readPos - paramInt1 - 1;
    byte b;
    for (b = 0; b < paramInt2 && this.buf[this.readPos + b] == this.buf[i + b]; b++);
    return b;
  }
  
  public int getMatchLen(int paramInt1, int paramInt2, int paramInt3) {
    int i = this.readPos + paramInt1;
    int j = i - paramInt2 - 1;
    byte b;
    for (b = 0; b < paramInt3 && this.buf[i + b] == this.buf[j + b]; b++);
    return b;
  }
  
  public boolean verifyMatches(Matches paramMatches) {
    int i = Math.min(getAvail(), this.matchLenMax);
    for (byte b = 0; b < paramMatches.count; b++) {
      if (getMatchLen(paramMatches.dist[b], i) != paramMatches.len[b])
        return false; 
    } 
    return true;
  }
  
  int movePos(int paramInt1, int paramInt2) {
    assert paramInt1 >= paramInt2;
    this.readPos++;
    int i = this.writePos - this.readPos;
    if (i < paramInt1 && (i < paramInt2 || !this.finishing)) {
      this.pendingSize++;
      i = 0;
    } 
    return i;
  }
  
  public abstract Matches getMatches();
  
  public abstract void skip(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\LZEncoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */