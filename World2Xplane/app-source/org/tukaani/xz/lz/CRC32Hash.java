package org.tukaani.xz.lz;

class CRC32Hash {
  private static final int CRC32_POLY = -306674912;
  
  static final int[] crcTable = new int[256];
  
  static {
    for (byte b = 0; b < 'Ā'; b++) {
      int i = b;
      for (byte b1 = 0; b1 < 8; b1++) {
        if ((i & 0x1) != 0) {
          i = i >>> 1 ^ 0xEDB88320;
        } else {
          i >>>= 1;
        } 
      } 
      crcTable[b] = i;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\CRC32Hash.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */