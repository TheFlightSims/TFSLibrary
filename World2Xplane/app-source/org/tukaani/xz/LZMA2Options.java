package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

public class LZMA2Options extends FilterOptions {
  public static final int PRESET_MIN = 0;
  
  public static final int PRESET_MAX = 9;
  
  public static final int PRESET_DEFAULT = 6;
  
  public static final int DICT_SIZE_MIN = 4096;
  
  public static final int DICT_SIZE_MAX = 805306368;
  
  public static final int DICT_SIZE_DEFAULT = 8388608;
  
  public static final int LC_LP_MAX = 4;
  
  public static final int LC_DEFAULT = 3;
  
  public static final int LP_DEFAULT = 0;
  
  public static final int PB_MAX = 4;
  
  public static final int PB_DEFAULT = 2;
  
  public static final int MODE_UNCOMPRESSED = 0;
  
  public static final int MODE_FAST = 1;
  
  public static final int MODE_NORMAL = 2;
  
  public static final int NICE_LEN_MIN = 8;
  
  public static final int NICE_LEN_MAX = 273;
  
  public static final int MF_HC4 = 4;
  
  public static final int MF_BT4 = 20;
  
  private static final int[] presetToDictSize = new int[] { 262144, 1048576, 2097152, 4194304, 4194304, 8388608, 8388608, 16777216, 33554432, 67108864 };
  
  private static final int[] presetToDepthLimit = new int[] { 4, 8, 24, 48 };
  
  private int dictSize;
  
  private byte[] presetDict = null;
  
  private int lc;
  
  private int lp;
  
  private int pb;
  
  private int mode;
  
  private int niceLen;
  
  private int mf;
  
  private int depthLimit;
  
  static final boolean $assertionsDisabled;
  
  public LZMA2Options() throws UnsupportedOptionsException {
    setPreset(6);
  }
  
  public LZMA2Options(int paramInt) throws UnsupportedOptionsException {
    setPreset(paramInt);
  }
  
  public LZMA2Options(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) throws UnsupportedOptionsException {
    setDictSize(paramInt1);
    setLcLp(paramInt2, paramInt3);
    setPb(paramInt4);
    setMode(paramInt5);
    setNiceLen(paramInt6);
    setMatchFinder(paramInt7);
    setDepthLimit(this.depthLimit);
  }
  
  public void setPreset(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 0 || paramInt > 9)
      throw new UnsupportedOptionsException("Unsupported preset: " + paramInt); 
    this.lc = 3;
    this.lp = 0;
    this.pb = 2;
    this.dictSize = presetToDictSize[paramInt];
    if (paramInt <= 3) {
      this.mode = 1;
      this.mf = 4;
      this.niceLen = (paramInt <= 1) ? 128 : 273;
      this.depthLimit = presetToDepthLimit[paramInt];
    } else {
      this.mode = 2;
      this.mf = 20;
      this.niceLen = (paramInt == 4) ? 16 : ((paramInt == 5) ? 32 : 64);
      this.depthLimit = 0;
    } 
  }
  
  public void setDictSize(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 4096)
      throw new UnsupportedOptionsException("LZMA2 dictionary size must be at least 4 KiB: " + paramInt + " B"); 
    if (paramInt > 805306368)
      throw new UnsupportedOptionsException("LZMA2 dictionary size must not exceed 768 MiB: " + paramInt + " B"); 
    this.dictSize = paramInt;
  }
  
  public int getDictSize() {
    return this.dictSize;
  }
  
  public void setPresetDict(byte[] paramArrayOfbyte) {
    this.presetDict = paramArrayOfbyte;
  }
  
  public byte[] getPresetDict() {
    return this.presetDict;
  }
  
  public void setLcLp(int paramInt1, int paramInt2) throws UnsupportedOptionsException {
    if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 > 4 || paramInt2 > 4 || paramInt1 + paramInt2 > 4)
      throw new UnsupportedOptionsException("lc + lp must not exceed 4: " + paramInt1 + " + " + paramInt2); 
    this.lc = paramInt1;
    this.lp = paramInt2;
  }
  
  public void setLc(int paramInt) throws UnsupportedOptionsException {
    setLcLp(paramInt, this.lp);
  }
  
  public void setLp(int paramInt) throws UnsupportedOptionsException {
    setLcLp(this.lc, paramInt);
  }
  
  public int getLc() {
    return this.lc;
  }
  
  public int getLp() {
    return this.lp;
  }
  
  public void setPb(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 0 || paramInt > 4)
      throw new UnsupportedOptionsException("pb must not exceed 4: " + paramInt); 
    this.pb = paramInt;
  }
  
  public int getPb() {
    return this.pb;
  }
  
  public void setMode(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 0 || paramInt > 2)
      throw new UnsupportedOptionsException("Unsupported compression mode: " + paramInt); 
    this.mode = paramInt;
  }
  
  public int getMode() {
    return this.mode;
  }
  
  public void setNiceLen(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 8)
      throw new UnsupportedOptionsException("Minimum nice length of matches is 8 bytes: " + paramInt); 
    if (paramInt > 273)
      throw new UnsupportedOptionsException("Maximum nice length of matches is 273: " + paramInt); 
    this.niceLen = paramInt;
  }
  
  public int getNiceLen() {
    return this.niceLen;
  }
  
  public void setMatchFinder(int paramInt) throws UnsupportedOptionsException {
    if (paramInt != 4 && paramInt != 20)
      throw new UnsupportedOptionsException("Unsupported match finder: " + paramInt); 
    this.mf = paramInt;
  }
  
  public int getMatchFinder() {
    return this.mf;
  }
  
  public void setDepthLimit(int paramInt) throws UnsupportedOptionsException {
    if (paramInt < 0)
      throw new UnsupportedOptionsException("Depth limit cannot be negative: " + paramInt); 
    this.depthLimit = paramInt;
  }
  
  public int getDepthLimit() {
    return this.depthLimit;
  }
  
  public int getEncoderMemoryUsage() {
    return (this.mode == 0) ? UncompressedLZMA2OutputStream.getMemoryUsage() : LZMA2OutputStream.getMemoryUsage(this);
  }
  
  public FinishableOutputStream getOutputStream(FinishableOutputStream paramFinishableOutputStream) {
    return (FinishableOutputStream)((this.mode == 0) ? new UncompressedLZMA2OutputStream(paramFinishableOutputStream) : new LZMA2OutputStream(paramFinishableOutputStream, this));
  }
  
  public int getDecoderMemoryUsage() {
    int i = this.dictSize - 1;
    i |= i >>> 2;
    i |= i >>> 3;
    i |= i >>> 4;
    i |= i >>> 8;
    i |= i >>> 16;
    return LZMA2InputStream.getMemoryUsage(i + 1);
  }
  
  public InputStream getInputStream(InputStream paramInputStream) throws IOException {
    return new LZMA2InputStream(paramInputStream, this.dictSize);
  }
  
  FilterEncoder getFilterEncoder() {
    return new LZMA2Encoder(this);
  }
  
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      assert false;
      throw new RuntimeException();
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2Options.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */