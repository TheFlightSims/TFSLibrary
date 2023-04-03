package org.hsqldb.lib.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.DriverManager;

public class JavaSystem {
  public static int gcFrequency;
  
  public static int memoryRecords;
  
  static final BigDecimal BD_1 = BigDecimal.valueOf(1L);
  
  static final BigDecimal MBD_1 = BigDecimal.valueOf(-1L);
  
  public static void gc() {
    if (gcFrequency > 0 && memoryRecords > gcFrequency) {
      memoryRecords = 0;
      System.gc();
    } 
  }
  
  public static IOException toIOException(Throwable paramThrowable) {
    return (paramThrowable instanceof IOException) ? (IOException)paramThrowable : new IOException(paramThrowable);
  }
  
  public static int precision(BigDecimal paramBigDecimal) {
    int i;
    if (paramBigDecimal == null)
      return 0; 
    if (paramBigDecimal.compareTo(BD_1) < 0 && paramBigDecimal.compareTo(MBD_1) > 0) {
      i = paramBigDecimal.scale();
    } else {
      i = paramBigDecimal.precision();
    } 
    return i;
  }
  
  public static String toString(BigDecimal paramBigDecimal) {
    return (paramBigDecimal == null) ? null : paramBigDecimal.toPlainString();
  }
  
  public static void setLogToSystem(boolean paramBoolean) {
    try {
      PrintWriter printWriter = paramBoolean ? new PrintWriter(System.out) : null;
      DriverManager.setLogWriter(printWriter);
    } catch (Exception exception) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\java\JavaSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */