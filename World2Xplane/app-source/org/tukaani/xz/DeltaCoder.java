package org.tukaani.xz;

abstract class DeltaCoder implements FilterCoder {
  public static final long FILTER_ID = 3L;
  
  public boolean changesSize() {
    return false;
  }
  
  public boolean nonLastOK() {
    return true;
  }
  
  public boolean lastOK() {
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\DeltaCoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */