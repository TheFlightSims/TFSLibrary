package org.tukaani.xz.simple;

public final class SPARC implements SimpleFilter {
  private final boolean isEncoder;
  
  private int pos;
  
  public SPARC(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2 - 4;
    int j;
    for (j = paramInt1; j <= i; j += 4) {
      if ((paramArrayOfbyte[j] == 64 && (paramArrayOfbyte[j + 1] & 0xC0) == 0) || (paramArrayOfbyte[j] == Byte.MAX_VALUE && (paramArrayOfbyte[j + 1] & 0xC0) == 192)) {
        int k = (paramArrayOfbyte[j] & 0xFF) << 24 | (paramArrayOfbyte[j + 1] & 0xFF) << 16 | (paramArrayOfbyte[j + 2] & 0xFF) << 8 | paramArrayOfbyte[j + 3] & 0xFF;
        k <<= 2;
        if (this.isEncoder) {
          m = k + this.pos + j - paramInt1;
        } else {
          m = k - this.pos + j - paramInt1;
        } 
        m >>>= 2;
        int m = 0 - (m >>> 22 & 0x1) << 22 & 0x3FFFFFFF | m & 0x3FFFFF | 0x40000000;
        paramArrayOfbyte[j] = (byte)(m >>> 24);
        paramArrayOfbyte[j + 1] = (byte)(m >>> 16);
        paramArrayOfbyte[j + 2] = (byte)(m >>> 8);
        paramArrayOfbyte[j + 3] = (byte)m;
      } 
    } 
    j -= paramInt1;
    this.pos += j;
    return j;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\SPARC.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */