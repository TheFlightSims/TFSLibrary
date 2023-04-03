package org.apache.xerces.impl.xpath.regex;

public class ParseException extends RuntimeException {
  static final long serialVersionUID = -7012400318097691370L;
  
  final int location;
  
  public ParseException(String paramString, int paramInt) {
    super(paramString);
    this.location = paramInt;
  }
  
  public int getLocation() {
    return this.location;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xpath\regex\ParseException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */