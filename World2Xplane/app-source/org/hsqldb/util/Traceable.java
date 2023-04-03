package org.hsqldb.util;

interface Traceable {
  public static final boolean TRACE = Boolean.getBoolean("hsqldb.util.trace");
  
  void trace(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\Traceable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */