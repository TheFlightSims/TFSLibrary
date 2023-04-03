package org.tukaani.xz;

abstract class LZMA2Coder implements FilterCoder {
  public static final long FILTER_ID = 33L;
  
  public boolean changesSize() {
    return true;
  }
  
  public boolean nonLastOK() {
    return false;
  }
  
  public boolean lastOK() {
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\LZMA2Coder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */