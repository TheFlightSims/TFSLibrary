package org.tukaani.xz;

public class XZ {
  public static final byte[] HEADER_MAGIC = new byte[] { -3, 55, 122, 88, 90, 0 };
  
  public static final byte[] FOOTER_MAGIC = new byte[] { 89, 90 };
  
  public static final int CHECK_NONE = 0;
  
  public static final int CHECK_CRC32 = 1;
  
  public static final int CHECK_CRC64 = 4;
  
  public static final int CHECK_SHA256 = 10;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\XZ.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */