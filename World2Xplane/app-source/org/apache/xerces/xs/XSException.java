package org.apache.xerces.xs;

public class XSException extends RuntimeException {
  static final long serialVersionUID = 3111893084677917742L;
  
  public short code;
  
  public static final short NOT_SUPPORTED_ERR = 1;
  
  public static final short INDEX_SIZE_ERR = 2;
  
  public XSException(short paramShort, String paramString) {
    super(paramString);
    this.code = paramShort;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\XSException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */