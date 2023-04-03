package org.apache.xerces.dom3.as;

public class DOMASException extends RuntimeException {
  public short code;
  
  public static final short DUPLICATE_NAME_ERR = 1;
  
  public static final short TYPE_ERR = 2;
  
  public static final short NO_AS_AVAILABLE = 3;
  
  public static final short WRONG_MIME_TYPE_ERR = 4;
  
  public DOMASException(short paramShort, String paramString) {
    super(paramString);
    this.code = paramShort;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom3\as\DOMASException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */