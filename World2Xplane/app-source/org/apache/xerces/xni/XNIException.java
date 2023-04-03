package org.apache.xerces.xni;

public class XNIException extends RuntimeException {
  static final long serialVersionUID = 9019819772686063775L;
  
  private Exception fException;
  
  public XNIException(String paramString) {
    super(paramString);
  }
  
  public XNIException(Exception paramException) {
    super(paramException.getMessage());
    this.fException = paramException;
  }
  
  public XNIException(String paramString, Exception paramException) {
    super(paramString);
    this.fException = paramException;
  }
  
  public Exception getException() {
    return this.fException;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\XNIException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */