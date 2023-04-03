package ch.qos.logback.classic.spi;

public interface IThrowableProxy {
  String getMessage();
  
  String getClassName();
  
  StackTraceElementProxy[] getStackTraceElementProxyArray();
  
  int getCommonFrames();
  
  IThrowableProxy getCause();
  
  IThrowableProxy[] getSuppressed();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\IThrowableProxy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */