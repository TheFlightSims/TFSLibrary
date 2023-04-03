package org.tukaani.xz.simple;

public final class ARMThumb implements SimpleFilter {
  private final boolean isEncoder;
  
  private int pos;
  
  public ARMThumb(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt + 4;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 + paramInt2 - 4;
    int j;
    for (j = paramInt1; j <= i; j += 2) {
      if ((paramArrayOfbyte[j + 1] & 0xF8) == 240 && (paramArrayOfbyte[j + 3] & 0xF8) == 248) {
        int m;
        int k = (paramArrayOfbyte[j + 1] & 0x7) << 19 | (paramArrayOfbyte[j] & 0xFF) << 11 | (paramArrayOfbyte[j + 3] & 0x7) << 8 | paramArrayOfbyte[j + 2] & 0xFF;
        k <<= 1;
        if (this.isEncoder) {
          m = k + this.pos + j - paramInt1;
        } else {
          m = k - this.pos + j - paramInt1;
        } 
        m >>>= 1;
        paramArrayOfbyte[j + 1] = (byte)(0xF0 | m >>> 19 & 0x7);
        paramArrayOfbyte[j] = (byte)(m >>> 11);
        paramArrayOfbyte[j + 3] = (byte)(0xF8 | m >>> 8 & 0x7);
        paramArrayOfbyte[j + 2] = (byte)m;
        j += 2;
      } 
    } 
    j -= paramInt1;
    this.pos += j;
    return j;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\ARMThumb.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */