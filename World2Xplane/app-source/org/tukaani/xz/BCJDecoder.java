package org.tukaani.xz;

import java.io.InputStream;
import org.tukaani.xz.simple.ARM;
import org.tukaani.xz.simple.ARMThumb;
import org.tukaani.xz.simple.IA64;
import org.tukaani.xz.simple.PowerPC;
import org.tukaani.xz.simple.SPARC;
import org.tukaani.xz.simple.SimpleFilter;
import org.tukaani.xz.simple.X86;

class BCJDecoder extends BCJCoder implements FilterDecoder {
  private final long filterID;
  
  private final int startOffset;
  
  static final boolean $assertionsDisabled;
  
  BCJDecoder(long paramLong, byte[] paramArrayOfbyte) throws UnsupportedOptionsException {
    assert isBCJFilterID(paramLong);
    this.filterID = paramLong;
    if (paramArrayOfbyte.length == 0) {
      this.startOffset = 0;
    } else if (paramArrayOfbyte.length == 4) {
      int i = 0;
      for (byte b = 0; b < 4; b++)
        i |= (paramArrayOfbyte[b] & 0xFF) << b * 8; 
      this.startOffset = i;
    } else {
      throw new UnsupportedOptionsException("Unsupported BCJ filter properties");
    } 
  }
  
  public int getMemoryUsage() {
    return SimpleInputStream.getMemoryUsage();
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    SPARC sPARC;
    X86 x86 = null;
    if (this.filterID == 4L) {
      x86 = new X86(false, this.startOffset);
    } else if (this.filterID == 5L) {
      PowerPC powerPC = new PowerPC(false, this.startOffset);
    } else if (this.filterID == 6L) {
      IA64 iA64 = new IA64(false, this.startOffset);
    } else if (this.filterID == 7L) {
      ARM aRM = new ARM(false, this.startOffset);
    } else if (this.filterID == 8L) {
      ARMThumb aRMThumb = new ARMThumb(false, this.startOffset);
    } else if (this.filterID == 9L) {
      sPARC = new SPARC(false, this.startOffset);
    } else {
      assert false;
    } 
    return new SimpleInputStream(paramInputStream, (SimpleFilter)sPARC);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\BCJDecoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */