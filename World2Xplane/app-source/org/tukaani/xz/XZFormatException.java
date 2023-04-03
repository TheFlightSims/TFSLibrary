package org.tukaani.xz;

public class XZFormatException extends XZIOException {
  private static final long serialVersionUID = 3L;
  
  public XZFormatException() {
    super("Input is not in the XZ format");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\XZFormatException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */