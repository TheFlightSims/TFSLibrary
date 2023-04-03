package org.tukaani.xz.simple;

public final class X86 implements SimpleFilter {
  private static final boolean[] MASK_TO_ALLOWED_STATUS = new boolean[] { true, true, true, false, true, false, false, false };
  
  private static final int[] MASK_TO_BIT_NUMBER = new int[] { 0, 1, 2, 2, 3, 3, 3, 3 };
  
  private final boolean isEncoder;
  
  private int pos;
  
  private int prevMask = 0;
  
  private static boolean test86MSByte(byte paramByte) {
    int i = paramByte & 0xFF;
    return (i == 0 || i == 255);
  }
  
  public X86(boolean paramBoolean, int paramInt) {
    this.isEncoder = paramBoolean;
    this.pos = paramInt + 5;
  }
  
  public int code(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt1 - 1;
    int j = paramInt1 + paramInt2 - 5;
    int k;
    for (k = paramInt1; k <= j; k++) {
      if ((paramArrayOfbyte[k] & 0xFE) != 232)
        continue; 
      i = k - i;
      if ((i & 0xFFFFFFFC) != 0) {
        this.prevMask = 0;
      } else {
        this.prevMask = this.prevMask << i - 1 & 0x7;
        if (this.prevMask != 0 && (!MASK_TO_ALLOWED_STATUS[this.prevMask] || test86MSByte(paramArrayOfbyte[k + 4 - MASK_TO_BIT_NUMBER[this.prevMask]]))) {
          i = k;
          this.prevMask = this.prevMask << 1 | 0x1;
          continue;
        } 
      } 
      i = k;
      if (test86MSByte(paramArrayOfbyte[k + 4])) {
        int n;
        int m;
        for (m = paramArrayOfbyte[k + 1] & 0xFF | (paramArrayOfbyte[k + 2] & 0xFF) << 8 | (paramArrayOfbyte[k + 3] & 0xFF) << 16 | (paramArrayOfbyte[k + 4] & 0xFF) << 24;; m = n ^ (1 << 32 - i1) - 1) {
          if (this.isEncoder) {
            n = m + this.pos + k - paramInt1;
          } else {
            n = m - this.pos + k - paramInt1;
          } 
          if (this.prevMask == 0)
            break; 
          int i1 = MASK_TO_BIT_NUMBER[this.prevMask] * 8;
          if (!test86MSByte((byte)(n >>> 24 - i1)))
            break; 
        } 
        paramArrayOfbyte[k + 1] = (byte)n;
        paramArrayOfbyte[k + 2] = (byte)(n >>> 8);
        paramArrayOfbyte[k + 3] = (byte)(n >>> 16);
        paramArrayOfbyte[k + 4] = (byte)((n >>> 24 & 0x1) - 1 ^ 0xFFFFFFFF);
        k += 4;
      } else {
        this.prevMask = this.prevMask << 1 | 0x1;
      } 
      continue;
    } 
    i = k - i;
    this.prevMask = ((i & 0xFFFFFFFC) != 0) ? 0 : (this.prevMask << i - 1);
    k -= paramInt1;
    this.pos += k;
    return k;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\simple\X86.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */