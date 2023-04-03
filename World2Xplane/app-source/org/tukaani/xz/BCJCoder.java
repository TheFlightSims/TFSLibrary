package org.tukaani.xz;

abstract class BCJCoder implements FilterCoder {
  public static final long X86_FILTER_ID = 4L;
  
  public static final long POWERPC_FILTER_ID = 5L;
  
  public static final long IA64_FILTER_ID = 6L;
  
  public static final long ARM_FILTER_ID = 7L;
  
  public static final long ARMTHUMB_FILTER_ID = 8L;
  
  public static final long SPARC_FILTER_ID = 9L;
  
  public static boolean isBCJFilterID(long paramLong) {
    return (paramLong >= 4L && paramLong <= 9L);
  }
  
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\BCJCoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */