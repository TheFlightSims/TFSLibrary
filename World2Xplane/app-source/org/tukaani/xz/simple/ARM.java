package org.tukaani.xz.simple;

public final class ARM implements SimpleFilter {
  private final boolean isEncoder;
  
  private int pos;
  
  public ARM(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt + 8;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2 - 4;
    int j;
    for (j = paramInt1; j <= i; j += 4) {
      if ((paramArrayOfbyte[j + 3] & 0xFF) == 235) {
        int m;
        int k = (paramArrayOfbyte[j + 2] & 0xFF) << 16 | (paramArrayOfbyte[j + 1] & 0xFF) << 8 | paramArrayOfbyte[j] & 0xFF;
        k <<= 2;
        if (this.isEncoder) {
          m = k + this.pos + j - paramInt1;
        } else {
          m = k - this.pos + j - paramInt1;
        } 
        m >>>= 2;
        paramArrayOfbyte[j + 2] = (byte)(m >>> 16);
        paramArrayOfbyte[j + 1] = (byte)(m >>> 8);
        paramArrayOfbyte[j] = (byte)m;
      } 
    } 
    j -= paramInt1;
    this.pos += j;
    return j;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\ARM.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */