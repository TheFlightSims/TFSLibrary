package org.tukaani.xz.lz;

final class Hash234 extends CRC32Hash {
  private static final int HASH_2_SIZE = 1024;
  
  private static final int HASH_2_MASK = 1023;
  
  private static final int HASH_3_SIZE = 65536;
  
  private static final int HASH_3_MASK = 65535;
  
  private final int hash4Mask;
  
  private final int[] hash2Table = new int[1024];
  
  private final int[] hash3Table = new int[65536];
  
  private final int[] hash4Table;
  
  private int hash2Value = 0;
  
  private int hash3Value = 0;
  
  private int hash4Value = 0;
  
  static int getHash4Size(int paramInt) {
    int i = paramInt - 1;
    i |= i >>> 1;
    i |= i >>> 2;
    i |= i >>> 4;
    i |= i >>> 8;
    i >>>= 1;
    i |= 0xFFFF;
    if (i > 16777216)
      i >>>= 1; 
    return i + 1;
  }
  
  static int getMemoryUsage(int paramInt) {
    return (66560 + getHash4Size(paramInt)) / 256 + 4;
  }
  
  Hash234(int paramInt) {
    this.hash4Table = new int[getHash4Size(paramInt)];
    this.hash4Mask = this.hash4Table.length - 1;
  }
  
  void calcHashes(byte[] paramArrayOfbyte, int paramInt) {
    int i = crcTable[paramArrayOfbyte[paramInt] & 0xFF] ^ paramArrayOfbyte[paramInt + 1] & 0xFF;
    this.hash2Value = i & 0x3FF;
    i ^= (paramArrayOfbyte[paramInt + 2] & 0xFF) << 8;
    this.hash3Value = i & 0xFFFF;
    i ^= crcTable[paramArrayOfbyte[paramInt + 3] & 0xFF] << 5;
    this.hash4Value = i & this.hash4Mask;
  }
  
  int getHash2Pos() {
    return this.hash2Table[this.hash2Value];
  }
  
  int getHash3Pos() {
    return this.hash3Table[this.hash3Value];
  }
  
  int getHash4Pos() {
    return this.hash4Table[this.hash4Value];
  }
  
  void updateTables(int paramInt) {
    this.hash2Table[this.hash2Value] = paramInt;
    this.hash3Table[this.hash3Value] = paramInt;
    this.hash4Table[this.hash4Value] = paramInt;
  }
  
  void normalize(int paramInt) {
    LZEncoder.normalize(this.hash2Table, paramInt);
    LZEncoder.normalize(this.hash3Table, paramInt);
    LZEncoder.normalize(this.hash4Table, paramInt);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\lz\Hash234.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */