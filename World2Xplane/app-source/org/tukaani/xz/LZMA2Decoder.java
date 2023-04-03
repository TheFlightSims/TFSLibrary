package org.tukaani.xz;

import java.io.InputStream;

class LZMA2Decoder extends LZMA2Coder implements FilterDecoder {
  private int dictSize;
  
  LZMA2Decoder(byte[] paramArrayOfbyte) throws UnsupportedOptionsException {
    if (paramArrayOfbyte.length != 1 || (paramArrayOfbyte[0] & 0xFF) > 37)
      throw new UnsupportedOptionsException("Unsupported LZMA2 properties"); 
    this.dictSize = 0x2 | paramArrayOfbyte[0] & 0x1;
    this.dictSize <<= (paramArrayOfbyte[0] >>> 1) + 11;
  }
  
  public int getMemoryUsage() {
    return LZMA2InputStream.getMemoryUsage(this.dictSize);
  }
  
  public InputStream getInputStream(InputStream paramInputStream) {
    return new LZMA2InputStream(paramInputStream, this.dictSize);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2Decoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */